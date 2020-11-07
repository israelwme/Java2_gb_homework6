import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Server(int port, JTextArea charArea) {
        try {
            System.out.println("Server is starting up...");
            server = new ServerSocket(port);

            new Thread(() -> {
                try {
                    System.out.println("Server is waiting for a connection...");
                    socket = server.accept();
                    System.out.println("Client connected: " + socket);

                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());

                    while (!socket.isInputShutdown()) {
                        try {
                            String message = in.readUTF();
                            out.writeUTF(message);
                            charArea.append("[Server] " + message + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
