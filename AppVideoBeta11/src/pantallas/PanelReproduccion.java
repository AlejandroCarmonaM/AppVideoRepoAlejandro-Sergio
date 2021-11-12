package pantallas;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dominio.Video;
import pruebaVideoAlumnos.VideoApp;
import tds.video.VideoWeb;

public class PanelReproduccion extends JPanel {

	private FrameBase frameBase;

	public PanelReproduccion(FrameBase frameBase, Video v) {
		
		this.frameBase = frameBase;
		VideoWeb vWeb = FrameBase.getVideoWeb();
		JLabel copyright=new JLabel(vWeb.getVersion());
		this.add(vWeb);
		this.add(copyright);	
		vWeb.playVideo(v.getUrl());
		this.frameBase.validate();
		
	}
	

}
