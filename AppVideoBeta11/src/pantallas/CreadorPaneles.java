package pantallas;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class CreadorPaneles {
	
	public static void creaPanel(JPanel panel_central, JPanel panel_nuevo)
	{
		panel_central.setVisible(false);
		panel_central.removeAll();
		panel_nuevo.setVisible(true);
		panel_central.add(panel_nuevo);
		panel_central.setVisible(true);
		panel_central.repaint();
		panel_central.revalidate();
		
	}
	
	
}
