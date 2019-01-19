import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;
    private Socket socket = null;

    public Client() {

        start();

    }

    private void start() {

        try {

            // Подключаемся к серверу
            try {
                socket = new Socket(IP_ADDRESS, PORT);
            } catch (ConnectException e) {
                System.out.println("Сервер недоступен");
                System.exit(1);
            }
            System.out.println("Подключились к серверу " + socket.getInetAddress());

            // Открываем потоки ввода-вывода
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            // Отпочкуем поток для обработки входящих сообщений
            // Будем слушать входящие сообщения и печатать их в консоль
            Thread fromServer = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {

                            String str = in.readUTF();

                            if(str.equals("/end")) {

                                System.out.println("Server disconnected");
                                break;
                            }

                            if(!str.isEmpty()) System.out.println(ANSI_PURPLE + str + ANSI_RESET);

                        }
                    } catch (IOException e) {

                        System.out.println("Connection closed. Can't send anymore messages");

                    } finally {

                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            fromServer.start();

            // Будем слушать свою консоль и отсылать сообщения серверу
            while(!socket.isClosed()) {

                String line =  scanner.nextLine();

                if(line.equals("/end")) {

                    out.writeUTF("/end");
                    fromServer.interrupt();
                    socket.close();
                    break;
                }

                if(!line.isEmpty()) out.writeUTF(line);

            }

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
