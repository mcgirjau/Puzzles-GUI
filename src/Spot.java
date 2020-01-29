public class Spot {

    private String value;

    // constructor
    public Spot(String value) {
        this.value = value;
    }

    // getter
    public String getValue() {
        return value;
    }

    // setter
    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(String string) {
        return value.equals(string);
    }
}