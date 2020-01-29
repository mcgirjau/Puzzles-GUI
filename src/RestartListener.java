import java.awt.event.*;

public class RestartListener implements ActionListener {

    private Puzzle puzzle;
    private Frame frame;

    public RestartListener(Puzzle puzzle, Frame frame) {
        this.puzzle = puzzle;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        puzzle.clearSpots();
        frame.update();
    }
}
