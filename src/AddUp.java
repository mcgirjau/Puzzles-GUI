// constraint for Kakurasu - the shaded spots must add up to the hints on the right and bottom
public class AddUp extends Constraint {

    // the hint sum
    private int sum;

    // constructor
    public AddUp(String string) {
        sum = Integer.parseInt(string);
    }

    // implementation of abstract method
    public boolean isSatisfied() {

        int total = 0;

        // sum up all the shaded spots
        for (int i = 0; i < getSpots().length; ++i) {

            if (getSpots()[i].equals(" ")) {
            	// ignore default spots
				return true;
			} else if (getSpots()[i].equals("black")) {
            	// add spot location if it's shaded
				total += (i + 1);
			}
        }

        // is the total equal to the hint sum?
		return total == sum;
    }
}
