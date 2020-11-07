import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI extends JFrame {
    private Client client;
    private Server server;

    public GUI(String endHost, int endPort, int localPort) {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(new Rectangle(0, 0, 300, 300));
        setLayout(new BorderLayout());

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(chatArea, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());

        JTextField inputField = new JTextField();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String message = inputField.getText();
            if (!message.trim().isBlank()) {
                try {
                    client.getOut().writeUTF(message);
                    inputField.setText("");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        bottom.add(inputField, BorderLayout.CENTER);
        bottom.add(submitButton, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);

        server = new Server(localPort, chatArea);
        client = new Client(endHost, endPort, chatArea);

        setVisible(true);
    }
}
