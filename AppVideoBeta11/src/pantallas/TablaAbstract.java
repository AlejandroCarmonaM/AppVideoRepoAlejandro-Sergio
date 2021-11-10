package pantallas;

import javax.swing.table.AbstractTableModel;

import dominio.GrupoVideos;
import dominio.Video;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;

public class TablaAbstract extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private LinkedList<GrupoVideos> videos;
	private String[] columnNames = {"Video 1","Video 2", "Video 3", "Video 3"};

	public TablaAbstract(LinkedList<GrupoVideos> sols){
		this.videos = sols;
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
}
