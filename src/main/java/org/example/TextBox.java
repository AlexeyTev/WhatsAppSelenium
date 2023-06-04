package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextBox extends JPanel {

    private String phoneNumber;
    private String textMessage;
    private Window window;

    public TextBox(Window window) {
        this.window = window;
        this.setLayout(null);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Phone number and message"),
                BorderFactory.createEmptyBorder(10, 5, 2, 5)));
        JLabel message = new JLabel("Message: ");
        message.setBounds(20, 20, 120, 15);
        message.setVisible(true);
        this.add(message);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 50, 150, 100);
        this.add(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setVisible(true);

        JLabel phoneNumberLable = new JLabel("Phone Number: ");
        phoneNumberLable.setBounds(20, 165, 120, 15);
        phoneNumberLable.setVisible(true);
        this.add(phoneNumberLable);

        JTextField phoneNumberTF = new JTextField();
        phoneNumberTF.setBounds(20, 190, 150, 20);
        phoneNumberTF.setVisible(true);
        this.add(phoneNumberTF);

        phoneNumberTF.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try {
                    if (phoneNumberTF.getText().startsWith("05") && phoneNumberTF.getText().length() == 10) {
                        String temp = "9725" + phoneNumberTF.getText().substring(2);
                        phoneNumberTF.setText(temp);
                    }
                }catch (NumberFormatException exception) {
                    phoneNumberTF.setText("");
                }

                }

        });

        JButton sendMessage = new JButton("Send Message");
        sendMessage.setBounds(40, 240, 120, 30);
        this.add(sendMessage);

        sendMessage.addActionListener(e -> {

            if (validPhone(phoneNumberTF.getText())) {
                if (!textArea.getText().isEmpty()) {
                    System.out.println("phoneNumber : " + phoneNumberTF.getText());
                    System.out.println("message : " + textArea.getText());
                    this.phoneNumber = phoneNumberTF.getText();
                    this.textMessage = textArea.getText();
                    this.window.openConversation(this.phoneNumber, this.textMessage);


                } else {
                    JOptionPane.showMessageDialog(null, "You need to enter text ", "Wrong Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "The number you entered is incorrect ", "Wrong Phone",
                        JOptionPane.INFORMATION_MESSAGE);
                phoneNumberTF.setText("");
            }
        });
    }

    public boolean validPhone(String phoneNumber) {
        boolean check = false;
        long length = phoneNumber.length();
        if (length == 12 && phoneNumber.startsWith("9725")) {
            check=true;
        }
        if (length == 11 && phoneNumber.startsWith("05") && phoneNumber.charAt(3) == '-') {
            check=true;
        }
        if (length == 10 && phoneNumber.startsWith("05")) {
            check=true;
        }
        return check;
    }



    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }

}
