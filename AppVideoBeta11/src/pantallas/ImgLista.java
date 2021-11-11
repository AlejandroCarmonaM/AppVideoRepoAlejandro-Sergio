package pantallas;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class ImgLista extends DefaultListCellRenderer {

	@Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    	
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //este no es nuestro JLabel
        if (value!=null) {
	        label.setIcon(((JLabel) value).getIcon()); //asi que hay que hacer un setIcon y setText
	        label.setText(((JLabel) value).getText()); 
        }
        return label;
    }
}