package pantallas;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import dominio.Video;
import tds.video.VideoWeb;

import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

public class PanelCrearLista extends JPanel {
	private FrameBase frameBase;
	private JTextField campoNombreLista;
	private JTextField campoBuscarTitulo;
	private static VideoWeb vWeb = new VideoWeb();
	private JTable tablaVideos;

	/**
	 * Create the panel.
	 */
	public PanelCrearLista(FrameBase frameBase) {
		
		this.frameBase=frameBase;
		setBackground(Color.GRAY);
		setForeground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));
		;
		
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setPreferredSize(new Dimension(235, 250));
		panelIzquierdo.setBackground(Color.GRAY);
		add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
		
		JPanel panelINL = new JPanel();
		panelINL.setPreferredSize(new Dimension(10, 100));
		panelINL.setMaximumSize(new Dimension(32767, 12000));
		panelINL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelINL.setBackground(Color.GRAY);
		panelIzquierdo.add(panelINL);
		GridBagLayout gbl_panelINL = new GridBagLayout();
		gbl_panelINL.columnWidths = new int[]{0, 0, 0};
		gbl_panelINL.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelINL.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelINL.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelINL.setLayout(gbl_panelINL);
		
		JLabel etiquetaINL = new JLabel("Introducir nueva lista:\r\n");
		etiquetaINL.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaINL = new GridBagConstraints();
		gbc_etiquetaINL.insets = new Insets(7, 0, 5, 5);
		gbc_etiquetaINL.gridx = 0;
		gbc_etiquetaINL.gridy = 0;
		panelINL.add(etiquetaINL, gbc_etiquetaINL);
		
		campoNombreLista = new JTextField();
		GridBagConstraints gbc_campoNombreLista = new GridBagConstraints();
		gbc_campoNombreLista.fill = GridBagConstraints.BOTH;
		gbc_campoNombreLista.insets = new Insets(0, 0, 5, 5);
		gbc_campoNombreLista.gridx = 0;
		gbc_campoNombreLista.gridy = 1;
		panelINL.add(campoNombreLista, gbc_campoNombreLista);
		campoNombreLista.setColumns(10);
		
		JButton botonBuscarLista = new JButton("Buscar\r\n");
		GridBagConstraints gbc_botonBuscarLista = new GridBagConstraints();
		gbc_botonBuscarLista.insets = new Insets(0, 0, 5, 0);
		gbc_botonBuscarLista.gridx = 1;
		gbc_botonBuscarLista.gridy = 1;
		panelINL.add(botonBuscarLista, gbc_botonBuscarLista);
		
		JButton botonEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_botonEliminar = new GridBagConstraints();
		gbc_botonEliminar.gridwidth = 2;
		gbc_botonEliminar.insets = new Insets(0, 0, 0, 5);
		gbc_botonEliminar.gridx = 0;
		gbc_botonEliminar.gridy = 2;
		panelINL.add(botonEliminar, gbc_botonEliminar);
		
		JPanel panelMiLista = new JPanel();
		panelMiLista.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelMiLista.setBackground(Color.GRAY);
		panelIzquierdo.add(panelMiLista);
		GridBagLayout gbl_panelMiLista = new GridBagLayout();
		gbl_panelMiLista.columnWidths = new int[]{0, 0};
		gbl_panelMiLista.rowHeights = new int[]{0, 0};
		gbl_panelMiLista.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelMiLista.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelMiLista.setLayout(gbl_panelMiLista);
		
		JList listaActual = new JList();
		listaActual.setBackground(Color.GRAY);
		GridBagConstraints gbc_listaActual = new GridBagConstraints();
		gbc_listaActual.fill = GridBagConstraints.BOTH;
		gbc_listaActual.gridx = 0;
		gbc_listaActual.gridy = 0;
		panelMiLista.add(listaActual, gbc_listaActual);
		
		JPanel panelAQA = new JPanel();
		panelAQA.setMaximumSize(new Dimension(32767, 10000));
		panelAQA.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelAQA.setBackground(Color.GRAY);
		panelIzquierdo.add(panelAQA);
		GridBagLayout gbl_panelAQA = new GridBagLayout();
		gbl_panelAQA.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelAQA.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelAQA.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAQA.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAQA.setLayout(gbl_panelAQA);
		
		JButton botonAnadir = new JButton("A\u00F1adir");
		GridBagConstraints gbc_botonAnadir = new GridBagConstraints();
		gbc_botonAnadir.insets = new Insets(5, 5, 5, 5);
		gbc_botonAnadir.gridx = 0;
		gbc_botonAnadir.gridy = 0;
		panelAQA.add(botonAnadir, gbc_botonAnadir);
		
		JButton botonQuitar = new JButton("Quitar");
		GridBagConstraints gbc_botonQuitar = new GridBagConstraints();
		gbc_botonQuitar.anchor = GridBagConstraints.WEST;
		gbc_botonQuitar.insets = new Insets(5, 0, 5, 5);
		gbc_botonQuitar.gridx = 3;
		gbc_botonQuitar.gridy = 0;
		panelAQA.add(botonQuitar, gbc_botonQuitar);
		
		JButton botonAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_botonAceptar = new GridBagConstraints();
		gbc_botonAceptar.gridwidth = 4;
		gbc_botonAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_botonAceptar.gridx = 0;
		gbc_botonAceptar.gridy = 2;
		panelAQA.add(botonAceptar, gbc_botonAceptar);
		
		JPanel panelDerecho = new JPanel();
		panelDerecho.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelDerecho.setBackground(Color.GRAY);
		add(panelDerecho, BorderLayout.CENTER);
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
		
		JPanel panelBT = new JPanel();
		panelBT.setPreferredSize(new Dimension(10, 100));
		panelBT.setMaximumSize(new Dimension(32767, 12000));
		panelBT.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelBT.setBackground(Color.GRAY);
		panelDerecho.add(panelBT);
		GridBagLayout gbl_panelBT = new GridBagLayout();
		gbl_panelBT.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelBT.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelBT.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelBT.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBT.setLayout(gbl_panelBT);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 2;
		gbc_rigidArea.gridy = 0;
		panelBT.add(rigidArea, gbc_rigidArea);
		
		JLabel etiquetaBuscar = new JLabel("Buscar titulo:");
		etiquetaBuscar.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaBuscar = new GridBagConstraints();
		gbc_etiquetaBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaBuscar.anchor = GridBagConstraints.EAST;
		gbc_etiquetaBuscar.gridx = 1;
		gbc_etiquetaBuscar.gridy = 1;
		panelBT.add(etiquetaBuscar, gbc_etiquetaBuscar);
		
		campoBuscarTitulo = new JTextField();
		GridBagConstraints gbc_campoBuscarTitulo = new GridBagConstraints();
		gbc_campoBuscarTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_campoBuscarTitulo.fill = GridBagConstraints.BOTH;
		gbc_campoBuscarTitulo.gridx = 2;
		gbc_campoBuscarTitulo.gridy = 1;
		panelBT.add(campoBuscarTitulo, gbc_campoBuscarTitulo);
		campoBuscarTitulo.setColumns(10);
		
		JButton botonBuscarVideo = new JButton("Buscar");
		
		GridBagConstraints gbc_botonBuscarVideo = new GridBagConstraints();
		gbc_botonBuscarVideo.insets = new Insets(0, 0, 5, 10);
		gbc_botonBuscarVideo.gridx = 4;
		gbc_botonBuscarVideo.gridy = 1;
		panelBT.add(botonBuscarVideo, gbc_botonBuscarVideo);
		
		JButton botonNuevaLista = new JButton("Nueva Lista\r\n");
		GridBagConstraints gbc_botonNuevaLista = new GridBagConstraints();
		gbc_botonNuevaLista.gridwidth = 3;
		gbc_botonNuevaLista.gridx = 2;
		gbc_botonNuevaLista.gridy = 2;
		panelBT.add(botonNuevaLista, gbc_botonNuevaLista);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.setBackground(Color.GRAY);
		panelDerecho.add(panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		tablaVideos = new JTable();
		
		tablaVideos.setCellSelectionEnabled(true);
		tablaVideos.setBackground(Color.GRAY);
		GridBagConstraints gbc_tablaVideos = new GridBagConstraints();
		gbc_tablaVideos.fill = GridBagConstraints.BOTH;
		gbc_tablaVideos.gridx = 0;
		gbc_tablaVideos.gridy = 0;
		
		tablaVideos.setDefaultRenderer(Object.class, new ImgTabla());
		String titulos[] = {"Columna 1", "Columna 2", "Columna 3", "Columna 4"};
		DefaultTableModel tm = new DefaultTableModel(null, titulos);
		rellenarTabla(tm);
		
		panel_5.add(tablaVideos, gbc_tablaVideos);
		
		//Listener para buscar los videos, si buscamos sin escribir nada la tabla se resetea
		botonBuscarVideo.addActionListener(event -> {
			String tituloVideo = campoBuscarTitulo.getText();
			if (!tituloVideo.equals("")) {
				List<Video> videoBuscado = this.frameBase.getAppVideo().buscarVideo(tituloVideo);
				int filas = tablaVideos.getRowCount();
				for (int i = filas-1; i >= 0; i--)
					tm.removeRow(i);
				if (videoBuscado != null)
					rellenarTabla(tm, videoBuscado);
				validate();
			}
			else {
				int filas = tablaVideos.getRowCount();
				for (int i = filas-1; i >= 0; i--)
					tm.removeRow(i);
				rellenarTabla(tm);
			}
				
		});
	}
	
	//Metodo para rellenar la tabla con todos los videos 
	private void rellenarTabla(DefaultTableModel tm) {
		
		List<String> urls = frameBase.getAppVideo().obtenerURLs();
		JLabel aux[] = new JLabel[4];
		int contadorAux = 0;
		for (String elemento : urls) {
			if (contadorAux < 4) {
				aux[contadorAux] = new JLabel(vWeb.getThumb(elemento));
				contadorAux++;
			}
			else {
				tm.addRow(new Object[] {aux[0], aux[1], aux[2], aux[3]});
				contadorAux = 0;
				aux[contadorAux] = new JLabel(vWeb.getThumb(elemento));
				contadorAux++;
			}
		}
		if (contadorAux < 4) {
			switch (contadorAux) {
			case 1:
				tm.addRow(new Object[] {aux[0]});
				break;
			case 2:
				tm.addRow(new Object[] {aux[0], aux[1]});
				break;
			case 3:
				tm.addRow(new Object[] {aux[0], aux[1], aux[2]});
				break;
			default:
				break;
			}
		}
		
		
		tablaVideos.setRowHeight(90);
		tablaVideos.setModel(tm);
	}
	
	private void rellenarTabla(DefaultTableModel tm, List<Video> videos) {
		
		JLabel aux[] = new JLabel[4];
		LinkedList<String> urls = new LinkedList<String>();
		for (Video elemento : videos) {
			urls.add(elemento.getUrl());
		}
		
		int contadorAux = 0;
		for (String elemento : urls) {
			if (contadorAux < 4) {
				aux[contadorAux] = new JLabel(vWeb.getThumb(elemento));
				contadorAux++;
			}
			else {
				tm.addRow(new Object[] {aux[0], aux[1], aux[2], aux[3]});
				contadorAux = 0;
				aux[contadorAux] = new JLabel(vWeb.getThumb(elemento));
				contadorAux++;
			}
		}
		if (contadorAux < 4) {
			switch (contadorAux) {
			case 1:
				tm.addRow(new Object[] {aux[0]});
				break;
			case 2:
				tm.addRow(new Object[] {aux[0], aux[1]});
				break;
			case 3:
				tm.addRow(new Object[] {aux[0], aux[1], aux[2]});
				break;
			default:
				break;
			}
		}
		tablaVideos.setRowHeight(90);
		tablaVideos.setModel(tm);
	}
	//Dos metodos para tablas
}