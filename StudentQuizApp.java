import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentQuizApp extends JFrame {

    // Lists to store quiz data
    private List<String> questions; // Store the questions
    private List<List<String>> choices; // Store choices for each question
    private List<Integer> answerIndices; // Store the index of the correct answer for each question
    private List<Integer> userAnswers; // Store the user's selected answers
    private List<Integer> userScores; // Store the user's scores

    private int currentQuestionIndex;

    private JTextArea questionArea;
    private List<JRadioButton> choiceButtons;
    private JButton nextButton;
    private JButton previousButton;
    private JButton submitButton;

    public StudentQuizApp() {
        this.questions = new ArrayList<>();
        this.choices = new ArrayList<>();
        this.answerIndices = new ArrayList<>();
        this.currentQuestionIndex = 0;
        this.userAnswers = new ArrayList<>();
        this.userScores = new ArrayList<>();

        try {
            // Read quiz questions from a file
            BufferedReader br = new BufferedReader(new FileReader("./files/quiz_questions.txt"));
            String line;
            List<String> questionChoices = new ArrayList<>();
            int answerIndex = -1;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Question")) {
                    if (!questionChoices.isEmpty()) {
                        choices.add(questionChoices);
                        answerIndices.add(answerIndex);
                    }
                    questionChoices = new ArrayList<>();
                    answerIndex = -1;
                    questions.add(line);
                } else if (line.startsWith("Choice")) {
                    questionChoices.add(line);
                } else if (line.startsWith("Answer")) {
                    answerIndex = Integer.parseInt(line.substring("Answer: ".length())) - 1;
                }
            }

            if (!questionChoices.isEmpty()) {
                choices.add(questionChoices);
                answerIndices.add(answerIndex);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Quiz App");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionArea = new JTextArea(10, 50);
        questionArea.setEditable(false);
        questionArea.setWrapStyleWord(true);
        questionArea.setLineWrap(true);
        questionArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(questionArea), BorderLayout.CENTER);

        JPanel choicesPanel = new JPanel();
        choicesPanel.setLayout(new GridLayout(4, 1));
        choiceButtons = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            JRadioButton choice = new JRadioButton();
            choiceButtons.add(choice);
            choicesPanel.add(choice);
        }
        add(choicesPanel, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        submitButton = new JButton("Submit");

        // ActionListener for the "Next" button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex >= questions.size()) {
                    currentQuestionIndex = questions.size() - 1;
                }
                displayQuestion(questions.get(currentQuestionIndex));
            }
        });

        // ActionListener for the "Previous" button
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentQuestionIndex--;
                if (currentQuestionIndex < 0) {
                    currentQuestionIndex = 0;
                }
                displayQuestion(questions.get(currentQuestionIndex));
            }
        });

        // ActionListener for the "Submit" button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                displayScoreAndClose();
                setVisible(false);
            }
        });

        previousButton.setEnabled(false);
        submitButton.setEnabled(false);

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        displayQuestion(questions.get(currentQuestionIndex));
    }

    private void displayQuestion(String question) {
        questionArea.setText(question);
        List<String> questionChoices = choices.get(currentQuestionIndex);
        for (int i = 0; i < questionChoices.size(); i++) {
            JRadioButton choice = choiceButtons.get(i);
            choice.setText(questionChoices.get(i));
            choice.setSelected(false);
        }

        previousButton.setEnabled(currentQuestionIndex > 0);
        nextButton.setEnabled(currentQuestionIndex < questions.size() - 1);
        submitButton.setEnabled(currentQuestionIndex == questions.size() - 1);
    }

    private void checkAnswer() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < questions.size()) {
            int selectedChoiceIndex = -1;

            for (int i = 0; i < choiceButtons.size(); i++) {
                JRadioButton choiceButton = choiceButtons.get(i);
                if (choiceButton.isSelected()) {
                    selectedChoiceIndex = i;
                    break;
                }
            }

            userAnswers.add(selectedChoiceIndex);
            int score = selectedChoiceIndex == answerIndices.get(currentQuestionIndex) ? 1 : 0;
            userScores.add(score);
        }
    }

    private void displayScoreAndClose() {
        String userName = JOptionPane.showInputDialog(this, "Enter your name:");

        if (userName != null && !userName.isEmpty()) {
            try {
                // Write user's score to a file
                BufferedWriter writer = new BufferedWriter(new FileWriter("./files/mark_list.txt", true));

                writer.write(userName + ": " + calculateTotalScore());
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder result = new StringBuilder("Quiz Result:\n");
        for (int i = 0; i < questions.size(); i++) {
            result.append("Question ").append(i + 1).append(": ");
            if (userScores.get(i) == 1) {
                result.append("Correct\n");
            } else {
                result.append("Incorrect\n");
            }
        }
        result.append("Your Total Score: ").append(calculateTotalScore());

        JOptionPane.showMessageDialog(this, result.toString(), "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private int calculateTotalScore() {
        int totalScore = 0;
        for (int score : userScores) {
            totalScore += score;
        }
        return totalScore;
    }
}
