import javax.swing.*;
import java.awt.event.*;

public class SolveListener implements ActionListener {

    private Puzzle puzzle;
    private Frame frame;

    public SolveListener(Puzzle puzzle, Frame frame) {
        this.puzzle = puzzle;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        puzzle.clearSpots(); // clear whatever mess the user has made
		int loops = puzzle.solve();
		frame.update();
        JFrame loopInfo = new JFrame("Loop Info");
        JOptionPane.showMessageDialog(loopInfo, "Solution took " + loops + " iterations to find.",
                "Loop Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
