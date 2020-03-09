package Test;

import database.DatabaseHandler;
import interfaces.DatabaseHandlerInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //new handler object
        DatabaseHandlerInterface databaseHandler = new DatabaseHandler();
        DatabaseHandler databaseHandler1 = new DatabaseHandler();

        //adding new words to database
        databaseHandler.addWord("jablko", "apple");

        //czech to english
        ResultSet result = databaseHandler.getEnglishWord("jedna");

        while (result.next()) {
            System.out.println(result.getString(1));
        }

        //english to czech
        ResultSet result2 = databaseHandler.getCzechWord("one");

        while (result2.next()) {
            System.out.println(result2.getString(1));
        }

        //delete record by id
        databaseHandler1.deleteWord("2");
    }
}
