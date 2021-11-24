package dominio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pantallas.FrameBase;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorFiltroDAO;
import persistencia.IAdaptadorUsuarioDAO;
import persistencia.IAdaptadorVideoDAO;

public class AppVideo {
	
	private static final String[] ETIQUETAS = {"Videoclips", "Peliculas", "Series"};
	private String nombreUsuario;
	private Usuario usuario;
	private List <String> etiquetasHabituales;
	private RepositorioUsuarios ruf;
	private RepositorioUsuarios repositoPrueba = new RepositorioUsuarios();
	private JPanel panelCentro;
	private FrameBase frameBase;
	private JLabel etiqueta;
	//private RepositorioVideo repositorioVideoPrueba = new RepositorioVideo();
	
	
	private IAdaptadorVideoDAO adaptadorVideo;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorFiltroDAO adaptadorFiltro;
	
	private CatalogoVideo catalogoVideo;
	private CatalogoUsuarios catalogoUsuario;
	
	public AppVideo() {
		this.nombreUsuario = "usuario";
		this.etiquetasHabituales = new LinkedList<String>();
		ruf = new RepositorioUsuarios();
		for(String etiqueta: ETIQUETAS)
		{
			etiquetasHabituales.add(etiqueta);
		}
		
		inicializarAdaptadores();
		inicializarCatalogos();
		
		/*repositorioVideoPrueba.anadirVideo("primerVideo", "https://www.youtube.com/watch?v=rk7ITikbhs4");
		repositorioVideoPrueba.anadirVideo("primerVideo", "https://www.youtube.com/watch?v=rk7ITikbhs4");
		repositorioVideoPrueba.anadirVideo("Segundo video", "https://www.youtube.com/watch?v=EdVMSYomYJY");
		repositorioVideoPrueba.anadirVideo("Tercer video", "https://www.youtube.com/watch?v=0243Z0YXPpY");
		repositorioVideoPrueba.anadirVideo("Cuarto video", "https://www.youtube.com/watch?v=UtF6Jej8yb4");
		repositorioVideoPrueba.anadirVideo("Quinto video", "https://www.youtube.com/watch?v=WQo9cHP7MIc");
		repositorioVideoPrueba.anadirVideo("Sexto video", "https://www.youtube.com/watch?v=WQo9cHP7MIc");
		repositorioVideoPrueba.anadirVideo("Septimo video", "https://www.youtube.com/watch?v=WQo9cHP7MIc");
		repositorioVideoPrueba.anadirVideo("Octabo video", "https://www.youtube.com/watch?v=0243Z0YXPpY",new Etiqueta("Series"));
		repositorioVideoPrueba.anadirVideo("Noveno video", "https://www.youtube.com/watch?v=WQo9cHP7MIc", new Etiqueta("Series"));*/
	}

	
	public boolean registrarUser(String nombre, String fechaNacimiento, String nombreUsuario, String contrasena, String contrasenaRep) {
		if (!catalogoUsuario.existeUsuario(nombreUsuario) && (contrasena.equals(contrasenaRep))) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha;
			try {
				fecha = formato.parse(fechaNacimiento);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return false;
			}
			Usuario usuarioAux = new Usuario(nombre, fecha, nombreUsuario, contrasena);
			adaptadorUsuario.registrarUsuario(usuarioAux);
			catalogoUsuario.registrarUsuario(usuarioAux);
			return true;
		}
		return false;
	}
	
	public void modificarUsuarioAppVideo()
	{
		catalogoUsuario.modificarUsuario(usuario);
	}
	
	public String getUsuario() {
		return nombreUsuario;
	}
	
	public void setUsuario(String usuario)
	{
		this.nombreUsuario=usuario;
	}
	
	public Usuario getUser()
	{
		return this.usuario;
	}


	public boolean login(String usuario, String contrasena)
	{
		if(catalogoUsuario.isLoginOK(usuario, contrasena))
		{
			System.out.println("cacatua");
			this.nombreUsuario=usuario;
			this.usuario = catalogoUsuario.getUsuario(usuario); //esto no se si puede causar que appVideo no tenga el usuario
			//despues de hacer un registro porque me dice que el user de appvideo es null en un momento
			if(this.usuario==null) System.out.println("null");
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
		JLabel lblLogin = new JLabel("Hola "+nombreUsuario);
		etiqueta = lblLogin;
		return etiqueta;
	}
	
	public void setEtiqueta(String usuario)
	{
		etiqueta.setText("Hola "+usuario);
	}
	
	public List<Video> buscarVideo(String titulo) {
		return catalogoVideo.buscarVideo(titulo);
	}
	public List<Video> buscarVideo(String titulo, Set<Etiqueta> etiquetas) {
		return catalogoVideo.buscarVideo(titulo, etiquetas);
	}
	
	public List<String> obtenerURLs() {
		return catalogoVideo.obtenerURLs();
	}
	
	public List<Video> obtenerVideos() {
		return catalogoVideo.obtenerVideos();
	}
	
	public void registrarVideo(String nombre, String url, Set<String> etiquetas) {
		// No se controla que el valor del string precio sea un double
		Video video = new Video(nombre, url);
		for(String e : etiquetas)
			video.anadirEtiqueta(e);
		adaptadorVideo.registrarVideo(video);

		catalogoVideo.addVideo(video);
	}
	
	public void anadirListaVideos(ListaVideos lv) {
		if (usuario != null)
			this.usuario.addListaVideos(lv);
	}
	
	public List<String> getNombreMisListas() {
		if (usuario != null)
			return this.usuario.getNombreMisListas();
		return new LinkedList<String>();
	}
	
	public boolean usuarioLogeado() {
		if (usuario != null)
			return true;
		return false;
	}
	
	public ListaVideos getListaVideosPorNombre(String nombreLista) {
		return this.usuario.getListaVideosPorNombre(nombreLista);
	}
	
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorVideo = factoria.getVideoDAO();
			adaptadorUsuario = factoria.getUsuaioDAO();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorVideo = factoria.getVideoDAO();
		adaptadorUsuario = factoria.getUsuaioDAO();
		adaptadorFiltro = factoria.getFiltroDAO();
	}

	private void inicializarCatalogos() {
		catalogoVideo = CatalogoVideo.getUnicaInstancia();
		catalogoUsuario = CatalogoUsuarios.getUnicaInstancia();
	}


	public void registrarFiltro(Filtro filtro) {
		adaptadorFiltro.registrarFiltro(filtro);
	}
	
}
