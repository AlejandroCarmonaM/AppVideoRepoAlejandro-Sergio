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
		/*List<Class<? extends dominio.Filtro>> ClassList = new ArrayList<Class<? extends dominio.Filtro>>(); 
		if (ClassList.isEmpty()) System.out.println("vacia"); -> preguntar profesor
		for (Class<? extends dominio.Filtro> clase: ClassList)
		{
			System.out.println(clase.toString());
			model.addElement(clase.toString());
		}*/
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
		this.add(scroller);
		
		JLabel lblFiltroActual = new JLabel("FiltroActual: "+filtroActual.getNombre());
		lblFiltroActual.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblFiltroActual);
		
		rdbtnPremium.addActionListener(ev ->{
			if (rdbtnPremium.isSelected()) this.frameBase.getAppVideo().getUser().setPremium(true);
			else {
				Filtro noFiltro = new NoFiltro();
				this.frameBase.getAppVideo().getUser().setPremium(false);
				this.frameBase.getAppVideo().getUser().setFiltro(noFiltro); //corregir cuando sea posible
				this.frameBase.getAppVideo().modificarUsuarioAppVideo();
				this.filtroActual=noFiltro;
				lblFiltroActual.setText("FiltroActual: "+filtroActual.getNombre());
				this.repaint();
				this.validate();
				this.frameBase.validate();
			}
		});
		
		lista.addListSelectionListener(event->{
			if (!event.getValueIsAdjusting()){
				JList source = (JList)event.getSource();
				String selected = source.getSelectedValue().toString();
				if(rdbtnPremium.isSelected())
				{
					try {
						//System.out.println("oooooooolaaaaaaa");
						Filtro filtro =(Filtro)Class.forName("dominio."+selected).getDeclaredConstructor().newInstance();
						//registrar filtro persistencia
						this.frameBase.getAppVideo().registrarFiltro(filtro); //funciona, pero poco eficiente
						this.frameBase.getAppVideo().getUser().setPremium(true);
						this.frameBase.getAppVideo().getUser().setFiltro(filtro); //corregir cuando sea posible
						this.frameBase.getAppVideo().modificarUsuarioAppVideo();
						this.filtroActual=filtro;
						lblFiltroActual.setText("FiltroActual: "+filtroActual.getNombre());
						this.repaint();
						this.validate();
						this.frameBase.validate();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
			}
		});

	}

}
