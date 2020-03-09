package database;

import database.properties.Configs;
import database.properties.Constants;

import java.sql.*;

public class DatabaseHandler extends Configs implements interfaces.DatabaseHandlerInterface {

    String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

    public void deleteWord(String id) {

        String sql = "delete from dictionary where id=" + id;

        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPass);
             Statement stmt = conn.createStatement();) {

            stmt.executeUpdate(sql);
            System.out.println("Record deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addWord(String czech, String english) {
        try {
            String insert = "INSERT INTO " + Constants.DICTIONARY + "(" + Constants.CZECH + "," + Constants.ENGLISH + ")" +
                    "VALUES(?,?)";

            PreparedStatement preparedStatement = getDatabaseConnection().prepareStatement(insert);
            preparedStatement.setString(1, czech);
            preparedStatement.setString(2, english);

            preparedStatement.execute();
            System.out.println("new word added");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getCzechWord(String english) throws SQLException, ClassNotFoundException {


        String select = "SELECT " + Constants.CZECH + " FROM " + Constants.DICTIONARY + " WHERE " + Constants.ENGLISH + "=?";

        PreparedStatement preparedStatement = getDatabaseConnection().prepareStatement(select);
        preparedStatement.setString(1, english);

        return preparedStatement.executeQuery();
    }

    public ResultSet getEnglishWord(String czech) throws SQLException, ClassNotFoundException {

        String select = "SELECT " + Constants.ENGLISH + " FROM " + Constants.DICTIONARY + " WHERE " + Constants.CZECH + "=?";

        PreparedStatement preparedStatement = getDatabaseConnection().prepareStatement(select);
        preparedStatement.setString(1, czech);

        return preparedStatement.executeQuery();
    }
}
