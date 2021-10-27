package dominio;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pantallas.FrameBase;

public class AppVideo {
	
	private static final String[] ETIQUETAS = {"Videoclips", "Peliculas", "Series"};
	private String usuario;
	private List <String> etiquetasHabituales;
	private RepositorioUsuarios ruf;
	private RepositorioUsuarios repositoPrueba = new RepositorioUsuarios();
	private JPanel panelCentro;
	private FrameBase frameBase;
	private JLabel etiqueta;
	
	
	public AppVideo() {
		this.usuario = "usuario";
		this.etiquetasHabituales = new LinkedList<String>();
		ruf = new RepositorioUsuarios();
		for(String etiqueta: ETIQUETAS)
		{
			etiquetasHabituales.add(etiqueta);
		}
	}

	
	public boolean registrarUser(String nombre, String fechaNacimiento, String nombreUsuario, String contrasena, String contrasenaRep) {
		if (repositoPrueba.registrarUsuario(nombre, fechaNacimiento, nombreUsuario, contrasena, contrasenaRep))
			return true;
		return false;
	}
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario)
	{
		this.usuario=usuario;
	}


	public boolean login(String usuario, String contrasena)
	{
		if(ruf.isLoginOK(usuario, contrasena))
		{
			this.usuario=usuario;
			return true;
			//PanelPrueba panel_prueba = new PanelPrueba();
			//CreadorPaneles.creaPanel(panel_centro_central, panel_prueba);
			//esto funciona
			
		}
		else {
			//JOptionPane.showMessageDialog(panel_centro_central, "Login rechazado", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public List<String> getEtiquetasHabitualesS()
	{
		return etiquetasHabituales;
	}
	
	public void setPanelCentro(JPanel panelCentro)
	{
		this.panelCentro= panelCentro;
	}
	
	/*public JPanel getPanelCentro()
	{
		return this.panelCentro;
	}
	
	public void setFrameBase(FrameBase frameBase)
	{
		this.frameBase= frameBase;
	}
	
	public FrameBase getFrameBase()
	{
		return this.frameBase;
	}*/
	
	public JLabel creaEtiqueta()
	{
		JLabel lblLogin = new JLabel("Hola Usuario");
		etiqueta = lblLogin;
		return etiqueta;
	}
	
	public void setEtiqueta(String usuario)
	{
		etiqueta.setText("Hola "+usuario);
	}

}
