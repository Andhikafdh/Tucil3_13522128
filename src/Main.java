import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Set<String> englishWords = new HashSet<>();
        WordLadderSolverGUI.loadWords("words_alpha.txt", englishWords); // Load words from file

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WordLadderSolverGUI(englishWords);
            }
        });
    }
}
