package sample;

import database.DatabaseHandler;
import interfaces.DatabaseHandlerInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    DatabaseHandlerInterface databaseHandler;

    @FXML
    private TextField enterField;

    @FXML
    private TextField outputField;

    @FXML
    private ComboBox<String> chooseLanguage;

    @FXML
    void translate(ActionEvent event) throws SQLException, ClassNotFoundException {
        ResultSet result;

        switch (chooseLanguage.getSelectionModel().getSelectedItem()) {
            case "do eng":
                result = databaseHandler.getEnglishWord(enterField.getText());
                break;
            case "do cz":
                result = databaseHandler.getCzechWord(enterField.getText());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + chooseLanguage.getSelectionModel().getSelectedItem());
        }
        while (result.next()) {
            outputField.setText(result.getString(1));
        }
    }

    @FXML
    void add(ActionEvent event) throws IOException {
        Parent second = FXMLLoader.load(getClass().getResource("../sample/second/sample.fxml"));
        Scene scene2 = new Scene(second);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseHandler = new DatabaseHandler();
        ObservableList<String> operation = FXCollections.observableArrayList("do eng", "do cz");
        chooseLanguage.setItems(operation);
        chooseLanguage.getSelectionModel().selectFirst();
    }
}
