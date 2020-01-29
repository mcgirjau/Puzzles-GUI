import java.util.ArrayList;

// constraint for Nonogram
public class Sections extends Constraint {

    private int sections; // how many separate sections a hint has
    private ArrayList<Integer> sectionLengths = new ArrayList<>(); // the lengths of the hint sections

    // constructor
    public Sections(String constraints) {
        readHints(constraints);
    }

    // implementation of abstract method
    public boolean isSatisfied() {

        // we need to check two things: if there's the same number of sections as the hint specifies,
        // and if the length of each section corresponds to the one given in the hint


        ArrayList<Integer> actualLengths = new ArrayList<>();

        for (int i = 0; i < getSpots().length; i++) {
            if (getSpots()[i].equals(" ")) {
                // ignore default spots
                return true;
            }
        }

        // we start out not inside a section
        boolean currentlyInSection = false;
        int howManySections = 0;
        int index = -1;

        for (int i = 0; i < getSpots().length; ++i) {

            if (currentlyInSection && !isFilledIn(getSpots()[i])) {

                // we have just exited a section
                currentlyInSection = false;

            } else if (!currentlyInSection && getSpots()[i].getValue().equals("black")) {

                // we have just entered a new section
                currentlyInSection = true;
                ++howManySections;
                actualLengths.add(1);
                ++index;

            } else if (currentlyInSection && getSpots()[i].getValue().equals("black")) {

                // we are within a section
                actualLengths.set(index, actualLengths.get(index) + 1);
            }
        }

        return (sections == howManySections) && lengthsCorrespond(actualLengths);
    }

    // method to read in the different section hints as integers
    private void readHints(String hint) {

        sections = (hint.length() + 1)/2;

        String[] hintsAsStrings = hint.split(" ");

        for (String currentHint : hintsAsStrings) {
            sectionLengths.add(Integer.parseInt(currentHint));
        }
    }

    private boolean isFilledIn(Spot spot) {
        return spot.getValue().equals("black");
    }

    private boolean lengthsCorrespond(ArrayList<Integer> actualLengths) {

        if (actualLengths.size() != sections) {
            return false;
        } else {
            for (int i = 0; i < sectionLengths.size(); ++i) {
                // if a section length doesn't correspond with the one specified by the hint, return false
                if (!actualLengths.get(i).equals(sectionLengths.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
