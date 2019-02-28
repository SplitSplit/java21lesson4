package ru.ponomaryov.se;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NetChat extends JFrame {

    private String chatBuffer = "";

    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();

    private JButton sendButton = new JButton("Send");
    private JEditorPane textArea = new JEditorPane();
    private JScrollPane scrollPanel = new JScrollPane(textArea);

    private JTextField textField = new JTextField();

    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");
    private JMenu helpMenu = new JMenu("Help");
    private JMenuItem aboutMenuItem = new JMenuItem("About");

    PrintWriter printWrite = new PrintWriter(new FileWriter("chat.txt"));

    NetChat() throws IOException {
        super("NetChat");
        setSize(360, 640);
        setMinimumSize(new Dimension(360, 640));
        inintNetChatStyle();
        textArea.setEditable(false);
        textArea.setContentType("text/html");
        textArea.setEditable(false);
        panel1.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        sendButton.addActionListener(e -> sendMessage());
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) sendMessage();
            }
        });
        panel1.add(scrollPanel);
        panel2.add(textField);
        panel2.add(sendButton, BorderLayout.EAST);

        add(panel1);
        add("South", panel2);

        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        fileMenu.add(exitMenuItem);
        helpMenu.add(aboutMenuItem);
        exitMenuItem.addActionListener(e -> System.exit(0));
        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Net Chat", "About", JOptionPane.INFORMATION_MESSAGE));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void sendMessage() {
        String message = textField.getText();
        chatBuffer += "<b class=\"time1\">" + getTime() + ":</b> " + message + "<br>";
        textArea.setText(chatBuffer);
        printWrite.append(getTime()).append(": ").append(message).append("\n");
        printWrite.flush();
        textField.setText("");
        textField.grabFocus();
    }

    private String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    private void inintNetChatStyle() {
        chatBuffer += "<style>" +
                ".time1 {\n" +
                "  -webkit-box-sizing: content-box;\n" +
                "  -moz-box-sizing: content-box;\n" +
                "  box-sizing: content-box;\n" +
                "  width: 95px;\n" +
                "  padding: 5px;\n" +
                "  overflow: hidden;\n" +
                "  border: 1px solid;\n" +
                "  -webkit-border-radius: 10px;\n" +
                "  border-radius: 10px;\n" +
                "  font: normal 12px/1 \"Times New Roman\", Times, serif;\n" +
                "  color: rgba(0,0,0,1);\n" +
                "  text-align: center;\n" +
                "  -o-text-overflow: ellipsis;\n" +
                "  text-overflow: ellipsis;\n" +
                "  background: #feffd8;\n" +
                "  -webkit-box-shadow: 1px 1px 1px 0 rgba(0,0,0,0.3) ;\n" +
                "  box-shadow: 1px 1px 1px 0 rgba(0,0,0,0.3) ;\n" +
                "  text-shadow: 1px 1px 1px rgba(0,0,0,0.2) ;\n" +
                "}" +
                "</style>";
    }
}
