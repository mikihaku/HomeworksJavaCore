package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginFormController {
    @FXML
    private TextField fioField;

    @FXML
    private TextField snilsField;

    @FXML
    private TextField innField;

    @FXML
    private Button submitButton;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws Exception {

        Scene scene = submitButton.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;

        if(fioField.getText().isEmpty() || !fioField.getText().matches("^([А-Я][а-я]+)\\s([А-Я][а-я]+)\\s([А-Я][а-я]+)$")) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Ошибка в форме",
                    "Мужчина, у вас неразборчиво, ФИО перепишите!");
            return;
        }
        else if(snilsField.getText().isEmpty() || !snilsField.getText().matches("^[\\d]{3}-[\\d]{3}-[\\d]{3}\\s[\\d]{2}$")) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Ошибка в форме",
                    "Как не знаете какой СНИЛС? А кто знает?");
            return;
        }
        else if(innField.getText().isEmpty() || !innField.getText().matches("^[\\d]{12}$")) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Ошибка в форме",
                    "Неверный ИНН, посылку не отдадим!");
            return;
        }
        else {

                FXMLLoader chat = new FXMLLoader(getClass().getResource("/fxml/chat_form.fxml"));
                stage.setTitle("Чат с техподдержкой");
                stage.setScene(new Scene(chat.load(), 600, 800));

                ChatController controller = chat.<ChatController>getController();

                stage.show();

                controller.initData(fioField.getText(), snilsField.getText(), innField.getText());
        }
    }
}


class AlertHelper {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}