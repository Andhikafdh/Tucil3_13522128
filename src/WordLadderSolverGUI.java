import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

// Custom JPanel with background image
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class WordLadderSolverGUI extends JFrame {
    private JTextField startWordField, endWordField;
    private JComboBox<String> algorithmComboBox;
    private JTextArea resultArea;

    public WordLadderSolverGUI(Set<String> englishWords) {
        super("Word Ladder Solver ");

        // Load background image
        ImageIcon backgroundImageIcon = new ImageIcon("../doc/background.jpg");
        Image backgroundImage = backgroundImageIcon.getImage();

        // Initialize components
        JLabel titleLabel = new JLabel("Word Ladder Solver", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);

        // Assuming `container` is the parent container of `titleLabel`
        titleLabel.setLayout(new BoxLayout(titleLabel, BoxLayout.Y_AXIS));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel startWordLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Draw the image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
                // Set the text color
                g.setColor(Color.WHITE);
        
                // Set the font
                Font font = new Font("Arial", Font.BOLD, 15);
                g.setFont(font);
        
                // Calculate the width and height of the text
                FontMetrics metrics = g.getFontMetrics(font);
                int textWidth = metrics.stringWidth("Start Word:");
                int textHeight = metrics.getHeight();
        
                // Calculate the x and y coordinates where the text should be drawn
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + metrics.getAscent(); // The ascent is the distance from the baseline to the top of the text
        
                // Draw the text
                g.drawString("Start Word:", x, y);
            }
        };

        startWordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        startWordField = new JTextField(15);

        JLabel endWordLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Draw the image 
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
                // Set the text color
                g.setColor(Color.WHITE);
        
                // Set the font
                Font font = new Font("Arial", Font.BOLD, 15);
                g.setFont(font);
        
                // Calculate the width and height of the text
                FontMetrics metrics = g.getFontMetrics(font);
                int textWidth = metrics.stringWidth("End Word:");
                int textHeight = metrics.getHeight();
        
                // Calculate the x and y coordinates where the text should be drawn
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + metrics.getAscent(); // The ascent is the distance from the baseline to the top of the text
        
                // Draw the text
                g.drawString("End Word:", x, y);
            }
        };
        endWordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        endWordField = new JTextField(15);

        JLabel algorithmLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Draw the image 
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        
                // Set the text color
                g.setColor(Color.WHITE);
        
                // Set the font
                Font font = new Font("Arial", Font.BOLD, 15);
                g.setFont(font);
        
                // Calculate the width and height of the text
                FontMetrics metrics = g.getFontMetrics(font);
                int textWidth = metrics.stringWidth("Algorithm:");
                int textHeight = metrics.getHeight();
        
                // Calculate the x and y coordinates where the text should be drawn
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + metrics.getAscent(); // The ascent is the distance from the baseline to the top of the text
        
                // Draw the text
                g.drawString("Algorithm:", x, y);
            }
        };

        algorithmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        String[] algorithms = {"Uniform Cost Search", "Greedy Best First Search", "A*"};
        algorithmComboBox = new JComboBox<>(algorithms);
        algorithmComboBox.setBackground(Color.LIGHT_GRAY);
        algorithmComboBox.setForeground(Color.BLACK);

        JButton solveButton = new JButton("Solve");
        solveButton.setBackground(Color.BLACK);
        solveButton.setForeground(Color.WHITE);
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startWord = startWordField.getText().toLowerCase();
                String endWord = endWordField.getText().toLowerCase();
                String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                List<String> path = null;
                long startTime = System.currentTimeMillis();

                // Check if startWord and endWord have the same length
                if (startWord.length() != endWord.length()) {
                    resultArea.setText("Error! \nStart and end words must have the same length.");
                    
                    return; // Exit the method early
                }

                switch (selectedAlgorithm) {
                    case "Uniform Cost Search":
                        path = UCS.findPath(startWord, endWord, englishWords);
                        break;
                    case "Greedy Best First Search":
                        path = GBFS.findPath(startWord, endWord, englishWords);
                        break;
                    case "A*":
                        path = AStar.findPath(startWord, endWord, englishWords);
                        break;
                    default:
                        break;
                }

                long endTime = System.currentTimeMillis();

                // If you want to know the memory used, use this code
                // if (path != null) {
                //     // Calculate memory usage
                //     Runtime runtime = Runtime.getRuntime();
                //     long memoryUsedBytes = runtime.totalMemory() - runtime.freeMemory();
                //     String memoryUsedKB = String.format("%.2f", (double) memoryUsedBytes / 1024);
                
                //     resultArea.setText("Path found:\n" + String.join(" -> ", path) +
                //             "\nNumber of nodes visited: " + path.size() +
                //             "\nExecution time: " + (endTime - startTime) + " milliseconds" +
                //             "\nMemory used: " + memoryUsedKB + " kilobytes");
                // } else {
                //     resultArea.setText("No path found.");
                // }

                if (path != null) {
                    resultArea.setText("Path found:\n" + String.join(" -> ", path) +
                            "\nNumber of nodes visited: " + path.size() +
                            "\nExecution time: " + (endTime - startTime) + " milliseconds");
                } else {
                    resultArea.setText("No path found.");
                }
            }
        });

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        // Layout setup
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(startWordLabel);
        inputPanel.add(startWordField);
        inputPanel.add(endWordLabel);
        inputPanel.add(endWordField);
        inputPanel.add(algorithmLabel);
        inputPanel.add(algorithmComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(solveButton);

        JPanel mainPanel = new BackgroundPanel(backgroundImage); // Use custom panel with background
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(resultScrollPane);

        // Add main panel to frame
        add(mainPanel);

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Auto-size frame based on components
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void loadWords(String filename, Set<String> words) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
