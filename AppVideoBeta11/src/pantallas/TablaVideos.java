package pantallas;

import java.awt.Component;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import dominio.Video;


public class TablaVideos extends AbstractTableModel {
	
	//atributos
	private final static int ANCHO_TABLA=5;
	//private final static int ALTO_TABLA=5;
	private List<Video> videos;
	
	public TablaVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public int getRowCount() {
		if(videos.size()%ANCHO_TABLA==0) return videos.size()/ANCHO_TABLA;
		return (videos.size()/ANCHO_TABLA)+1;
	}

	@Override
	public int getColumnCount() {
		if(videos.size()<ANCHO_TABLA) return videos.size();
		return ANCHO_TABLA;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Video v = videos.get(row+col);
		return v.getTitulo(); //de momento va con los strings, con lo de abajo, se pueden poner fotos
		}
		
	
	public void setValueAt(Object value, int row, int col) { }
	
	/*private class ImageRender extends DefaultTableCellRenderer{
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column)
		{
			String photoName = value.toString();
			ImageIcon imageIcon = new ImageIcon();
			return new JLabel(imageIcon);
			
		}
		//http://www.chuidiang.org/java/tablas/tablarender/tablarender.php
	}*/

}
