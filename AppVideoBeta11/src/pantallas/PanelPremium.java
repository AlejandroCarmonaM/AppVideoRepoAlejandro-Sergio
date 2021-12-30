package pantallas;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import dominio.Etiqueta;
import dominio.Filtro;
import dominio.FiltroMisListas;
import dominio.FiltroNombresLargos;
import dominio.NoFiltro;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import pulsador.Luz;

public class PanelPremium extends JPanel {
	private FrameBase frameBase;
	private Filtro filtroActual;
	
	public PanelPremium(FrameBase frameBase) {
		this.frameBase= frameBase;
		filtroActual= this.frameBase.getAppVideo().getUser().getFiltro(); //si hay que evitar clases dios 
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblPremium = new JLabel("Premium");
		lblPremium.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPremium.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPremium);
		
		JRadioButton rdbtnPremium = new JRadioButton("On");
		
		if(this.frameBase.getAppVideo().getUser().isPremium()) rdbtnPremium.setSelected(true);
		add(rdbtnPremium);
		
		JList<String> lista = new JList<String>();
		lista.setVisibleRowCount(4);
		lista.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		List<String> filtros = new LinkedList<String>();
		filtros.add(FiltroNombresLargos.class.getSimpleName());
		filtros.add(FiltroMisListas.class.getSimpleName());
		for(String f: filtros)
		{
			model.addElement(f);
		}
		lista.setModel(model);
		JScrollPane scroller = new JScrollPane(lista);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroller.setMaximumSize(new Dimension(500, 15000));
		scroller.setVisible(frameBase.getAppVideo().getUser().isPremium());
		this.add(scroller);
		
		JLabel lblFiltroActual = new JLabel("FiltroActual: "+filtroActual.getNombre().substring(8));
		lblFiltroActual.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblFiltroActual);
		
		JButton btnGenerarPDF = new JButton("Generar PDF listas");
		btnGenerarPDF.setForeground(Color.WHITE);
		btnGenerarPDF.setBackground(Color.RED);
		btnGenerarPDF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerarPDF.setHorizontalAlignment(SwingConstants.RIGHT);
		add(btnGenerarPDF);
		
		
		rdbtnPremium.addActionListener(ev ->{
			if (rdbtnPremium.isSelected()) {
				this.frameBase.getAppVideo().getUser().setPremium(true);
				scroller.setVisible(true);
				this.repaint();
				this.validate();
				this.frameBase.validate();
			}
			else {
				scroller.setVisible(false);
				this.frameBase.getAppVideo().getUser().setPremium(false);
				Filtro noFiltro = new NoFiltro();
				this.frameBase.getAppVideo().registrarFiltro(noFiltro);
				this.frameBase.getAppVideo().getUser().setFiltro(noFiltro);
				this.frameBase.getAppVideo().modificarUsuarioAppVideo();
				this.filtroActual=noFiltro;
				lblFiltroActual.setText("FiltroActual: "+filtroActual.getNombre().substring(8));
				this.repaint();
				this.validate();
				this.frameBase.validate();
			}
		});
		
		btnGenerarPDF.addActionListener(ev->{
			if(this.frameBase.getAppVideo().generaPDF()) {
				btnGenerarPDF.setBackground(Color.GREEN);
				this.validate();
			};
		});
		
		lista.addListSelectionListener(event->{
			if (!event.getValueIsAdjusting()){
				JList source = (JList)event.getSource();
				String selected = source.getSelectedValue().toString();
				
					try {
						Filtro filtro =(Filtro)Class.forName("dominio."+selected).getDeclaredConstructor().newInstance();
						//registrar filtro persistencia
						this.frameBase.getAppVideo().registrarFiltro(filtro); //funciona, pero poco eficiente
						//pd:es completamente necesario tener el filtro del usuario registrado en persistencia
						//deberiamos eliminar el filtro anterior?
						this.frameBase.getAppVideo().getUser().setPremium(true);
						this.frameBase.getAppVideo().getUser().setFiltro(filtro); //corregir cuando sea posible
						this.frameBase.getAppVideo().modificarUsuarioAppVideo();
						this.filtroActual=filtro;
						lblFiltroActual.setText("FiltroActual: "+selected);
						this.repaint();
						this.validate();
						this.frameBase.validate();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
			});

	}

}
