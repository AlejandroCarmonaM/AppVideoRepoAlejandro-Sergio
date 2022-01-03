package pantallas;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

import dominio.CuartetoVideos;
import dominio.ListaVideos;
import dominio.Video;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.ComponentOrientation;

public class PanelMisListas extends JPanel {

	private FrameBase frameBase;
	
	public PanelMisListas(FrameBase frameBase) {
		this.frameBase=frameBase;
		setBackground(Color.GRAY);
		setForeground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setPreferredSize(new Dimension(235, 250));
		panelIzquierdo.setBackground(Color.GRAY);
		add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
		
		JPanel panelSL = new JPanel();
		panelSL.setPreferredSize(new Dimension(10, 100));
		panelSL.setMaximumSize(new Dimension(32767, 12000));
		panelSL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSL.setBackground(Color.GRAY);
		panelIzquierdo.add(panelSL);
		GridBagLayout gbl_panelSL = new GridBagLayout();
		gbl_panelSL.columnWidths = new int[]{0, 0, 0};
		gbl_panelSL.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelSL.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelSL.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSL.setLayout(gbl_panelSL);
		
		JLabel etiquetaSL = new JLabel("Seleccione la lista:");
		etiquetaSL.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaSL = new GridBagConstraints();
		gbc_etiquetaSL.anchor = GridBagConstraints.WEST;
		gbc_etiquetaSL.insets = new Insets(7, 0, 5, 5);
		gbc_etiquetaSL.gridx = 0;
		gbc_etiquetaSL.gridy = 0;
		panelSL.add(etiquetaSL, gbc_etiquetaSL);
		
		JComboBox misListas = new JComboBox();
		List<String> listaNombres = frameBase.getAppVideo().getNombreMisListas();
		for(int i = 0; i < listaNombres.size(); i++) {
			misListas.addItem(listaNombres.get(i));
		}
		GridBagConstraints gbc_misListas = new GridBagConstraints();
		gbc_misListas.gridwidth = 2;
		gbc_misListas.insets = new Insets(0, 0, 5, 5);
		gbc_misListas.fill = GridBagConstraints.HORIZONTAL;
		gbc_misListas.gridx = 0;
		gbc_misListas.gridy = 1;
		panelSL.add(misListas, gbc_misListas);
		
		/*JButton botonReproducir = new JButton("Reproducir");
		GridBagConstraints gbc_botonReproducir = new GridBagConstraints();
		gbc_botonReproducir.gridwidth = 2;
		gbc_botonReproducir.gridx = 0;
		gbc_botonReproducir.gridy = 2;
		panelSL.add(botonReproducir, gbc_botonReproducir);*/
		
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
		
		JPanel panelDerecho = new JPanel();
		panelDerecho.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelDerecho.setBackground(Color.GRAY);
		add(panelDerecho, BorderLayout.CENTER);
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
		
		misListas.addActionListener(ev -> {
				modeloLista.clear();
				modeloLista.removeAllElements();
				String nombreListaSeleccionada = misListas.getSelectedItem().toString();
				ListaVideos lv = frameBase.getAppVideo().getListaVideosPorNombre(nombreListaSeleccionada);
				for (Video elemento : lv.getListaVideos())
					modeloLista.addElement(elemento);
		});
		
		listaActual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (modeloLista.size() > 0 ) {
					Video v = modeloLista.get(listaActual.getSelectedIndex());
					frameBase.getAppVideo().addVideoRecientes(v);
					frameBase.creaPanelReproduccion(panelDerecho, new PanelReproduccion(frameBase, v));
					validate();
				}
			}
		});
		
	}

}
