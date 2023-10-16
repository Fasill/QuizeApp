import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class LoginManager {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private TeacherHome teacherHome;

    public LoginManager() {
        teacherHome = new TeacherHome();
    }

    public void showLoginDialog() {
        // Create a JFrame for the login dialog
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

        // Add the login panel to the login frame
        loginFrame.add(loginPanel);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        // Add an ActionListener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the login frame and check credentials
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

        try (BufferedReader reader = new BufferedReader(new FileReader("./files/auth.txt"))) {
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
            // Call the teacher's home page upon successful login
            showTeacherHomePage();
        } else {
            // Display an error message for incorrect username or password
            JOptionPane.showMessageDialog(null, "Wrong username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            showLoginDialog();
        }
    }

    private void showTeacherHomePage() {
        // Navigate to the teacher's home page upon a successful login
        teacherHome.showTeacherHome();
    }
}
