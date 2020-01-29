public class VisibleSkyscrapers extends Constraint {

    private int theoreticalVisible;

    // constructor
    public VisibleSkyscrapers(String theoreticalVisible) {
        this.theoreticalVisible = Integer.parseInt(theoreticalVisible);
    }

    // implementation of abstract method
    // implementation of abstract method
    public boolean isSatisfied() {

        int spots = getSpots().length;
        int[] spotValues = new int[spots];

        for (int i = 0; i < spots; ++i) {
            try {
                spotValues[i] = Integer.parseInt(getSpots()[i].getValue());
            } catch (Exception e) {
                // if the spot is empty
                return true;
            }
        }

        int actualVisible = 0;
        int topSkyscraper = 0;

        for (var currentSkyscraper : spotValues) {
            if (topSkyscraper < currentSkyscraper) {
                topSkyscraper = currentSkyscraper;
                actualVisible++;
            }
        }

        return actualVisible == theoreticalVisible;
    }
}
