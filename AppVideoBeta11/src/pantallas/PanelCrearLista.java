package pantallas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import dominio.CuartetoVideos;
import dominio.GrupoVideos;
import dominio.Video;
import tds.video.VideoWeb;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;

public class PanelCrearLista extends JPanel {
	private FrameBase frameBase;
	private JTextField campoNombreLista;
	private JTextField campoBuscarTitulo;
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
		
		JList<Video> listaActual = new JList<Video>();
		listaActual.setCellRenderer(new ImgLista());
		listaActual.setBackground(Color.GRAY);
		GridBagConstraints gbc_listaActual = new GridBagConstraints();
		gbc_listaActual.fill = GridBagConstraints.BOTH;
		gbc_listaActual.gridx = 0;
		gbc_listaActual.gridy = 0;
		
		DefaultListModel<Video> modeloLista = new DefaultListModel<Video>();
		listaActual.setModel(modeloLista);
		
		JScrollPane scroller1 = new JScrollPane(listaActual);
		scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		panelMiLista.add(scroller1, gbc_listaActual);
		
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
		CuartetoVideos gVideos = new CuartetoVideos();
		LinkedList<CuartetoVideos> listaCVideos = new LinkedList<CuartetoVideos>();
		ArrayList<Video> videosAux = (ArrayList<Video>) frameBase.getAppVideo().obtenerVideos();
		
		
		listaCVideos.add(gVideos);
		TablaAbstract tm = new TablaAbstract();
		tm.rellenarTabla(videosAux, FrameBase.getVideoWeb());
		
		tablaVideos.setModel(tm);
		tablaVideos.setRowHeight(120); //cambio en la altura para que se vean los titulos
		tablaVideos.setModel(tm);
		
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
					tm.rellenarTabla(videoBuscado, FrameBase.getVideoWeb());
			}
			else {
				int filas = tablaVideos.getRowCount();
				for (int i = filas-1; i >= 0; i--)
					tm.removeRow(i);
				List<Video> todosVideos = frameBase.getAppVideo().obtenerVideos();
				tm.rellenarTabla(todosVideos, FrameBase.getVideoWeb());
			}
			tm.fireTableDataChanged();
			validate();	
		});

		tablaVideos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaVideos.rowAtPoint(e.getPoint());
				int columna = tablaVideos.columnAtPoint(e.getPoint());
				modeloLista.addElement(tm.getValueAt(fila, columna));
			}
		});
	}
}