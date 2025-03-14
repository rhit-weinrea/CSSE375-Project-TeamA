package mainApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Switches the color of a gene to the opposite color.
 * 
 * @author akamahwa
 *
 */
public class GeneButtonListener implements ActionListener {

	private JButton button;
	private Color color_1, color_2;

	public GeneButtonListener(JButton button, Color color_1, Color color_2) {
		this.button = button;
		this.color_1 = color_1;
		this.color_2 = color_2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.button.getBackground().equals(this.color_1)) {
			this.button.setBackground(this.color_2);
		} else {
			this.button.setBackground(this.color_1);
		}
	}

}
