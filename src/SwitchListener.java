import java.awt.event.*;
import javax.swing.*;

public class SwitchListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        String name = (String) comboBox.getSelectedItem();
        if (name != null) {
            FrameManager.switchPuzzle(name);
        }
    }
}
