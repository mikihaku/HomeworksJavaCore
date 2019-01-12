import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent login = FXMLLoader.load(getClass().getResource("/fxml/login_form.fxml"));
        //Parent login = FXMLLoader.load(getClass().getResource("/chat_form.fxml"));
        primaryStage.setTitle("Требуется аутентификация");
        primaryStage.setScene(new Scene(login, 600, 800));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
