package sample.second;

import database.DatabaseHandler;
import interfaces.DatabaseHandlerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddWindow implements Initializable {
    DatabaseHandlerInterface databaseHandler;
    @FXML
    private TextField CzechField;

    @FXML
    private TextField EnglishField;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField getId;

    @FXML
    void add(ActionEvent event) {

        try {
            databaseHandler.addWord(CzechField.getText(), EnglishField.getText());
            statusLabel.setText("Slovo úspěšně přidáno");

        } catch (Exception ignored) {
            statusLabel.setText("Slovo už existuje");
        }
    }

    @FXML
    void delete(ActionEvent event) {
        databaseHandler.deleteWord(getId.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseHandler = new DatabaseHandler();
    }
}
