import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {

    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    ServerSocket server = null;
    Socket socket = null;

    public Server() {

          start();

    }

    private void start() {

        try {

            // Сервер слушает порт 8189
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем клиента...");

            // Ждём подключение 1 клиента
            socket = server.accept();
            System.out.println("Клиент подключился: " + socket.getInetAddress());

            // Открываем потоки ввода-вывода
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            // Отпочкуем поток для обработки входящих сообщений
            // Будем слушать входящие сообщения и печатать их в консоль
            Thread fromClient = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!socket.isClosed()) {

                            String str = in.readUTF();

                            if(str.equals("/end")) {

                                System.out.println("Client disconnected");
                                break;
                            }

                            if(!str.isEmpty()) System.out.println(ANSI_PURPLE + str + ANSI_RESET);

                        }
                    } catch (IOException e) {

                        System.out.println("Сonnection terminated");

                    } finally {

                        try {

                            socket.close();

                        } catch (IOException e) {

                            System.out.println("Client disconnected unexpectedly \n");
                            e.printStackTrace();

                        }
                    }
                }
            });
            fromClient.start();

            // Будем слушать свою консоль и отсылать сообщения клиенту
            while(!socket.isClosed()) {

                String line =  scanner.nextLine();

                if(line.equals("/end")) {

                    out.writeUTF("The server has disconnected you");
                    fromClient.interrupt();
                    socket.close();
                    break;
                }

                if(!line.isEmpty()) out.writeUTF(line);

            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                server.close();

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
    }
}
