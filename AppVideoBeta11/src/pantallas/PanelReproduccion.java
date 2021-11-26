package pantallas;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dominio.Etiqueta;
import dominio.Video;
import tds.video.VideoWeb;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelReproduccion extends JPanel {

	private FrameBase frameBase;
	private JTextField textFieldEtiqeuta;

	public PanelReproduccion(FrameBase frameBase, Video v) {
		this.frameBase = frameBase;
		VideoWeb vWeb = FrameBase.getVideoWeb();
		this.add(vWeb);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel copyright=new JLabel(vWeb.getVersion());
		panel.add(copyright);
		
		v.actualizarNumRepro();
		this.frameBase.getAppVideo().modificarVideo(v);
		JLabel visitas = new JLabel("Visitas: "+v.getNumeroReproducciones());
		panel.add(visitas);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JTextField textFieldEtiqueta = new JTextField();
		panel_1.add(textFieldEtiqueta);
		textFieldEtiqueta.setColumns(10);
		
		JButton btnEtiquetaAnadir = new JButton("Anadir");
		panel_1.add(btnEtiquetaAnadir);
		vWeb.playVideo(v.getUrl());
		this.frameBase.validate();
		
		btnEtiquetaAnadir.addActionListener(ev -> {
			String textoEtiqueta = textFieldEtiqueta.getText();
			if(textoEtiqueta!="") {
				Etiqueta e = new Etiqueta(textoEtiqueta);
				this.frameBase.getAppVideo().registrarEtiqueta(e);
				v.anadirEtiqueta(e);
				this.frameBase.getAppVideo().modificarVideo(v);
				textFieldEtiqueta.setText("");
				this.validate();
			}
			
			});
		
	}
	

}
