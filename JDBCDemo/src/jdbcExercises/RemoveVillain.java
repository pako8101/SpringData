package jdbcExercises;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {
    private static final String GET_VILLAIN_BY_ID = "select v.name from  villains v where  id=?";
    private static final String GET_ALL_MINIONS_BY_VILLAIN_ID =
            "select count( mv.minion_id) m_count from minions_villains mv where mv.villain_id = ?";

    private static final String COLUMN_LABEL_MINION_COUNT = "m_count";
    private static final String NO_SUCH_VILLAIN_MESSAGE = "No such villain was found";
    private static final String DELETE_MINIONS_VILLAINS_BY_VILLAIN_ID =
            "delete  from  minions_villains as mv where mv.villain_id = ? ";
    private static final String DELETE_VILLAINS_BY_VILLAIN_ID =
            "delete from villains as v where v.id = ?";
    private static final String DELETE_COUNT_OF_MINIONS_FORMAT = "%s minions released%n";
    private static final String DELETED_VILLAIN_FORMAT  = "%s was deleted%n";
    public static void main(String[] args) throws SQLException {
        final Connection connection = utils.getSQLConnection();

        final int villainId = new Scanner(System.in).nextInt();

        final PreparedStatement selectedVillain = connection.prepareStatement(GET_VILLAIN_BY_ID);
        selectedVillain.setInt(1, villainId);
        final ResultSet villainSet = selectedVillain.executeQuery();

        if (!villainSet.next()) {
            System.out.println(NO_SUCH_VILLAIN_MESSAGE);
            connection.close();
            return;
        }
        final String villainName = villainSet.getString(1);

        final PreparedStatement selectAllMinions = connection.prepareStatement(GET_ALL_MINIONS_BY_VILLAIN_ID);
        selectAllMinions.setInt(1, villainId);

        final ResultSet countOfMinions = selectAllMinions.executeQuery();
countOfMinions.next();

final int countOfDeletedMinions = countOfMinions.getInt(1);

connection.setAutoCommit(false);

try (PreparedStatement deletedMinionsStatement =   connection.prepareStatement(DELETE_MINIONS_VILLAINS_BY_VILLAIN_ID);
     PreparedStatement deletedVillainsStatement =   connection.prepareStatement(DELETE_VILLAINS_BY_VILLAIN_ID);){
deletedMinionsStatement.setInt(1,villainId);
deletedMinionsStatement.executeUpdate();

deletedVillainsStatement.setInt(1,villainId);
deletedVillainsStatement.executeUpdate();

connection.commit();
    System.out.printf(DELETED_VILLAIN_FORMAT,villainName);
    System.out.println();
    System.out.printf(DELETE_COUNT_OF_MINIONS_FORMAT,countOfDeletedMinions);
}catch (SQLException sqlException){
    sqlException.printStackTrace();

    connection.rollback();
}
connection.close();

    }


}
