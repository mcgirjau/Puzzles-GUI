import java.awt.event.*;
import javax.swing.*;

public class SpotListener implements ActionListener {

    private Spot spot;

    public SpotListener(Spot spot) {
        this.spot = spot;
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        String value = (String) comboBox.getSelectedItem();
        spot.setValue(value);
    }
}
