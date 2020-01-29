import javax.swing.*;
import java.awt.event.WindowEvent;

public class FrameManager {

    private static Puzzle puzzle = new Kakurasu(); // begin with a Kakurasu game
    private static Frame frame = new Frame(puzzle);

    public static void run() {
        frame.initialize();
        frame.pack();
        frame.setVisible(true);
    }

    public static void switchPuzzle(String name) {

        // current frame has become outdated
        Frame previousFrame = frame;
        previousFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // change puzzle
        switch (name) {
            case "Kakurasu":
                puzzle = new Kakurasu();
                break;
            case "Nonogram":
                puzzle = new Nonogram();
                break;
            case "Skyscraper":
                puzzle = new Skyscraper();
                break;
        }

        frame = new Frame(puzzle); // new frame for current puzzle
        run(); // initialize the new frame

        // close previous frame
        previousFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
