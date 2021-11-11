package pantallas;

import javax.swing.table.AbstractTableModel;

import dominio.GrupoVideos;
import dominio.Video;
import tds.video.VideoWeb;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TablaAbstract extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private LinkedList<GrupoVideos> videos;
	private LinkedList<Video> todosVideos;
	private String[] columnNames = {"Video 1","Video 2", "Video 3", "Video 3"};

	public TablaAbstract(List<Video> v){
		this.videos = new LinkedList<GrupoVideos>();
		this.todosVideos = (LinkedList<Video>) v;
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
	
	//modificacion para incluir el titulo para mostrarlo en la lista
	public void rellenarTabla(List<Video> videos, VideoWeb vWeb) {
		JLabel aux[] = new JLabel[4];
		boolean escribir = false;
		if (videos.size() > 4) {
			for(int i = 0; i < videos.size(); i++) {
				Video elemento = videos.get(i);
				switch (i%4) {
					case 0:
						aux[0] = creaLabel(elemento, vWeb);
						break;
					case 1:
						aux[1] = creaLabel(elemento, vWeb);
						break;
					case 2:
						aux[2] = creaLabel(elemento, vWeb);
						break;
					case 3:
						aux[3] = creaLabel(elemento, vWeb);
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
						aux[0] = creaLabel(elemento, vWeb);
						break;
					case 1:
						aux[1] = creaLabel(elemento, vWeb);
						break;
					case 2:
						aux[2] = creaLabel(elemento, vWeb);
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
	
	//metodo para crear los label con el nombre
	private JLabel creaLabel(Video elemento, VideoWeb vWeb)
	{
		JLabel label = new JLabel(elemento.getTitulo(), vWeb.getThumb(elemento.getUrl()), SwingConstants.CENTER);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		return label;
	}
}
