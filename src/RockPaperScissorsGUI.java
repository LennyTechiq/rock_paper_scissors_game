import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class RockPaperScissorsGUI {
    private static final String ROCK = "Rock";
    private static final String PAPER = "Paper";
    private static final String SCISSORS = "Scissors";

    private JFrame frame;
    private JLabel backgroundLabel;
    private JLabel resultLabel;
    private JLabel conditionLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new RockPaperScissorsGUI().initialize();
        });
    }

    private void initialize() {
        frame = new JFrame("Rock Paper Scissors");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the background image
        URL imageURL = getClass().getResource("/background.jpg");
        ImageIcon backgroundImage = new ImageIcon(imageURL);
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        // Set up the result label
        resultLabel = new JLabel("<html><div style='text-align: center; color: #FFFFF0;'>Welcome to Rock, Paper, Scissors!</div></html>");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Set up the condition label
        conditionLabel = new JLabel("<html><div style='text-align: center; color: #FFFFF0;'>Game Conditions:</div>"
                + "<br/><div style='text-align: center;'>Rock beats Scissors, Scissors beats Paper, Paper beats Rock</div></html>");
        conditionLabel.setHorizontalAlignment(JLabel.CENTER);

        // Set up the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);

        JButton rockButton = createStyledButton("Rock", new Color(50, 150, 200)); // Example color
        JButton paperButton = createStyledButton("Paper", new Color(150, 50, 200)); // Example color
        JButton scissorsButton = createStyledButton("Scissors", new Color(200, 50, 150)); // Example color
        JButton exitButton = createStyledButton("Exit", new Color(100, 100, 100)); // Example color

        rockButton.addActionListener(e -> playGame(ROCK));
        paperButton.addActionListener(e -> playGame(PAPER));
        scissorsButton.addActionListener(e -> playGame(SCISSORS));
        exitButton.addActionListener(e -> exitGame());

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(exitButton);

        // Add components to the frame
        backgroundLabel.add(resultLabel, BorderLayout.CENTER);
        backgroundLabel.add(conditionLabel, BorderLayout.SOUTH);
        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);
        frame.setContentPane(backgroundLabel);

        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(90, 40));
        button.setBackground(bgColor); // Background color
        button.setForeground(Color.BLACK); // Text color
        button.setBorder(BorderFactory.createLineBorder(bgColor.darker(), 2)); // Border color and thickness
        button.setFocusPainted(false); // Remove the border around the text when clicked
        return button;
    }

    private void playGame(String playerMove) {
        String[] rps = {ROCK, PAPER, SCISSORS};
        String computerMove = rps[new Random().nextInt(rps.length)];

        String result;
        if (playerMove.equals(computerMove)) {
            result = "It's a tie!";
        } else if ((playerMove.equals(ROCK) && computerMove.equals(SCISSORS))
                || (playerMove.equals(PAPER) && computerMove.equals(ROCK))
                || (playerMove.equals(SCISSORS) && computerMove.equals(PAPER))) {
            result = "You win!";
        } else {
            result = "You lose!";
        }

        resultLabel.setText("<html><div style='text-align: center; color: #FFFFF0;'>Computer played: " + computerMove + ". " + result + "</div></html>");
    }

    private void exitGame() {
        int option = JOptionPane.showConfirmDialog(frame, "Do you really want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            frame.dispose();
        }
    }

    private Color getContrastColor(Color color) {
        double luminance = 0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue();
        return luminance > 128 ? Color.BLACK : Color.WHITE;
    }
}
