public class Nonogram extends Puzzle {

	private static String[] possibleValues = {" ", "white", "black"};

	private static String[] topHints = {"4", "4", "1 1", "1 1", "1"};
	private static String[] bottomHints = topHints;
	private static String[] leftHints = {"1 2", "2", "4", "2", "2"};
	private static String[] rightHints = leftHints;

	// constructor
	public Nonogram() {
		super(5, 5, possibleValues);

		setName("Nonogram");
		reorderPuzzles();

		setBlackAndWhite(true);
		setHints(topHints, bottomHints, leftHints, rightHints);

		setConstraints();
	}

	public void setConstraints() {

		// we need to check the constraints for every row and every column
		Constraint[] constraints = new Constraint[getRows() + getColumns()];

		int index = 0;

		for (int j = 0; j < getColumns(); ++j) {

			Spot[] spots = new Spot[getRows()];

			for (int i = 0; i < getRows(); ++i) {
				spots[i] = getSpotGrid()[j][i];
			}

			constraints[index] = new Sections(leftHints[j]);
			constraints[index++].setSpots(spots);
		}

		for (int i = 0; i < getRows(); ++i) {

			Spot[] spots = new Spot[getColumns()];

			for (int j = 0; j < getColumns(); ++j) {
				spots[j] = getSpotGrid()[j][i];
			}

			constraints[index] = new Sections(topHints[i]);
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
