import java.io.*;
import java.util.*;

public class Kakurasu extends Puzzle {

    private static String[] possibleValues = {" ", "white", "black"};

    // The hints for Kakurasu will be read from kakurasu.txt
    private String[] topHints = new String[5];
    private String[] bottomHints = new String[5];
    private String[] leftHints = new String[5];
    private String[] rightHints = new String[5];

    // constructor
    public Kakurasu() {

        super(5, 5, possibleValues);

        setName("Kakurasu");
        reorderPuzzles();

        // reading hints from kakurasu.txt
        try {
			Scanner fileReader = new Scanner(new File("kakurasu.txt"));
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
        setBlackAndWhite(true);
        setConstraints();
    }

    public void setConstraints() {

        // we need to check the constraints for every row and every column
        Constraint[] constraints = new Constraint[getRows() + getColumns()];

        int index = 0;

        // puzzle values must add up to the bottom hints
        for (int i = 0; i < getRows(); ++i) {

            Spot[] spots = new Spot[getColumns()];

            for (int j = 0; j < getColumns(); ++j) {
                spots[j] = getSpotGrid()[j][i];
            }

            constraints[index] = new AddUp(bottomHints[i]);
            constraints[index++].setSpots(spots);
        }

        // puzzle values must add up to the right side hints
        for (int i = 0; i < getColumns(); ++i) {

            Spot[] spots = new Spot[getRows()];

            for (int j = 0; j < getRows(); ++j) {
                spots[j] = getSpotGrid()[i][j];
            }

            constraints[index] = new AddUp(rightHints[i]);
            constraints[index++].setSpots(spots);
        }

        constraintList = new Constraint[][]{constraints};
    }

    // implementation of abstract method
    public boolean isFilledIn(Spot spot) {
        return spot.getValue().equals("black");
    }

    // implementation of abstract method
    public void toggleSpot(Spot spot) {
        spot.setValue(isFilledIn(spot) ? "white" : "black");
    }
}
