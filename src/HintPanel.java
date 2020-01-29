import javax.swing.*;
import java.awt.*;

public class HintPanel extends JPanel {

	public HintPanel(String s) {
		// using GridBagLayout to center the value within the panel
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.add(new JLabel(s));
	}
}