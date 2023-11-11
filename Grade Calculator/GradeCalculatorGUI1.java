import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI1 extends JFrame {

    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JLabel resultLabel;

    private static final String[] SUBJECT_NAMES = {"Physics", "Math", "Science", "English","History"};

    public GradeCalculatorGUI1() {
        setTitle("Grade Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 240, 255)); // Light blue background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        subjectFields = new JTextField[SUBJECT_NAMES.length];

        for (int i = 0; i < SUBJECT_NAMES.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            mainPanel.add(new JLabel(SUBJECT_NAMES[i] + ":"), gbc);

            gbc.gridx = 1;
            subjectFields[i] = new JTextField(10);
            mainPanel.add(subjectFields[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = SUBJECT_NAMES.length;
        gbc.gridwidth = 2;
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayResults();
            }
        });
        mainPanel.add(calculateButton, gbc);

        gbc.gridy = SUBJECT_NAMES.length + 1;
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setForeground(new Color(25, 25, 112));
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(resultLabel, gbc);

        // Set the background color for the content pane (optional)
        mainPanel.setBackground(new Color(240, 240, 255));

        // Set the content pane of the JFrame
        setContentPane(mainPanel);
    }

    private void calculateAndDisplayResults() {
        try {
            double[] marks = new double[subjectFields.length];
            for (int i = 0; i < subjectFields.length; i++) {
                marks[i] = Double.parseDouble(subjectFields[i].getText());
            }

            // Calculate Total Marks
            double totalMarks = 0;
            for (double mark : marks) {
                totalMarks += mark;
            }

            // Calculate Average Percentage
            double averagePercentage = totalMarks / subjectFields.length;

            // Grade Calculation
            String grade = calculateGrade(averagePercentage);

            // Display Results
            String resultMessage = String.format(
                    "Total Marks: %.2f\nAverage Percentage: %.2f%%\nGrade: %s",
                    totalMarks, averagePercentage, grade
            );
            resultLabel.setText(resultMessage);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numerical values for marks.");
        }
    }

    private String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculatorGUI1().setVisible(true);
            }
        });
    }
}
