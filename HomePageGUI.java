import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HomePageGUI {

    // private JTextField usernameField; // Make these instance variables
    // private JPasswordField passwordField;

    public HomePageGUI() {
        // Create the main JFrame
        JFrame f = new JFrame("");
        JPanel p = new JPanel();
        JTextField number_of_questions = new JTextField();
        JLabel numtext = new JLabel("number of question.");
        numtext.setBounds(152, 100, 150, 20);
        number_of_questions.setBounds(100, 100, 50, 20);

        p.setBounds(0, 0, 1400, 50);
        p.setBackground(Color.blue);
        f.add(number_of_questions);
        f.add(numtext);
        f.add(number_of_questions);
        f.add(p);
        f.setSize(1000,1000);
        f.setBackground(Color.white);
        f.setSize(1200, 1200);
        f.setLayout(null);
        f.setVisible(true);

        }

    // Method to display the login dialog
    // private void showLoginDialog() {
    //     JFrame loginFrame = new JFrame("Login");
    //     JPanel loginPanel = new JPanel();
    //     usernameField = new JTextField(20);
    //     passwordField = new JPasswordField(20);
    //     JButton loginButton = new JButton("Login");

    //     loginPanel.add(new JLabel("Username:"));
    //     loginPanel.add(usernameField);
    //     loginPanel.add(new JLabel("Password:"));
    //     loginPanel.add(passwordField);
    //     loginPanel.add(loginButton);

    //     loginFrame.add(loginPanel);
    //     loginFrame.pack();
    //     loginFrame.setLocationRelativeTo(null); // Center the login dialog on the screen
    //     loginFrame.setVisible(true);

    //     loginButton.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             checkCredentials();
    //         }
    //     });

    // }

    // private void checkCredentials() {
    //     String enteredUsername = usernameField.getText();
    //     char[] enteredPassword = passwordField.getPassword();
    //     String enteredPasswordStr = new String(enteredPassword);
    //     boolean authenticated = false;

    //     try (BufferedReader reader = new BufferedReader(new FileReader("auth.txt"))) {
    //         String line;
    //         while ((line = reader.readLine()) != null) {
    //             String[] parts = line.split(",");
    //             if (parts.length == 2 && parts[0].equals(enteredUsername) && parts[1].equals(enteredPasswordStr)) {
    //                 authenticated = true;
    //                 break;
    //             }
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     if (authenticated) {
    //         // Call another function or display a message for successful login
    //         handleSuccessfulLogin();
    //     } else {
    //         // Display an error message for incorrect username or password
    //         JOptionPane.showMessageDialog(null, "Wrong username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
    //     }
    // }

    // // Method to handle successful login
    private void handleSuccessfulLogin() {
        // Jframe f = new JFrame()
        JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePageGUI();
            }
        });
    }
}
