package pantallas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.Video;


public class TablaVideos extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//atributos
	private final static int ANCHO_TABLA=5;
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
		return v.getTitulo();
	}
		
}
