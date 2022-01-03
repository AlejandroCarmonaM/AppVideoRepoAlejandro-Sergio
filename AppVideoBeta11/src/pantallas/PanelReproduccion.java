package pantallas;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dominio.Etiqueta;
import dominio.PoolEtiquetas;
import dominio.Video;
import javafx.scene.paint.Color;
import tds.video.VideoWeb;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
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
		
		JPanel panel_etiquetas = new JPanel();
		panel.add(panel_etiquetas);
		vWeb.playVideo(v.getUrl());
		this.frameBase.validate();
		
		for(Etiqueta e :v.getEtiquetas())
		{
			JLabel label_etiqueta = new JLabel(e.getNombre());
			label_etiqueta.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			label_etiqueta.setForeground(java.awt.Color.BLACK);
			panel_etiquetas.add(label_etiqueta);
		}
		
		btnEtiquetaAnadir.addActionListener(ev -> {
			String textoEtiqueta = textFieldEtiqueta.getText();
			if(textoEtiqueta!="") {
				Etiqueta e = PoolEtiquetas.INSTANCE.get(textoEtiqueta);
				this.frameBase.getAppVideo().registrarEtiqueta(e);
				v.anadirEtiqueta(e);
				this.frameBase.getAppVideo().modificarVideo(v);
				textFieldEtiqueta.setText("");
				this.validate();
			}
			
			});
		
	}
	

}
