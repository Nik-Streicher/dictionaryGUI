package interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseHandlerInterface {

    void addWord(String czech, String english);

    ResultSet getCzechWord(String english) throws SQLException, ClassNotFoundException;

    ResultSet getEnglishWord(String czech) throws SQLException, ClassNotFoundException;

    void deleteWord(String id);
}
