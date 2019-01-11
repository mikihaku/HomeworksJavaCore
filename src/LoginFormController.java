import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
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

        if(fioField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Ошибка в форме",
                    "Мужчина, у вас неразборчиво, ФИО перепишите!");
            return;
        }
        else if(snilsField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Ошибка в форме",
                    "Как не знаете какой СНИЛС? А кто знает?");
            return;
        }
        else if(innField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, window, "Ошибка в форме",
                    "Без ИНН соединение не производится!");
            return;
        }
        else {

                Parent chat = FXMLLoader.load(getClass().getResource("chat_form.fxml"));
                stage.setTitle("Чат с техподдержкой");
                stage.setScene(new Scene(chat, 600, 800));
                stage.show();



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