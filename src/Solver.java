public class Solver {

    private Puzzle puzzle;
    private String[] possibleValues;
    private int loops = 0;

    // constructor
    public Solver(Puzzle puzzle) {
        this.puzzle = puzzle;
        possibleValues = puzzle.copyPossibleValues();
    }

    // method based on class handout
    public boolean findSolution(Spot[][] spots, int row, int column) {

        ++loops;

    	// base case
        if (row == puzzle.getRows()) {
            return puzzle.allConstraintsSatisfied();
        }

        for (var value : possibleValues) {

        	// trying out all possible values in every spots
        	spots[row][column].setValue(value);

        	// if everything is OK so far...
            if (puzzle.allConstraintsSatisfied()) {

            	// ...move on to the next spot
                int nextColumn = (column + 1) % puzzle.getColumns();
                int nextRow = (nextColumn == 0) ? row + 1 : row;

                if (findSolution(spots, nextRow, nextColumn)) {
                    return true;
                }
            }
        }

        // if no solution has been found so far, set spots back to default
        spots[row][column].setValue(puzzle.getDefaultValue());
        return false;
    }

    public int getLoops() {
        return loops;
    }

    public void resetLoops() {
        loops = 0;
    }
}
