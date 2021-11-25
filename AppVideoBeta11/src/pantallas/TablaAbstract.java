package pantallas;

import javax.swing.table.AbstractTableModel;

import dominio.CuartetoVideos;
import dominio.GrupoVideos;
import dominio.Video;
import tds.video.VideoWeb;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TablaAbstract extends AbstractTableModel {
	private static final int NUM_COLUMNAS =4;
	private static final long serialVersionUID = 1L;
	private LinkedList<CuartetoVideos> videos;
	private String[] columnNames = {"","", "", ""};

	public TablaAbstract(){
		this.videos = new LinkedList<CuartetoVideos>();
	}
	
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public int getRowCount() { 
		return videos.size(); 
	}
	
	public int getColumnCount() { 
		return NUM_COLUMNAS; 
	}
	
	public Video getValueAt(int row, int col) {
		Video video = null;
		CuartetoVideos sol = videos.get(row);
		switch (col) {						 
			case 0 : 
				video = sol.getVideo1(); 
				break;
			case 1 : 
				video = sol.getVideo2();
				break;
			case 2 : 
				video = sol.getVideo3();
				break;
			case 3 : 
				video = sol.getVideo4();
				break;
		}
		return video;
	}
	
	public void addRow(CuartetoVideos gVideos) {
		videos.add(gVideos);
	}
	
	//modificacion para incluir el titulo para mostrarlo en la lista
	public void rellenarTabla(List<Video> videos, VideoWeb vWeb) {
		Video aux[] = new Video[4];
		boolean escribir = false;
		if (videos.size() > 4) {
			for(int i = 0; i < videos.size(); i++) {
				Video elemento = videos.get(i);
				switch (i%4) {
					case 0:
						aux[0] = elemento;
						break;
					case 1:
						aux[1] = elemento;
						break;
					case 2:
						aux[2] = elemento;
						break;
					case 3:
						aux[3] = elemento;
						escribir = true;
						break;
					default:
						break;
				}
				if (escribir) {
					this.addRow(new CuartetoVideos(aux[0], aux[1], aux[2], aux[3]));
					escribir = false;
				}
			}
		}
		else {
			for(int i = 0; i < videos.size(); i++) {
				Video elemento = videos.get(i);
				switch (i%4) {
					case 0:
						aux[0] =elemento;
						break;
					case 1:
						aux[1] = elemento;
						break;
					case 2:
						aux[2] = elemento;
						break;
					default:
						break;
				}
			}
		}
		if ((videos.size() % 4) != 0) {
			switch (videos.size() %4) {
			case 1:
				this.addRow(new CuartetoVideos(aux[0]));
				break;
			case 2:
				this.addRow(new CuartetoVideos(aux[0], aux[1]));
				break;
			case 3:
				this.addRow(new CuartetoVideos(aux[0], aux[1], aux[2]));
				break;
			default:
				break;
			}
		}
	}
	
	public void removeRow(int i) {
		if (i <= videos.size())
			videos.remove(i);
	}
	
	
}
