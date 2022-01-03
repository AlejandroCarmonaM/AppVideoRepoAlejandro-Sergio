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
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import pulsador.Luz;
import javax.swing.border.BevelBorder;

public class PanelPremium extends JPanel {
	private FrameBase frameBase;
	private Filtro filtroActual;
	
	public PanelPremium(FrameBase frameBase) {
		setBackground(Color.GRAY);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.frameBase= frameBase;
		filtroActual= this.frameBase.getAppVideo().getUser().getFiltro(); //si hay que evitar clases dios 
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblPremium = new JLabel("Premium");
		lblPremium.setForeground(Color.WHITE);
		lblPremium.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPremium.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPremium);
		
		JRadioButton rdbtnPremium = new JRadioButton("On");
		rdbtnPremium.setBackground(Color.GRAY);
		
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
		lblFiltroActual.setForeground(Color.WHITE);
		lblFiltroActual.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblFiltroActual);
		
		JButton btnGenerarPDF = new JButton("Generar PDF listas");
		btnGenerarPDF.setVisible(frameBase.getAppVideo().getUser().isPremium());
		btnGenerarPDF.setForeground(Color.WHITE);
		btnGenerarPDF.setBackground(Color.RED);
		btnGenerarPDF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerarPDF.setHorizontalAlignment(SwingConstants.RIGHT);
		add(btnGenerarPDF);
		
		JButton btnTopTen = new JButton("Crear lista Top ten");
		btnTopTen.setBackground(Color.RED);
		btnTopTen.setForeground(Color.WHITE);
		add(btnTopTen);
		btnTopTen.setVisible(frameBase.getAppVideo().getUser().isPremium());
		
		
		rdbtnPremium.addActionListener(ev ->{
			if (rdbtnPremium.isSelected()) {
				this.frameBase.getAppVideo().getUser().setPremium(true);
				scroller.setVisible(true);
				btnGenerarPDF.setVisible(true);
				btnTopTen.setVisible(true);
				this.repaint();
				this.validate();
				this.frameBase.validate();
			}
			else {
				scroller.setVisible(false);
				btnGenerarPDF.setVisible(false);
				btnTopTen.setVisible(false);
				Filtro noFiltro = new NoFiltro();
				this.frameBase.getAppVideo().setUsuarioNoPremium(noFiltro);
				this.filtroActual=noFiltro;
				lblFiltroActual.setText("FiltroActual: "+filtroActual.getNombre().substring(8));
				this.repaint();
				this.validate();
				this.frameBase.validate();
			}
		});
		
		btnGenerarPDF.addActionListener(ev->{
			try {
				if(this.frameBase.getAppVideo().generaPDF()) {
					btnGenerarPDF.setBackground(Color.GREEN);
					this.validate();
				}
			} catch (FileNotFoundException excepcionAdministrador) {
				excepcionAdministrador.printStackTrace();
			}
			finally {
				if(btnGenerarPDF.getBackground().equals(Color.RED)) {
					JOptionPane.showMessageDialog(this, "No tienes permisos para escribir en C:");
				}
				else
					JOptionPane.showMessageDialog(this, "PDF creado en C:");
			};
		});
		
		btnGenerarPDF.addActionListener(ev->{
			try {
				if(this.frameBase.getAppVideo().generaPDF()) {
					btnGenerarPDF.setBackground(Color.GREEN);
					this.validate();
				}
			} catch (FileNotFoundException excepcionAdministrador) {
				excepcionAdministrador.printStackTrace();
			}
			finally {
				if(btnGenerarPDF.getBackground().equals(Color.RED)) {
					JOptionPane.showMessageDialog(this, "No tienes permisos para escribir en C:");
				}
				else
					JOptionPane.showMessageDialog(this, "PDF creado en C:");
			};
		});
		
		btnTopTen.addActionListener(ev->{
			this.frameBase.getAppVideo().generarTopTen();
		});
		
		lista.addListSelectionListener(event->{
			if (!event.getValueIsAdjusting()){
				JList source = (JList)event.getSource();
				String selected = source.getSelectedValue().toString();
				
					try {
						Filtro filtro =(Filtro)Class.forName("dominio."+selected).getDeclaredConstructor().newInstance();
						this.frameBase.getAppVideo().cambioFiltroPremium(filtro); //metodo que cambia el filtro antiguo por el nuevo
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
