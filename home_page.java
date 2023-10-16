import javax.swing.*;
import java.awt.*; // Import font and color classes
import java.awt.event.*; // Import the ActionListener interface

public class home_page {

    public home_page() {
        JFrame f = new JFrame();
        
        // Create "Take Quiz" and "Test & Grades" buttons
        JButton takeQuiz = new JButton("Take Quiz");
        JButton testAndGrades = new JButton("Test & Grades");
        
        // Create header and paragraphs labels
        JLabel header = new JLabel("Welcome to the Ultimate Quiz Experience");
        JLabel paraph1 = new JLabel("where learning, excitement, and fun come together in a single,");
        JLabel paraph2 = new JLabel("captivating journey of knowledge and discovery.");
        
        // Create hero and navbar panels
        JPanel hero = new JPanel();
        JPanel navbar = new JPanel();
        
        // Set the bounds and styles for the navbar
        navbar.setBounds(0, 0, 1000, 60);
        navbar.setBackground(new Color(138, 117, 41));
        
        // Set the font for the header label
        Font font = new Font("Arial", Font.PLAIN, 26);
        header.setFont(font);
        
        // Add labels to the hero panel
        hero.add(header);
        hero.add(paraph1);
        hero.add(paraph2);
        
        // Set the bounds for the hero panel
        hero.setBounds(200, 150, 600, 400);
        
        // Set the bounds and styles for the "Take Quiz" button
        takeQuiz.setBounds(300, 300, 150, 50);
        takeQuiz.setBackground(new Color(138, 117, 41));
        takeQuiz.setBorder(BorderFactory.createLineBorder(new Color(138, 117, 41), 2));
        takeQuiz.setForeground(Color.white);
        
        // Set the bounds and styles for the "Test & Grades" button
        testAndGrades.setBounds(460, 300, 150, 50);
        testAndGrades.setBackground(Color.WHITE);
        testAndGrades.setBorder(BorderFactory.createLineBorder(new Color(138, 117, 41), 2));
        
        // Add components to the JFrame
        f.add(testAndGrades);
        f.add(hero);
        f.add(takeQuiz);
        f.add(navbar);
        f.setSize(1000, 1000);
        f.setLayout(null);
        f.setVisible(true);

        // Add ActionListener for the "Test & Grades" button
        testAndGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action when the "Test & Grades" button is clicked
                LoginManager loginManager = new LoginManager();
                loginManager.showLoginDialog();
                f.setVisible(false);
            }
        });

        // Add ActionListener for the "Take Quiz" button
        takeQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action when the "Take Quiz" button is clicked
                StudentQuizApp studentApp = new StudentQuizApp();
                studentApp.setVisible(true);
                f.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new home_page();
            }
        });
    }
}
