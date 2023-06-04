package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Window extends JFrame {

    private Program program;
    private TextBox textBox;
    private ChromeDriver chromeDriver;
    private WebElement search = null;


    public Window() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(Constants.APP_WIDTH, Constants.APP_HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("WhatsappBOT");
        this.setVisible(true);


        createProgram();
    }



    public void createProgram() {
        program = new Program(this);
        this.add(program);
        program.setBounds(0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        program.setVisible(true);

    }



    public void createTextBox() {
        this.textBox = new TextBox(this);
        this.add(textBox);
        this.textBox.setBounds(535, 160, 200, 300);
        this.textBox.requestFocus();
        this.textBox.setVisible(true);

    }

    public void openConversation(String phoneNumber, String textMessage) {

        this.chromeDriver.get(Constants.WHATSAPP_CONV_LINK + phoneNumber);
        try {
            Thread.sleep(Constants.OPEN_CONV_THREAD_SLEEP_IN_MILLIS);
        } catch (Exception f) {
        }

        WebElement sendMessage1 = null;
        WebElement sendMessage2 = null;
        try {
            Thread.sleep(Constants.TENTH_OF_SECOND_IN_MILLIS);
            sendMessage1 = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p"));
        } catch (Exception e) {

        }
        try {
            Thread.sleep(Constants.TENTH_OF_SECOND_IN_MILLIS);
            sendMessage2 = chromeDriver.findElement(By.xpath("//*[@id=\"pane-side\"]/div[1]/div/span"));
        } catch (Exception e) {

        }
        if (sendMessage2 != null) {
            JOptionPane.showMessageDialog(null, "this number is not at contacts", "Message", JOptionPane.INFORMATION_MESSAGE);
            WebElement temp = chromeDriver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div/span/button/span"));
            temp.click();
        } else {
            if (sendMessage1 != null) {
                sendMessage1.sendKeys(textMessage);
                sendMessage1.sendKeys(Keys.ENTER);
                System.out.println("first try");
                checkSentMessage();
                System.out.println("second try");
                try {
                    Thread.sleep(Constants.TENTH_OF_SECOND_IN_MILLIS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void checkSentMessage() {
        new Thread(() -> {
            WebElement checkV = chromeDriver.findElement(By.cssSelector("span[data-testid='msg-dblcheck']"));
            String status = checkV.getAttribute("aria-label");
            System.out.println("Good luck");
            if (status.contains("נשלחה")) {
                JOptionPane.showMessageDialog(null, "The message was successfully sent", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            this.program.addStatusMessage(status);
            System.out.println(status);
        }).start();
    }

    public void openChrome() {
        System.setProperty("webdriver.openqa.driver", "chrome/chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.get(Constants.WHATSAPP_LINK);
        chromeDriver.manage().window().maximize();
        WebElement searchBox;
        while (true) {
            try {
                searchBox = chromeDriver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div/div[2]/div/div[1]/p"));
                if (searchBox != null) {
                    break;
                }
            } catch (Exception e) {
            }
        }
    }

    public void aboutWindow() {
        JFrame aboutWindow = new JFrame("About");
        aboutWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aboutWindow.setLayout(new FlowLayout());
        JTextArea textArea = new JTextArea(Constants.ABOUT_TEXT);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aboutWindow.dispose();
            }
        });

        aboutWindow.add(scrollPane);
        aboutWindow.add(closeButton);
        aboutWindow.pack();
        aboutWindow.setLocationRelativeTo(null);
        aboutWindow.setVisible(true);
    }


}
