package orm;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static entities.Constants.Constants.ID_COLUM_MISSING_MESSAGE;
import static entities.Constants.Constants.Queries.*;

public class EntityManager<E> implements DBContext<E> {

    private final Connection connection;
    private static final String INT = "INT";
    private static final String DATE = "DATE";
    private static final String VARCHAR = "VARCHAR";
    private static final String CREATE_VALUE_FORMAT = "%s = %s";
    private static final String CREATE_QUERY_FORMAT =
            "CREATE TABLE %s (id int primary key auto_increment, %s);";

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String fieldList = this.getDBFieldsWithOutId(entity).toString();

        String valueList = this.getValuesWithoutIdentity(entity);

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, fieldList, valueList);

        return this.connection.prepareStatement(sql).execute();

    }

    private String getValuesWithoutIdentity(E entity) throws IllegalAccessException {
        List<String> result = new ArrayList<>();
        for (Field declaredField : entity.getClass().getDeclaredFields()) {

            if (declaredField.getAnnotation(Column.class) == null) {
                continue;
            }
            declaredField.setAccessible(true);
            Object value = declaredField.get(entity);
            result.add("\"" + value.toString() + "\"");
        }
        return String.join(",", result);

    }


    private List<Field> getDBFieldsWithOutId(E entity) {
        return Arrays.stream(entity.getClass()
                        .getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class)
                        && f.isAnnotationPresent(Column.class))
                .toList();


    }

    private String getTableName(Class<?> clazz) {
        final Entity annotation = clazz.getAnnotation(Entity.class);

        if (annotation == null) {
            throw new ORMException("Provided class does not have Entity annotation");
        }
        return annotation.name();
    }

    @Override
    public Iterable<E> find(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(entityType, null);
    }

    @Override
    public Iterable<E> find(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(entityType);

        String sql = String.format("SELECT * FROM %s %s ",
                tableName, where == null ? "" : "WHERE " + where);

        ResultSet resultSet = this.connection.prepareStatement(sql).executeQuery();
        List<E> result = new ArrayList<>();

        E lastResult = this.fillEntity(entityType, resultSet);
        while (lastResult != null) {
            result.add(lastResult);
            lastResult = this.fillEntity(entityType, resultSet);
        }
        return result;
    }

    @Override
    public E findFirst(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(entityType);

        String sql = String.format("SELECT * FROM %s %s limit 1",
                tableName, where == null ? "" : "WHERE " + where);

        ResultSet resultSet = this.connection.prepareStatement(sql).executeQuery();

        return this.fillEntity(entityType, resultSet);
    }

    private E fillEntity(Class<E> entityType, ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!resultSet.next()) {
            return null;
        }
        E entity = entityType.getDeclaredConstructor().newInstance();

        final Field[] declaredFields = entityType.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(Column.class)
                    && !declaredField.isAnnotationPresent(Id.class)) {
                continue;
            }

            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            String fieldName = columnAnnotation == null ? declaredField.getName() : columnAnnotation.name();

            String value = resultSet.getString(fieldName);
            entity = this.fillData(entity, declaredField, value);
        }

        return entity;
    }

    private E fillData(E entity, Field field, String value) throws IllegalAccessException {
        field.setAccessible(true);

        if (field.getType() == long.class || field.getType() == Long.class) {
            field.setLong(entity, Long.parseLong(value));
        } else if (field.getType() == int.class || field.getType() == Integer.class) {
            field.setInt(entity, Integer.parseInt(value));

        } else if (field.getType() == LocalDate.class) {
            field.set(entity, LocalDate.parse(value));
        } else if (field.getType() == String.class) {
            field.set(entity, value);
        } else {
            throw new ORMException("Unsupported type " + field.getType());
        }
        return entity;
    }

    @Override
    public E findFirst(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        return findFirst(entityType, null);


    }

    @Override
    public void doCreate(Class<E> entity) throws SQLException {
        final String tableName = getTableName(entity.getClass());

        final List<KeyValuePair> fieldsAndTypesInKeyValuePairs =
                getAllFieldsAndTypesInKeyValuePairs((E) entity);
        final String fieldsAndTypesInKeyValuePairsFormatted =
                fieldsAndTypesInKeyValuePairs.stream()
                        .map(keyValuePair -> String.format(CREATE_VALUE_FORMAT, keyValuePair.key, keyValuePair.value))
                        .collect(Collectors.joining(", "));

        final PreparedStatement createStatement =
                connection.prepareStatement
                        (String.format(CREATE_QUERY_FORMAT, tableName, fieldsAndTypesInKeyValuePairsFormatted));
        createStatement.execute();


    }

    private List<KeyValuePair> getAllFieldsAndTypesInKeyValuePairs(E entity) {

        return getDBFieldsWithOutId(entity)
                .stream()
                .map(f -> new KeyValuePair(getSqlColumnName(f), getSqlType(f.getType())))
                .toList();
    }

    private String getSqlType(Class<?> type) {
        if (type == Integer.class || type == int.class || type == long.class || type == Long.class) {
            return INT;
        } else if (type == LocalDate.class) {
            return DATE;
        }
        return VARCHAR;
    }

    private String getSqlColumnName(Field field) {
        return field.getAnnotationsByType(Column.class)[0].name();
    }

    public record KeyValuePair(String key, String value) {
    }

    @Override
    public void doAlter(Class<E> entity) throws SQLException {

        final String tableName = getTableName(entity);
        String addColumnsStatement = addColumns(entity, tableName);
        final Set<Field> currentFields = getEntityColumnFields(entity);

        String alterQuery = String.format(ALTER_TABLE_FORMAT, tableName, addColumnsStatement);

        final PreparedStatement alterStatement = connection.prepareStatement(alterQuery);

        alterStatement.execute();
    }

    private Set<Field> getEntityColumnFields(Class<E> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .collect(Collectors.toSet());


    }

    private String addColumns(Class<E> entity, String tableName) throws SQLException {
        final Set<String> sqlColumns = getSqlColumnNames(entity, tableName);
        final List<Field> allFieldsWithoutId = getDBFieldsWithOutId((E) entity);
        final List<String> allNewFields = new ArrayList<>();
        for (Field field : allFieldsWithoutId) {
            final String fieldName = getSqlColumnName(field);
            if (sqlColumns.contains(fieldName)) {
                final String dropStatement = String.format(DROP_COLUMN_FORMAT, fieldName);
                allNewFields.add(dropStatement);
                continue;
            }
            final String sqlType = getSqlType(field.getType());
            String addStatement = String.format(ADD_COLUMN_FORMAT, fieldName, sqlType);
            allNewFields.add(addStatement);

        }
        return String.join(", ", allNewFields);
    }

    private Set<String> getSqlColumnNames(Class<E> entity, String tableName) throws SQLException {
        Set<String> allFields = new HashSet<>();
        final PreparedStatement getAllFieldStatement =
                connection.prepareStatement(GET_ALL_COLUMN_NAMES_BY_TABLE_NAME);
        getAllFieldStatement.setString(1, tableName);
        ResultSet resultSet = getAllFieldStatement.executeQuery();
        while (resultSet.next()) {
            allFields.add(resultSet.getString(1));
        }
        return allFields;

    }

    private Field getIdColumn(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException(ID_COLUM_MISSING_MESSAGE));

    }

    @Override
    public void doDelete(E entity) throws SQLException, IllegalAccessException {
        final String tableName = getTableName(entity.getClass());
        final Field idFiled = getIdColumn(entity.getClass());

        final String idName = getSqlColumnName(idFiled);
        final Object idValue = getFieldValue(idFiled, idFiled);
        final String deleteQuery = String.format(DELETE_RECORD_BY_CONDITION_FORMAT
                , tableName
                , idName
                , idValue);
        final PreparedStatement deletedStatements = connection.prepareStatement(deleteQuery);
        deletedStatements.execute();
    }

    private Object getFieldValue(Field entity, Field idName) throws IllegalAccessException {
        idName.setAccessible(true);
        return entity.get(idName);
    }


}
