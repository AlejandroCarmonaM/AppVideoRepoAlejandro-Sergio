package pantallas;

import javax.swing.table.AbstractTableModel;

import dominio.GrupoVideos;
import dominio.Video;
import tds.video.VideoWeb;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;

public class TablaAbstract extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private LinkedList<GrupoVideos> videos;
	private String[] columnNames = {"Video 1","Video 2", "Video 3", "Video 3"};

	public TablaAbstract(){
		this.videos = new LinkedList<GrupoVideos>();
	}
	
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public int getRowCount() { 
		return videos.size(); 
	}
	
	public int getColumnCount() { 
		return 4; 
	}
	
	public JLabel getValueAt(int row, int col) {
		JLabel video = null;
		GrupoVideos sol = videos.get(row);
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
	
	public void addRow(GrupoVideos gVideos) {
		videos.add(gVideos);
	}
	
	public void rellenarTabla(List<Video> videos, VideoWeb vWeb) {
		JLabel aux[] = new JLabel[4];
		boolean escribir = false;
		if (videos.size() > 4) {
			for(int i = 0; i < videos.size(); i++) {
				Video elemento = videos.get(i);
				switch (i%4) {
					case 0:
						aux[0] = new JLabel(vWeb.getThumb(elemento.getUrl()));
						break;
					case 1:
						aux[1] = new JLabel(vWeb.getThumb(elemento.getUrl()));
						break;
					case 2:
						aux[2] = new JLabel(vWeb.getThumb(elemento.getUrl()));
						break;
					case 3:
						aux[3] = new JLabel(vWeb.getThumb(elemento.getUrl()));
						escribir = true;
						break;
					default:
						break;
				}
				if (escribir) {
					this.addRow(new GrupoVideos(aux[0], aux[1], aux[2], aux[3]));
					escribir = false;
				}
			}
		}
		else {
			for(int i = 0; i < videos.size(); i++) {
				Video elemento = videos.get(i);
				switch (i%4) {
					case 0:
						aux[0] = new JLabel(vWeb.getThumb(elemento.getUrl()));
						break;
					case 1:
						aux[1] = new JLabel(vWeb.getThumb(elemento.getUrl()));
						break;
					case 2:
						aux[2] = new JLabel(vWeb.getThumb(elemento.getUrl()));
						break;
					default:
						break;
				}
			}
		}
		if ((videos.size() % 4) != 0) {
			switch (videos.size() %4) {
			case 1:
				this.addRow(new GrupoVideos(aux[0]));
				break;
			case 2:
				this.addRow(new GrupoVideos(aux[0], aux[1]));
				break;
			case 3:
				this.addRow(new GrupoVideos(aux[0], aux[1], aux[2]));
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
