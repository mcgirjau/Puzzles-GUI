import java.io.File;
import java.util.Scanner;

public class Skyscraper extends Puzzle {

	private static String[] possibleValues = {" ", "1", "2", "3", "4", "5"};

	// The hints for Skyscraper will be read from skyscraper.txt
    private String[] topHints = new String[5];
    private String[] bottomHints = new String[5];
    private String[] leftHints = new String[5];
    private String[] rightHints = new String[5];

    // constructor
    public Skyscraper() {

        super(5, 5, possibleValues);

        setName("Skyscraper");
        reorderPuzzles();

        // reading hints from skyscraper.txt
        try {
            Scanner fileReader = new Scanner(new File("skyscraper.txt"));
            for (String[] hint : new String[][]{topHints, bottomHints, leftHints, rightHints}) {
                int i = 0;
                while (fileReader.hasNextInt()) {
                    hint[i++] = Integer.toString(fileReader.nextInt());
                }
                fileReader.next(); // clear line
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setHints(topHints, bottomHints, leftHints, rightHints);
        setConstraints();
    }

    public void setConstraints() {

        // we need two constraints for Skyscraper

        // check for each row and column
        Constraint[] noRepeatedValues = new Constraint[getColumns() + getRows()];
        // check for each row and column back and forth
        Constraint[] visibleSkyscrapers = new Constraint[2 * (getColumns() + getRows())];

        int index = 0;

        for (int j = 0; j < getColumns(); ++j) {

            Spot[] spots = getSpotGrid()[j];
            Spot[] reversedSpots = new Spot[spots.length];

            // copy the spots in reverse order
            for (int i = 0; i < spots.length; ++i) {
                reversedSpots[spots.length - i - 1] = spots[i];
            }

            visibleSkyscrapers[j] = new VisibleSkyscrapers(leftHints[j]);
            visibleSkyscrapers[j].setSpots(spots);

            visibleSkyscrapers[j + getColumns()] = new VisibleSkyscrapers(rightHints[j]);
            visibleSkyscrapers[j + getColumns()].setSpots(reversedSpots);

            noRepeatedValues[j] = new NoRepeatedValues();
            noRepeatedValues[j].setSpots(spots);

            ++index;
        }

        for (int i = 0; i < getRows(); ++i) {

            Spot[] spots = new Spot[getRows()];

            int n = 0;

            for (int j = 0; j < getColumns(); ++j) {
                spots[n++] = getSpotGrid()[j][i];
            }

            Spot[] sReverse = new Spot[spots.length];

            for (int j = 0; j < spots.length; j++) {
                sReverse[spots.length - j - 1] = spots[j];
            }

            visibleSkyscrapers[2 * index + i] = new VisibleSkyscrapers(topHints[i]);
            visibleSkyscrapers[2 * index + i].setSpots(spots);

            visibleSkyscrapers[2 * index + i + getRows()] = new VisibleSkyscrapers(bottomHints[i]);
            visibleSkyscrapers[2 * index + i + getRows()].setSpots(sReverse);

            noRepeatedValues[index + i] = new NoRepeatedValues();
            noRepeatedValues[index + i].setSpots(spots);
        }

        constraintList = new Constraint[][]{visibleSkyscrapers, noRepeatedValues};
    }

    // implementation of abstract method - empty since Skyscraper doesn't need toggling
    public void toggleSpot(Spot spot) {
        // empty body
    }

    // implementation of abstract method
    public boolean isFilledIn(Spot spot) {
        return false;
    }
}
