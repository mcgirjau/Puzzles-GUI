public abstract class Constraint {

    private Spot[] spots;

    // to be implemented for each subclass
    public abstract boolean isSatisfied();

    // setter
    public void setSpots(Spot[] spots) {
        this.spots = spots;
    }

    // getter
    public Spot[] getSpots() {
        return spots;
    }
}
