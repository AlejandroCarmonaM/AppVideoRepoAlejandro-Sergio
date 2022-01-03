package pantallas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import java.util.regex.*;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;

public class PanelCargadorVideo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_titulo;
	private JTextField textField_URL;
	private JTextField textField_etiquetas;
	private JLabel etiquetaURL;
	private JLabel etiqueta;
	private JButton botonAceptar;
	private FrameBase frameBase;

	/**
	 * Create the panel.
	 */
	public PanelCargadorVideo(FrameBase frameBase) {
		setBackground(Color.GRAY);
		this.frameBase = frameBase;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel etiquetaTitulo = new JLabel("titulo");
		etiquetaTitulo.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaTitulo = new GridBagConstraints();
		gbc_etiquetaTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaTitulo.anchor = GridBagConstraints.EAST;
		gbc_etiquetaTitulo.gridx = 4;
		gbc_etiquetaTitulo.gridy = 2;
		add(etiquetaTitulo, gbc_etiquetaTitulo);
		
		textField_titulo = new JTextField();
		GridBagConstraints gbc_textField_titulo = new GridBagConstraints();
		gbc_textField_titulo.insets = new Insets(0, 0, 5, 0);
		gbc_textField_titulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_titulo.gridx = 5;
		gbc_textField_titulo.gridy = 2;
		add(textField_titulo, gbc_textField_titulo);
		textField_titulo.setColumns(10);
		
		etiquetaURL = new JLabel("URL");
		etiquetaURL.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaURL = new GridBagConstraints();
		gbc_etiquetaURL.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaURL.anchor = GridBagConstraints.EAST;
		gbc_etiquetaURL.gridx = 4;
		gbc_etiquetaURL.gridy = 3;
		add(etiquetaURL, gbc_etiquetaURL);
		
		textField_URL = new JTextField();
		GridBagConstraints gbc_textField_URL = new GridBagConstraints();
		gbc_textField_URL.insets = new Insets(0, 0, 5, 0);
		gbc_textField_URL.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_URL.gridx = 5;
		gbc_textField_URL.gridy = 3;
		add(textField_URL, gbc_textField_URL);
		textField_URL.setColumns(10);
		
		etiqueta = new JLabel("etiquetas");
		etiqueta.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiqueta = new GridBagConstraints();
		gbc_etiqueta.insets = new Insets(0, 0, 5, 5);
		gbc_etiqueta.anchor = GridBagConstraints.EAST;
		gbc_etiqueta.gridx = 4;
		gbc_etiqueta.gridy = 4;
		add(etiqueta, gbc_etiqueta);
		
		textField_etiquetas = new JTextField();
		GridBagConstraints gbc_textField_etiquetas = new GridBagConstraints();
		gbc_textField_etiquetas.insets = new Insets(0, 0, 5, 0);
		gbc_textField_etiquetas.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_etiquetas.gridx = 5;
		gbc_textField_etiquetas.gridy = 4;
		add(textField_etiquetas, gbc_textField_etiquetas);
		textField_etiquetas.setColumns(20);
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBackground(Color.RED);
		botonAceptar.setForeground(Color.WHITE);
		
		GridBagConstraints gbc_botonAceptar = new GridBagConstraints();
		gbc_botonAceptar.gridx = 5;
		gbc_botonAceptar.gridy = 5;
		add(botonAceptar, gbc_botonAceptar);

		
		botonAceptar.addActionListener(ev-> {
			Pattern p = Pattern.compile("^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+");
			Matcher m = p.matcher(textField_URL.getText());
			
			if(!textField_titulo.getText().isBlank() && m.matches()) {
				ArrayList<String> etiquetas = new ArrayList<String>(Arrays.asList(textField_etiquetas.getText().split(",")));
				this.frameBase.getAppVideo().registrarVideo(textField_titulo.getText(), textField_URL.getText(), etiquetas);
				botonAceptar.setBackground(Color.GREEN);
				this.validate();
				JOptionPane.showMessageDialog(this, "Vídeo subido");
			}
			else JOptionPane.showMessageDialog(this, "Campo URL y campo título no válidos");
		});
	}
}


