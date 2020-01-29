import java.awt.*;

public class SpotToggler extends SpotRenderer {

    private Puzzle puzzle;
    private boolean filledIn = false;

    // constructor
    public SpotToggler(Spot spot, Puzzle puzzle) {

        super(spot);
        this.puzzle = puzzle;

        this.addActionListener(new ToggleListener(this));

        // make everything white by default
        setBackground(Color.WHITE);
    }

    // toggle spot between black and white
    public void toggleSpot() {
        puzzle.toggleSpot(getSpot());
        filledIn = puzzle.isFilledIn(getSpot());
        setBackground(filledIn ? Color.BLACK : Color.WHITE);
    }

    // set value of spot
    public void setValue() {
        filledIn = puzzle.isFilledIn(getSpot());
        setBackground(filledIn ? Color.BLACK : Color.WHITE);
    }
}
