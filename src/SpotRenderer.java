import javax.swing.*;

public class SpotRenderer extends JButton {

    private Spot spot;
    private String[] options;
    private JComboBox dropdown;

    // constructor for Skyscraper (which has dropdown options)
    public SpotRenderer(Spot spot, String[] options) {
        this.spot = spot;
        this.options = options;
        createDropdownMenu();
    }

    // constructor for Kakurasu and Nonogram (to be used in SpotToggler)
    public SpotRenderer(Spot spot) {
        this.spot = spot;
    }

    public void setValue() {
    	String currentValue = spot.getValue();
        dropdown.setSelectedItem(currentValue);
    }

    public Spot getSpot() {
    	return spot;
	}

    private void createDropdownMenu() {

        dropdown = new JComboBox();
        add(dropdown);

        dropdown.addActionListener(new SpotListener(spot));

        // add all the options to the dropdown menu
        for (int i = 0; i < options.length; ++i) {
            dropdown.addItem(options[i]);
        }

        setValue();
    }
}
