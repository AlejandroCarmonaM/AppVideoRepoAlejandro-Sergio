package pantallas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import dominio.Etiqueta;
import dominio.Video;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.awt.Color;

public class PanelCargadorVideo extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel etiquetaURL;
	private JLabel etiqueta;
	private JButton botonAceptar;
	private FrameBase frameBase;

	/**
	 * Create the panel.
	 */
	public PanelCargadorVideo(FrameBase frameBase) {
		this.frameBase = frameBase;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel etiquetaTitulo = new JLabel("titulo");
		GridBagConstraints gbc_etiquetaTitulo = new GridBagConstraints();
		gbc_etiquetaTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaTitulo.anchor = GridBagConstraints.EAST;
		gbc_etiquetaTitulo.gridx = 4;
		gbc_etiquetaTitulo.gridy = 2;
		add(etiquetaTitulo, gbc_etiquetaTitulo);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		etiquetaURL = new JLabel("URL");
		GridBagConstraints gbc_etiquetaURL = new GridBagConstraints();
		gbc_etiquetaURL.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaURL.anchor = GridBagConstraints.EAST;
		gbc_etiquetaURL.gridx = 4;
		gbc_etiquetaURL.gridy = 3;
		add(etiquetaURL, gbc_etiquetaURL);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 3;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		etiqueta = new JLabel("etiquetas");
		GridBagConstraints gbc_etiqueta = new GridBagConstraints();
		gbc_etiqueta.insets = new Insets(0, 0, 5, 5);
		gbc_etiqueta.anchor = GridBagConstraints.EAST;
		gbc_etiqueta.gridx = 4;
		gbc_etiqueta.gridy = 4;
		add(etiqueta, gbc_etiqueta);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 5;
		gbc_textField_2.gridy = 4;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(20);
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBackground(Color.RED);
		botonAceptar.setForeground(Color.WHITE);
		
		GridBagConstraints gbc_botonAceptar = new GridBagConstraints();
		gbc_botonAceptar.gridx = 5;
		gbc_botonAceptar.gridy = 5;
		add(botonAceptar, gbc_botonAceptar);

		
		botonAceptar.addActionListener(ev-> {
			//HashSet<String> etiquetas = new HashSet<String>();
			//etiquetas.add(textField_2.getText());
			String textoSinEspacios = textField_2.getText()/*.replace(" ", "")*/;
			ArrayList<String> etiquetas = new ArrayList<String>(Arrays.asList(textoSinEspacios.split(",")));
			frameBase.getAppVideo().registrarVideo(textField.getText(), textField_1.getText(), etiquetas);
			botonAceptar.setBackground(Color.GREEN);
			this.validate();
		});
	}

}
