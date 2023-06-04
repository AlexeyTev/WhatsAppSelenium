package org.example;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Program extends JPanel {

    private Image background;
    private JButton loginButton;
    private JButton aboutButton;
    private JLabel success;
    private Window window;

    public Program(Window window) {
        this.window = window;
        this.setLayout(null);
        addBackgroundPicture();
        addByLine();
        loginProcess();
        aboutButton();
        repaint();
    }

    public void addBackgroundPicture() {
        try {
            background = ImageIO.read(new File(Constants.BACKGROUND_IMG_PATH));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addByLine() {
        success = new JLabel();
        success.setText("");
        success.setForeground(Color.GREEN);
        success.setBounds(575, 460, 800, 40);
        success.setVisible(true);
        this.add(success);
    }

    public void loginProcess() {
        this.loginButton = new JButton("Log on");
        this.loginButton.setBounds(Constants.LOGIN_PROCESS_BUTTON_X, Constants.LOGIN_PROCESS_BUTTON_Y, Constants.LOGIN_PROCESS_BUTTON_WIDTH, Constants.LOGIN_PROCESS_BUTTON_HEIGHT);
        this.loginButton.setBackground(Constants.BUTTONS_COLOR);
        this.loginButton.setBorder(BorderFactory.createLineBorder(Constants.BUTTONS_BORDER_COLOR));
        this.loginButton.setVisible(true);
        this.add(loginButton);
        this.loginButton.addActionListener(e -> {
            window.openChrome();
            addSuccessLogin();
            this.loginButton.setVisible(false);
        });
    }

    public void aboutButton() {
        this.aboutButton = new JButton("About");
        this.aboutButton.setBounds(Constants.LOGIN_PROCESS_BUTTON_X, Constants.LOGIN_PROCESS_BUTTON_Y + 50, Constants.LOGIN_PROCESS_BUTTON_WIDTH, Constants.LOGIN_PROCESS_BUTTON_HEIGHT);
        this.aboutButton.setBackground(Constants.BUTTONS_COLOR);
        this.aboutButton.setBorder(BorderFactory.createLineBorder(Constants.BUTTONS_BORDER_COLOR));
        this.aboutButton.setVisible(true);
        this.add(aboutButton);
        this.aboutButton.addActionListener(e -> {
            window.aboutWindow();
            this.aboutButton.setVisible(true);
        });
    }


    public void addSuccessLogin() {
        success.setText(Constants.LOGIN_SUCCEED_TEXT);
        this.window.createTextBox();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    public void addStatusMessage(String status) {
        JLabel statusMessage = new JLabel("Status Message");
        statusMessage.setBounds(Constants.STATUS_MASSAGE_BUTTON_X, Constants.STATUS_MASSAGE_BUTTON_Y, Constants.STATUS_MASSAGE_BUTTON_WIDTH, Constants.STATUS_MASSAGE_BUTTON_HEIGHT);
        statusMessage.setForeground(Color.WHITE);
        String v = "";
        statusMessage.setVisible(true);
        this.add(statusMessage);
        switch (status) {
            case "נשלחה" -> v = "v";
            case "נמסרה" -> v = "vv";
        }
        JLabel statusV = new JLabel(v);
        statusV.setForeground(Color.GRAY);
        statusV.setBounds(100, 80, 50, 30);
        if (status.equals("נקראה"))
            statusV.setForeground(Color.BLUE);
        statusV.setVisible(true);
        this.add(statusV);
        System.out.println(v);
    }
}

