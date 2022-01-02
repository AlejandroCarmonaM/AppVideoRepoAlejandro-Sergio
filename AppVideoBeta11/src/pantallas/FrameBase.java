package pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dominio.AppVideo;
import dominio.Usuario;
import tds.video.VideoWeb;
import umu.tds.componente.VideoEvent;
import umu.tds.componente.Videos;
import umu.tds.componente.VideosListener;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.EventObject;
import java.awt.event.ActionEvent;

import pulsador.IEncendidoListener;
import pulsador.Luz;

public class FrameBase extends JFrame {

	private JPanel contentPane;
	private AppVideo appVideo;
	private JPanel panel_centro;
	private JLabel etiquetaUsuario = new JLabel("");
	private static VideoWeb vWeb = new VideoWeb();
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppVideo appVideo = AppVideo.INSTANCE;
					FrameBase frame = new FrameBase(appVideo);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public FrameBase(AppVideo appVideo) {
		//diseño frame
		this.setAppVideo(appVideo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setResizable(false); //con esto bloqueamos la pantalla a la resolución que queramos
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_norte = new JPanel();
		panel_norte.setBackground(Color.LIGHT_GRAY);
		this.getContentPane().add(panel_norte, BorderLayout.NORTH);
		panel_norte.setLayout(new BoxLayout(panel_norte, BoxLayout.Y_AXIS));
		
		JPanel panel_norte1 = new JPanel();
		panel_norte1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout_1 = (FlowLayout) panel_norte1.getLayout();
		flowLayout_1.setVgap(6);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setHgap(0);
		panel_norte.add(panel_norte1);
		
		JLabel lblAppVideo = new JLabel("AppVideo");
		lblAppVideo.setHorizontalAlignment(SwingConstants.LEFT);
		lblAppVideo.setForeground(Color.RED);
		lblAppVideo.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		panel_norte1.add(lblAppVideo);
		
		Component rigidArea_0 = Box.createRigidArea(new Dimension(70, 0));
		panel_norte1.add(rigidArea_0);
		
		this.actualizarEtiqueta();
		panel_norte1.add(etiquetaUsuario);
		
		Component rigidArea = Box.createRigidArea(new Dimension(70, 0));
		panel_norte1.add(rigidArea);
		
		JButton btnRegistro = new JButton("Registro");
		panel_norte1.add(btnRegistro);
		
		JButton btnLogin = new JButton("login");
		panel_norte1.add(btnLogin);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(30, 0));
		panel_norte1.add(rigidArea_1);
		
		JButton btnLogout = new JButton("logout");
		panel_norte1.add(btnLogout);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(40, 0));
		panel_norte1.add(rigidArea_2);
		
		JButton btnPremium = new JButton("Premium");
		btnPremium.setForeground(Color.RED);
		panel_norte1.add(btnPremium);
		
		JPanel panel_norte2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_norte2.getLayout();
		flowLayout.setHgap(-2);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_norte.add(panel_norte2);
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.setBackground(Color.LIGHT_GRAY);
		btnExplorar.setForeground(Color.WHITE);
		panel_norte2.add(btnExplorar);
		
		JButton btnMisListas = new JButton("Mis Listas");
		btnMisListas.setForeground(Color.WHITE);
		btnMisListas.setBackground(Color.LIGHT_GRAY);
		panel_norte2.add(btnMisListas);
		
		JButton btnRecientes = new JButton("Recientes");
		btnRecientes.setForeground(Color.WHITE);
		btnRecientes.setBackground(Color.LIGHT_GRAY);
		panel_norte2.add(btnRecientes);
		
		JButton btnNuevaLista = new JButton("Nueva Lista");
		btnNuevaLista.setForeground(Color.WHITE);
		btnNuevaLista.setBackground(Color.LIGHT_GRAY);
		panel_norte2.add(btnNuevaLista);
		
		JButton botonAnadirVideo = new JButton("A\u00F1adir Video");
		
		panel_norte2.add(botonAnadirVideo);
		
		Component horizontalStrut = Box.createHorizontalStrut(250);
		panel_norte2.add(horizontalStrut);
		
		Luz luz = new Luz();
		luz.setColor(Color.GREEN);
		panel_norte2.add(luz);
		
		JPanel panel_centro = new JPanel();
		
		
		panel_centro.setForeground(Color.GRAY);
		panel_centro.setBackground(Color.GRAY);
		this.getContentPane().add(panel_centro, BorderLayout.CENTER);
		panel_centro.setLayout(new BorderLayout(0, 0));
		this.setPanelCentro(panel_centro);
		
