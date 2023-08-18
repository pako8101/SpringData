package orm;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DBContext<E> {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String fieldList = this.getDBFieldsWithOutId(entity);
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


    private String getDBFieldsWithOutId(E entity) {
        return Arrays.stream(entity.getClass()
                        .getDeclaredFields())
                .filter(f -> f.getAnnotation(Column.class) != null)
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.joining(","));


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
        return find(entityType,null);
    }

    @Override
    public Iterable<E> find(Class<E> entityType,String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(entityType);

        String sql = String.format("SELECT * FROM %s %s ",
                tableName, where == null ? "" : "WHERE " + where);

        ResultSet resultSet = this.connection.prepareStatement(sql).executeQuery();
        List<E> result = new ArrayList<>();

     E lastResult =  this.fillEntity(entityType, resultSet);
     while (lastResult !=null){
         result.add(lastResult);
         lastResult = this.fillEntity(entityType,resultSet);
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
        }  else  if (field.getType() == int.class || field.getType() == Integer.class) {
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
}
