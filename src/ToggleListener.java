import java.awt.event.*;

class ToggleListener implements ActionListener {

    SpotToggler spotToggler;

    // constructor
    public ToggleListener(SpotToggler spotToggler) {
        this.spotToggler = spotToggler;
    }

    public void actionPerformed(ActionEvent e) {
        spotToggler.toggleSpot();
    }
}