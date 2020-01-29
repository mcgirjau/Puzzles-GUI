public abstract class Puzzle {

	// static list of available puzzles
	private static String[] choices = {"Kakurasu", "Nonogram", "Skyscraper"};

	// puzzle data
	private String name;
	private Spot[][] spots;
	private String[] possibleValues;

	// Kakurasu and Nonogram are black and white
	private boolean isBlackAndWhite = false;

	// puzzle dimensions
	private int rows;
	private int columns;

	// constraints
	public Constraint[][] constraintList;

	private Solver solver;

	// the hints to be displayed on the sides of the grid
	public String[] top;
	public String[] left;
	public String[] bottom;
	public String[] right;

	// constructor
	public Puzzle(int columns, int rows, String[] possibleValues) {

		this.possibleValues = possibleValues;

		this.rows = rows;
		this.columns = columns;

		spots = new Spot[rows][columns];
		setSpotValues();

		solver = new Solver(this);
	}

	public static String[] getAvailablePuzzles() {
		return choices;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultValue() {
		return " ";
	}

	public String[] getPossibleValues() {
		return possibleValues;
	}

	public String[] copyPossibleValues() {
		String[] copy = new String[possibleValues.length - 1];
		System.arraycopy(possibleValues, 1, copy, 0, possibleValues.length - 1);
		return copy;
	}

	public void setBlackAndWhite(boolean blackAndWhite) {
		isBlackAndWhite = blackAndWhite;
	}

	public boolean isBlackAndWhite() {
		return isBlackAndWhite;
	}

	// setter for the hints
	public void setHints(String[] top, String[] bottom, String[] left, String[] right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}

	// setter for the default spot values
	private void setSpotValues() {
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				// set all spots to default value " "
				spots[i][j] = new Spot(" ");
			}
		}
	}

	public Spot[][] getSpotGrid() {
		return spots;
	}

	public Spot[] getSpotList() {

		// assuming a rectangular grid
		Spot[] spots = new Spot[rows * columns];

		int index = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				spots[index++] = this.spots[i][j];
			}
		}

		return spots;
	}

	// method to set all the spots to their default value " "
	public void clearSpots() {
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				spots[i][j].setValue(" ");
			}
		}
	}

	// method to solve the puzzle
	public int solve() {
		solver.findSolution(spots, 0, 0);
		int loops = solver.getLoops();
		solver.resetLoops();
		return loops;
	}

	// method to reorder puzzle dropdown list
	public void reorderPuzzles() {

		for (int i = 0; i < choices.length; ++i) {

			if (name.equals(choices[i])) {
				// swap the current top puzzle and the future top puzzle
				String temp = choices[0];
				choices[0] = name;
				choices[i] = temp;
			}
		}
	}

	// method to check whether all constraints are satisfied
	public boolean allConstraintsSatisfied() {

		boolean isGridBlank = true;

		for (int i = 0; i < getRows(); ++i) {
			for (int j = 0; j < getColumns(); ++j) {
				if (!getSpotGrid()[i][j].getValue().equals(" ")) {
					isGridBlank = false;
				}
			}
		}

		// if the grid is blank, the constraints are not satisfied
		if (isGridBlank) {
			return false;
		}

		// if one constraint is violated, return false
		for (Constraint[] constraints : constraintList) {
			for (Constraint constraint : constraints) {
				if (!constraint.isSatisfied()) {
					return false;
				}
			}
		}

		return true;
	}

	// methods to be implemented in subclasses

	public abstract void setConstraints(); // puzzle-specific constraint setter

	public abstract boolean isFilledIn(Spot spot);

	public abstract void toggleSpot(Spot spot);
}