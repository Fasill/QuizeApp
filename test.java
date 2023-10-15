import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class test{

    public test(){
        JFrame f = new JFrame();
        JButton takeQuiz = new JButton("take Quiz");
        JButton test = new JButton("Test & Grades");

        JLabel header = new JLabel("Welcome to the Ultimate Quiz Experience");
        JLabel paraph1 = new JLabel("where learning, excitement, and fun come together in a single,");
        JLabel paraph2= new JLabel("captivating journey of knowledge and discovery.");
        JPanel hero = new JPanel();
        Font font = new Font("Arial", Font.PLAIN, 26);
        JPanel navbar = new JPanel();
        navbar.setBounds(0, 0, 1000, 60);
        navbar.setBackground(new Color(138, 117, 41));
        header.setFont(font);
        hero.add(header);
        hero.add(paraph1);
        hero.add(paraph2);

        hero.setBounds(200,150,600,400);

        takeQuiz.setBounds(300, 300, 150, 50);
        takeQuiz.setBackground(new Color(138, 117, 41));
        takeQuiz.setBorder(BorderFactory.createLineBorder(new Color(138, 117, 41), 2));
        takeQuiz.setForeground(Color.white);

        test.setBounds(460, 300, 150, 50);
        test.setBackground(Color.WHITE); 
        test.setBorder(BorderFactory.createLineBorder(new Color(138, 117, 41), 2));
        test.setBounds(460, 300, 150, 50); 

        // hero.setBounds();
        f.add(test);
        f.add(hero);
        f.add(takeQuiz);
        f.add(navbar);
        f.setSize(1000, 1000);
        f.setLayout(null);
        f.setVisible(true);
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and display the LoginShow frame
                LoginshowLoginshow();
                        f.setVisible(false);

            }
        });
    }
  private JTextField usernameField; // Make these instance variables
    private JPasswordField passwordField;
    private void LoginshowLoginshow() {
        JFrame loginFrame = new JFrame("Login");
        JPanel loginPanel = new JPanel();
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        loginFrame.add(loginPanel);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null); // Center the login dialog on the screen
        loginFrame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(false);
                checkCredentials();
                
            }
        });

    }
     private void checkCredentials() {
        String enteredUsername = usernameField.getText();
        char[] enteredPassword = passwordField.getPassword();
        String enteredPasswordStr = new String(enteredPassword);
        boolean authenticated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("auth.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(enteredUsername) && parts[1].equals(enteredPasswordStr)) {
                    authenticated = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (authenticated) {
            // Call another function or display a message for successful login
            handleSuccessfulLogin();
        } else {
            // Display an error message for incorrect username or password
            JOptionPane.showMessageDialog(null, "Wrong username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                LoginshowLoginshow();

        }
    }
    private void handleSuccessfulLogin() {
        // Jframe f = new JFrame()
        
        JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new test();
            }
        });
       }

}