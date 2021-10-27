package pantallas;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;

import dominio.AppVideo;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import dominio.Etiqueta;
import javax.swing.event.ListSelectionListener;

public class PanelExplorar extends JPanel {
	//atributos
	
	private JTextField textField;
	private FrameBase frameBase;
	private List<Etiqueta> etiquetasSeleccionadas= new LinkedList<Etiqueta>();  
	
	//constructor
	public PanelExplorar(FrameBase frameBase) {
		//creacion panel
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.frameBase=frameBase;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_este = new JPanel();
		panel_este.setBackground(Color.GRAY);
		add(panel_este, BorderLayout.EAST);
		panel_este.setLayout(new BoxLayout(panel_este, BoxLayout.Y_AXIS));
		
		JLabel lblEtiquetasDisponibles = new JLabel("Etiquetas disponibles");
		lblEtiquetasDisponibles.setForeground(Color.WHITE);
		lblEtiquetasDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_este.add(lblEtiquetasDisponibles);
		
		JList<String> lista = new JList<String>();
		lista.setPreferredSize(new Dimension(100, 200));
		//lista.setFixedCellWidth(0);
		lista.setVisibleRowCount(5);
		lista.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lista.setSelectedIndex(0);
		
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (String item: this.frameBase.getAppVideo().getEtiquetasHabitualesS())
		{
			model.addElement(item);
		}
		lista.setModel(model);
		JScrollPane scroller = new JScrollPane(lista);
		//scroller.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
		lista.setPrototypeCellValue("Alejandro");
		panel_este.add(scroller);
		
		
		
		JLabel lblBuscarEtiquetas = new JLabel("Buscar etiquetas:");
		lblBuscarEtiquetas.setForeground(Color.WHITE);
		lblBuscarEtiquetas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_este.add(lblBuscarEtiquetas);
		
		JList<String> lista1 = new JList<String>();
		lista1.setPreferredSize(new Dimension(100, 200));
		lista1.setVisibleRowCount(5);
		lista1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lista1.setSelectedIndex(0);
		
		DefaultListModel<String> model1 = new DefaultListModel<String>();
		lista.setModel(model);
		
		
		JScrollPane scroller1 = new JScrollPane(lista1);
		panel_este.add(scroller1);
		
		JPanel panel_oeste = new JPanel();
		panel_oeste.setBackground(Color.GRAY);
		//panel_oeste.setPreferredSize(null);
		add(panel_oeste, BorderLayout.WEST);
		panel_oeste.setLayout(new BoxLayout(panel_oeste, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
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
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(23);
		
		JButton btnBuscar = new JButton("Buscar");
		panel_2.add(btnBuscar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel.add(panel_3);
		
		JButton btnNuevaBusqueda = new JButton("Nueva b\u00FAsqueda");
		panel_3.add(btnNuevaBusqueda);
		
		
		JPanel panel_tabla_videos = new JPanel();
		panel_tabla_videos.setBackground(Color.GRAY);
		panel_oeste.add(panel_tabla_videos);
		
		Component verticalStrut = Box.createVerticalStrut(200);
		panel_tabla_videos.add(verticalStrut);
		
		Component horizontalStrut = Box.createHorizontalStrut(500);
		panel_oeste.add(horizontalStrut);
		
		JPanel panel_centro = new JPanel();
		panel_centro.setBackground(Color.RED);
		add(panel_centro, BorderLayout.CENTER);
		
		//listeners
		
		lista.addListSelectionListener(event->{
			if (!event.getValueIsAdjusting()){
				JList source = (JList)event.getSource();
				String selected = source.getSelectedValue().toString();
				if(!etiquetasSeleccionadas.contains(new Etiqueta(selected)))
				{
					etiquetasSeleccionadas.add(new Etiqueta(selected));
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
			textField.setText("");
			panel_tabla_videos.removeAll();
			panel_tabla_videos.add(verticalStrut);
			model1.removeAllElements();
			lista1.setModel(model1);
			panel_este.repaint();
			panel_este.revalidate();
			this.frameBase.validate();

			});
	}

}
