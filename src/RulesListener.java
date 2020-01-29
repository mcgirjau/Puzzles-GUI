import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RulesListener implements ActionListener {

    private Puzzle puzzle;

    public RulesListener(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void actionPerformed(ActionEvent e) {

        switch (puzzle.getName()) {

            case "Kakurasu": {
                ImageIcon image = new ImageIcon(
                        new ImageIcon("kakurasu-rules.png").getImage().getScaledInstance(900, 800, Image.SCALE_DEFAULT)
                );
                JFrame rules = new JFrame("Rules");
                JOptionPane.showMessageDialog(rules, "", "Kakurasu Rules", JOptionPane.INFORMATION_MESSAGE, image);
                break;
            }

            case "Nonogram": {
                ImageIcon image = new ImageIcon(
                        new ImageIcon("nonogram-rules.png").getImage().getScaledInstance(1200, 600, Image.SCALE_DEFAULT)
                );
                JFrame rules = new JFrame("Rules");
                JOptionPane.showMessageDialog(rules, "", "Nonogram Rules", JOptionPane.INFORMATION_MESSAGE, image);
                break;
            }

            case "Skyscraper": {
                ImageIcon image = new ImageIcon(
                        new ImageIcon("skyscraper-rules.png").getImage().getScaledInstance(700, 800, Image.SCALE_DEFAULT)
                );
                JFrame rules = new JFrame("Rules");
                JOptionPane.showMessageDialog(rules, "", "Skyscraper Rules", JOptionPane.INFORMATION_MESSAGE, image);
                break;
            }
        }
    }
}