		//listeners
		
		luz.addEncendidoListener(ev->
		{
			JFileChooser fileChooser = new JFileChooser();
			int seleccion = fileChooser.showSaveDialog(this);
			if (seleccion == JFileChooser.APPROVE_OPTION)
			{
			   File fichero = fileChooser.getSelectedFile();
			   this.getAppVideo().cargarVideos(fichero);
			}
		});
		
		btnLogin.addActionListener(ev -> {
			PanelLoginProto panelLoginProto = new PanelLoginProto(this);
			this.creaPanel(panel_centro, panelLoginProto);
			panel_norte.repaint();
			panel_norte.revalidate();
			});
		
		btnLogout.addActionListener(ev -> {
			this.cargarPanelExplorar();
			this.appVideo.logOut();
			
			this.actualizarEtiqueta();
			panel_norte.repaint();
			panel_norte.revalidate();
			});
		
		btnExplorar.addActionListener(ev -> {
			this.cargarPanelExplorar();
			});
		
		btnRegistro.addActionListener(ev -> {
			PanelRegistro panelRegistro = new PanelRegistro(this);
			this.creaPanel(panel_centro, panelRegistro);
			});
		
		btnPremium.addActionListener(ev -> {
			if(this.getAppVideo().usuarioLogeado()) {
				PanelPremium panelPremium = new PanelPremium(this);
				this.creaPanel(panel_centro, panelPremium);
			}
			else
				JOptionPane.showMessageDialog(panel_centro, "Necesitas estar registrado");
			
			});
		
		btnNuevaLista.addActionListener(ev -> {
			PanelCrearLista panelCrearLista = new PanelCrearLista(this);
			this.creaPanel(panel_centro, panelCrearLista);
			});
		
		botonAnadirVideo.addActionListener(ev -> {
			PanelCargadorVideo panelCargadorVideo = new PanelCargadorVideo(this);
			this.creaPanel(panel_centro, panelCargadorVideo);
		});
		
		btnMisListas.addActionListener(ev -> {
			if (this.getAppVideo().usuarioLogeado()) {
				PanelMisListas panelMisListas = new PanelMisListas(this);
				this.creaPanel(panel_centro, panelMisListas);
			}
			else
				JOptionPane.showMessageDialog(panel_centro, "Necesitas estar registrado");
		});
		
		btnRecientes.addActionListener(ev -> {
			if (this.getAppVideo().usuarioLogeado()) {
				PanelRecientes panelRecientes = new PanelRecientes(this);
				this.creaPanel(panel_centro, panelRecientes);
			}
		});
	}
	
	public void actualizarEtiqueta() {
		String nombreUsuario = this.appVideo.getNombreUsuario();
		if(nombreUsuario!=null) etiquetaUsuario.setText("Hola "+nombreUsuario);
		else etiquetaUsuario.setText("Hola usuario"); 
	}

	//metodos getters y setters
	public AppVideo getAppVideo()
	{
		return this.appVideo;
	}
	
	private void setAppVideo(AppVideo appVideo)
	{
		this.appVideo=appVideo;
	}
	
	private void setPanelCentro(JPanel panel_centro)
	{
		this.panel_centro=panel_centro;
	}
	
	public JPanel getPanelCentro()
	{
		return this.panel_centro;
	}
	
	//metodo creaPanel
	
	public void creaPanel(JPanel panel_central, JPanel panel_nuevo)
	{
		FrameBase.getVideoWeb().cancel();
		panel_central.setVisible(false);
		panel_central.removeAll();
		panel_nuevo.setVisible(true);
		panel_central.add(panel_nuevo);
		panel_central.setVisible(true);
		panel_central.repaint();
		panel_central.revalidate();
		this.validate();
		
	}

	public static VideoWeb getVideoWeb() {
		return vWeb;
	}
	
	private void cargarPanelExplorar()
	{
		PanelExplorar panelExplorarBox = new PanelExplorar(this);
		this.creaPanel(panel_centro, panelExplorarBox);
	}
	
	public void creaPanelReproduccion(JPanel panel_central, JPanel panel_nuevo)
	{
		panel_central.setVisible(false);
		panel_central.removeAll();
		panel_nuevo.setVisible(true);
		panel_central.add(panel_nuevo);
		panel_central.setVisible(true);
		panel_central.repaint();
		panel_central.revalidate();
		this.validate();
		
	}
}
