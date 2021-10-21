package pantallas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class PanelPrueba extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelPrueba() {
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblNewLabel = new JLabel("HOLAAAAA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblNewLabel);

	}

}
