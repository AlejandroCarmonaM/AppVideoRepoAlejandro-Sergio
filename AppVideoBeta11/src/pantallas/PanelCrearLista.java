package pantallas;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.BevelBorder;

import dominio.AppVideo;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Dimension;

public class PanelCrearLista extends JPanel {
	private JTextField textField_1;
	private JTextField textField;
	private AppVideo appvideo;

	/**
	 * Create the panel.
	 */
	public PanelCrearLista(AppVideo appvideo) {
		this.appvideo = appvideo;
		setBackground(Color.GRAY);
		setForeground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(200, 40));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(Color.GRAY);
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {70, 0, 0};
		gbl_panel_1.rowHeights = new int[]{15, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel etiquetaINL = new JLabel("Introducir nombre lista:");
		etiquetaINL.setFont(new Font("Tahoma", Font.BOLD, 11));
		etiquetaINL.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaINL = new GridBagConstraints();
		gbc_etiquetaINL.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaINL.anchor = GridBagConstraints.WEST;
		gbc_etiquetaINL.gridx = 0;
		gbc_etiquetaINL.gridy = 0;
		panel_1.add(etiquetaINL, gbc_etiquetaINL);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.insets = new Insets(10, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBackground(Color.GRAY);
		panel.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel etiquetaBT = new JLabel("Buscar titulo:");
		etiquetaBT.setFont(new Font("Tahoma", Font.BOLD, 14));
		etiquetaBT.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaBT = new GridBagConstraints();
		gbc_etiquetaBT.insets = new Insets(15, 0, 5, 5);
		gbc_etiquetaBT.anchor = GridBagConstraints.EAST;
		gbc_etiquetaBT.gridx = 0;
		gbc_etiquetaBT.gridy = 0;
		panel_2.add(etiquetaBT, gbc_etiquetaBT);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 7;
		gbc_textField_1.insets = new Insets(15, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 0;
		panel_2.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		;
		
		JButton botonBuscar2 = new JButton("Buscar");
		GridBagConstraints gbc_botonBuscar2 = new GridBagConstraints();
		gbc_botonBuscar2.insets = new Insets(15, 0, 5, 5);
		gbc_botonBuscar2.gridx = 8;
		gbc_botonBuscar2.gridy = 0;
		panel_2.add(botonBuscar2, gbc_botonBuscar2);
		
		JButton botonNBusqueda = new JButton("Nueva b\u00FAsqueda");
		GridBagConstraints gbc_botonNBusqueda = new GridBagConstraints();
		gbc_botonNBusqueda.insets = new Insets(0, 0, 0, 5);
		gbc_botonNBusqueda.gridx = 5;
		gbc_botonNBusqueda.gridy = 2;
		panel_2.add(botonNBusqueda, gbc_botonNBusqueda);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(235, 250));
		panel_3.setBackground(Color.GRAY);
		add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBackground(Color.GRAY);
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.setBackground(Color.GRAY);
		panel_3.add(panel_5, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JButton botonAnadir = new JButton("A\u00F1adir");
		GridBagConstraints gbc_botonAnadir = new GridBagConstraints();
		gbc_botonAnadir.anchor = GridBagConstraints.WEST;
		gbc_botonAnadir.insets = new Insets(5, 0, 10, 5);
		gbc_botonAnadir.gridx = 0;
		gbc_botonAnadir.gridy = 0;
		panel_5.add(botonAnadir, gbc_botonAnadir);
		
		JButton botonQuitar = new JButton("Quitar");
		GridBagConstraints gbc_botonQuitar = new GridBagConstraints();
		gbc_botonQuitar.anchor = GridBagConstraints.EAST;
		gbc_botonQuitar.insets = new Insets(5, 0, 10, 0);
		gbc_botonQuitar.gridx = 2;
		gbc_botonQuitar.gridy = 0;
		panel_5.add(botonQuitar, gbc_botonQuitar);
		
		JButton botonAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_botonAceptar = new GridBagConstraints();
		gbc_botonAceptar.gridwidth = 3;
		gbc_botonAceptar.insets = new Insets(0, 0, 10, 0);
		gbc_botonAceptar.gridx = 0;
		gbc_botonAceptar.gridy = 1;
		panel_5.add(botonAceptar, gbc_botonAceptar);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6.setBackground(Color.GRAY);
		add(panel_6, BorderLayout.CENTER);

	}

}