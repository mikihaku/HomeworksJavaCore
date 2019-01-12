package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatController {

    @FXML
    TextField inputField;

    @FXML
    Button btn;

    @FXML
    GridPane base;

    @FXML
    ScrollPane scroll;

    @FXML
    VBox vbox;

    String fio = "";
    String snils = "";
    String inn = "";

    int botMsgLevel = 0;

    private int msgIndex = 1;

    public void sendMsg() throws Exception {

        if(!inputField.getText().isEmpty()) {

            addMsg((byte) 1, inputField.getText());
            inputField.clear();

            //Thread.sleep(2 * 1000);
            addBotMsg();

        }

        // scroll to bottom
        Thread.sleep(1 * 300);
        Platform.runLater(() -> scroll.setVvalue(1.0));

        inputField.requestFocus();

    }

    public void initData(String fio, String snils, String inn) {

        this.fio = fio;
        this.snils = snils;
        this.inn = inn;

        addMsg((byte) 0, "Приветствуем Вас, " + fio + ". Добро пожаловать в инновационный чат-бот Почты России.");
        addMsg((byte) 0, "Сочетание нанотехнологий, искусственного интеллекта и блокчейна позволит " +
                              "Вам получать мгновенное обслуживание не выходя из дома.");
        addMsg((byte) 0, "Введите запрос чтобы начать :)");

    }

    private void addMsg(byte side, String msg) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");

        int    column   = 0;
        String textClass = "";
        String timeClass = "";
        TextAlignment alignment;

        if(side == 0) {

            textClass = "remote";
            timeClass = "remote-time";
            alignment = TextAlignment.LEFT;
            column    = 0;

        } else {

            textClass = "local";
            timeClass = "local-time";
            alignment = TextAlignment.RIGHT;
            column    = 1;

        }

        Text text = new Text(msg + "\n");
             text.getStyleClass().add(textClass);

        Text time = new Text("~ " + sdf.format(new Date()));
             time.getStyleClass().add(timeClass);

        TextFlow textFlowPane = new TextFlow();
                 textFlowPane.setTextAlignment(alignment);
                 textFlowPane.setLineSpacing(5.0);

                 textFlowPane.getChildren().addAll(text, time);

        base.add(textFlowPane, column, msgIndex);

        msgIndex += 1; // Increment message counter

    }

    private void addBotMsg() {

        if(botMsgLevel == 0) {

            addMsg((byte) 0, "В данный момент все чат-боты заняты, пожалуйста, оставайтесь на линии...");
            botMsgLevel += 1;

        } else if(botMsgLevel == 1) {

            addMsg((byte) 0, "Перерыв на обед. 30 минут.");
            botMsgLevel += 1;

        } else if(botMsgLevel == 2) {

            addMsg((byte) 0, "Вы что не видите?!!!!111 У нас обед! Бот - тоже человек!");
            botMsgLevel += 1;

        } else {

            addMsg((byte) 0, "Добрый день! К сожалению, чат-боты в данный момент не активны.");
            addMsg((byte) 0, "Часы работы по будням: с 9:45 до 13:15, перерыв с 13:16 до 14:45, далее с 14:46 до 16:00.");
            addMsg((byte) 0, "По выходным и праздникам с 12:00 до 15:00 без перерыва. До свидания!");

        }



    }
}
