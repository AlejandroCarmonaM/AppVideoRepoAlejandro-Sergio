package pantallas;

import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;

import dominio.AppVideo;
import dominio.CuartetoVideos;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import dominio.Etiqueta;
import dominio.PoolEtiquetas;
import dominio.Video;

import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;

public class PanelExplorar extends JPanel {
	//atributos
	
	private JTextField campoBuscarTitulo;
	private FrameBase frameBase;
	private Set<Etiqueta> etiquetasSeleccionadas= new HashSet<Etiqueta>();  
	private JTextField etiquetaPersonalizada;
	
	//constructor
	public PanelExplorar(FrameBase frameBase) {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.frameBase=frameBase;
		setBackground(Color.GRAY);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel_oeste = new JPanel();
		panel_oeste.setBackground(Color.GRAY);
		add(panel_oeste);
		panel_oeste.setLayout(new BoxLayout(panel_oeste, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(Color.GRAY);
		panel_oeste.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblBuscarTitulo = new JLabel("Buscar t\u00EDtulo");
		lblBuscarTitulo.setForeground(Color.WHITE);
		lblBuscarTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_2.add(lblBuscarTitulo);
		
		campoBuscarTitulo = new JTextField();
		panel_2.add(campoBuscarTitulo);
		campoBuscarTitulo.setColumns(30);
		
		JButton btnBuscar = new JButton("Buscar");
		panel_2.add(btnBuscar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel.add(panel_3);
		
		JLabel lblAnadeEtiqueta = new JLabel("Introduce una etiqueta ");
		lblAnadeEtiqueta.setForeground(Color.WHITE);
		lblAnadeEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_3.add(lblAnadeEtiqueta);
		
		etiquetaPersonalizada = new JTextField();
		etiquetaPersonalizada.setColumns(15);
		panel_3.add(etiquetaPersonalizada);
		
		JButton btnAnadir = new JButton("A\u00F1adir");
		panel_3.add(btnAnadir);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(30);
		panel_3.add(horizontalStrut_1);
		
		JButton btnNuevaBusqueda = new JButton("Nueva b\u00FAsqueda");
		panel_3.add(btnNuevaBusqueda);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setForeground(Color.GRAY);
		panel.add(panel_1);
		
		JTable tablaVideos = new JTable();
		
		tablaVideos.setCellSelectionEnabled(true);
		tablaVideos.setBackground(Color.GRAY);
		tablaVideos.setDefaultRenderer(Object.class, new ImgTabla());
		CuartetoVideos gVideos = new CuartetoVideos();
		LinkedList<CuartetoVideos> listaCVideos = new LinkedList<CuartetoVideos>();
		ArrayList<Video> videosAux  = (ArrayList<Video>) frameBase.getAppVideo().obtenerVideos();
		
		
		listaCVideos.add(gVideos);
		TablaAbstract tm = new TablaAbstract();
		tm.rellenarTabla(videosAux, FrameBase.getVideoWeb());
		
		tablaVideos.setModel(tm);
		tablaVideos.setRowHeight(125); //cambio en la altura para que se vean los titulos
		tablaVideos.getTableHeader().setUI(null);  //Elimina la cabecera
		TableColumnModel colModel=tablaVideos.getColumnModel();
		for(int i=0; i<4; i++)
		{
			TableColumn col=colModel.getColumn(i);
			col.setPreferredWidth(145);
		}
		
		tablaVideos.setShowGrid(false);
		JScrollPane js=new JScrollPane(tablaVideos);
		panel_oeste.add(js);
		
		JPanel panel_este = new JPanel();
		panel_este.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_este.setBackground(Color.GRAY);
		add(panel_este);
		panel_este.setLayout(new BoxLayout(panel_este, BoxLayout.Y_AXIS));
		
		JLabel lblEtiquetasDisponibles = new JLabel("Etiquetas disponibles");
		lblEtiquetasDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblEtiquetasDisponibles.setForeground(Color.WHITE);
		lblEtiquetasDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_este.add(lblEtiquetasDisponibles);
		
		JList<String> lista = new JList<String>();
		lista.setBackground(Color.WHITE);
		lista.setVisibleRowCount(4);
		lista.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lista.setSelectedIndex(0);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (String item: this.frameBase.getAppVideo().getEtiquetasHabitualesS())
		{
			model.addElement(item);
		}
		lista.setModel(model);
		JScrollPane scroller = new JScrollPane(lista);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroller.setMaximumSize(new Dimension(500, 15000));
		panel_este.add(scroller);
		JLabel lblBuscarEtiquetas = new JLabel("Buscar etiquetas");
		lblBuscarEtiquetas.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarEtiquetas.setForeground(Color.WHITE);
		lblBuscarEtiquetas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_este.add(lblBuscarEtiquetas);
		
		JList<String> lista1 = new JList<String>();
		lista1.setPreferredSize(new Dimension(100, 200));
		lista1.setVisibleRowCount(5);
		lista1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lista1.setSelectedIndex(0);
		
		DefaultListModel<String> model1 = new DefaultListModel<String>();
		model1.addElement("");
		lista1.setModel(model1);
		
		
		JScrollPane scroller1 = new JScrollPane(lista1);
		scroller1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroller1.setMaximumSize(new Dimension(500, 15000));
		panel_este.add(scroller1);
		
		//listeners
		
		lista.addListSelectionListener(event->{
			if (!event.getValueIsAdjusting()){
				JList source = (JList)event.getSource();
				String selected = source.getSelectedValue().toString();
				Etiqueta nueva = PoolEtiquetas.INSTANCE.get(selected);
				if(!etiquetasSeleccionadas.contains(nueva))
				{
					etiquetasSeleccionadas.add(nueva);
					model1.addElement(selected);
					lista1.setModel(model1);
					panel_este.repaint();
					panel_este.revalidate();
					this.frameBase.validate();
				}
			}
		});
		
		btnNuevaBusqueda.addActionListener(ev -> {
			etiquetasSeleccionadas.clear();
			campoBuscarTitulo.setText("");
			int filas = tablaVideos.getRowCount();
			for (int i = filas-1; i >= 0; i--)
				tm.removeRow(i);
			model1.removeAllElements();
			model1.addElement("");
			lista1.setModel(model1);
			tm.fireTableDataChanged();
			panel_este.repaint();
			panel_este.revalidate();
			this.frameBase.validate();
		});
		
		btnAnadir.addActionListener(ev -> {
			String textoEtiquetaNueva = etiquetaPersonalizada.getText();
			Etiqueta etiquetaNueva = PoolEtiquetas.INSTANCE.get(textoEtiquetaNueva);
			etiquetaPersonalizada.setText("");
			if(!etiquetasSeleccionadas.contains(etiquetaNueva))
			{
				etiquetasSeleccionadas.add(etiquetaNueva);
				model1.addElement(textoEtiquetaNueva);
				lista1.setModel(model1);
				panel_este.repaint();
				panel_3.repaint();
				panel_este.revalidate();
				panel_3.revalidate();
				this.frameBase.validate();
			}
			
		});
		
		btnBuscar.addActionListener(event -> {
			String tituloVideo = campoBuscarTitulo.getText();
			if (!tituloVideo.equals("")) {
				List<Video> videoBuscado = this.frameBase.getAppVideo().buscarVideo(tituloVideo, etiquetasSeleccionadas);
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
			public void mouseClicked(MouseEvent e) {
				int fila = tablaVideos.rowAtPoint(e.getPoint());
				int columna = tablaVideos.columnAtPoint(e.getPoint());
				if(frameBase.getAppVideo().usuarioLogeado())frameBase.getAppVideo().addVideoRecientes(tm.getValueAt(fila, columna));
				frameBase.creaPanelReproduccion(frameBase.getPanelCentro(), new PanelReproduccion(frameBase, tm.getValueAt(fila, columna)));
				validate();
			}
		});
	
	} 
	
}