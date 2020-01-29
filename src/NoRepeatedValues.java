// constraint for Skyscraper - there must be no repeated values
public class NoRepeatedValues extends Constraint {

    // implementation of abstract method
    public boolean isSatisfied() {

        for (int i = 0; i < getSpots().length; ++i) {

            if (getSpots()[i].getValue().equals(" ")){
				// ignore default spots
				return true;
			}

            for (int j = 0; j < getSpots().length; ++j) {

            	String firstValue = getSpots()[i].getValue();
            	String secondValue = getSpots()[j].getValue();

            	// return false we have a repetition in two different spots
                if (i != j && firstValue.equals(secondValue)) {
					return false;
				}
            }
        }

        return true;
    }
}
