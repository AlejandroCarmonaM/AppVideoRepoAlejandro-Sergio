package dominio;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorEtiquetaDAO;
import persistencia.IAdaptadorFiltroDAO;
import persistencia.IAdaptadorListaVideosDAO;
import persistencia.IAdaptadorUsuarioDAO;
import persistencia.IAdaptadorVideoDAO;
import umu.tds.componente.CargadorVideos;
import umu.tds.componente.VideoEvent;
import umu.tds.componente.Videos;
import umu.tds.componente.VideosListener;

public class AppVideo implements VideosListener {
	
	private static final String[] ETIQUETAS = {"Videoclips", "Peliculas", "Series", "Comedia", "Drama"};
	private Usuario usuario;
	private List <String> etiquetasHabituales;
	private CargadorVideos cv;
	
	
	private IAdaptadorVideoDAO adaptadorVideo;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorFiltroDAO adaptadorFiltro;
	private IAdaptadorEtiquetaDAO adaptadorEtiqueta;
	private IAdaptadorListaVideosDAO adaptadorListaVideos;
	
	private CatalogoVideo catalogoVideo;
	private CatalogoUsuarios catalogoUsuario;
	
	public AppVideo() {
		cv = new CargadorVideos();
		cv.addSueldoListener(this);
		this.etiquetasHabituales = new LinkedList<String>();
		for(String etiqueta: ETIQUETAS)
		{
			etiquetasHabituales.add(etiqueta);
		}
		
		inicializarAdaptadores();
		inicializarCatalogos();
		
		//urls videos
		
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
		if(usuario!=null) catalogoUsuario.modificarUsuario(usuario);
	}
	
	public String getNombreUsuario() {
		if(usuario==null) return null;
		return usuario.getUsuario();
	}
		
	public Usuario getUser()
	{
		return this.usuario;
	}


	public boolean login(String usuario, String contrasena)
	{
		if(catalogoUsuario.isLoginOK(usuario, contrasena))
		{
			this.usuario = catalogoUsuario.getUsuario(usuario);
			return true;
			
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
	
	public List<Video> buscarVideo(String titulo) {
		return this.filtraVideos(catalogoVideo.buscarVideo(titulo));
	}
	public List<Video> buscarVideo(String titulo, Set<Etiqueta> etiquetas) {
		return this.filtraVideos(catalogoVideo.buscarVideo(titulo, etiquetas));
	}
	
	public List<String> obtenerURLs() {
		return catalogoVideo.obtenerURLs();
	}
	
	public List<Video> obtenerVideos() {
		return catalogoVideo.obtenerVideos();
	}
	
	public void registrarVideo(String nombre, String url, Collection<String> etiquetas) {
		// No se controla que el valor del string precio sea un double
		Video video = new Video(nombre, url);
		for(String e : etiquetas)
			video.anadirEtiqueta(e);
		adaptadorVideo.registrarVideo(video);

		catalogoVideo.addVideo(video);
	}
	
	//hay que registrar la lista de videos en este metodo
	public void anadirListaVideos(ListaVideos lv) {
		if (usuario != null)
			adaptadorListaVideos.registrarListaVideos(lv);
			this.usuario.addListaVideos(lv);
			this.modificarUsuarioAppVideo();
			
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
	
	public void addVideoRecientes(Video v) {
		if(usuario!=null) {
			this.modificarUsuarioAppVideo();
			this.usuario.addVideoRecientes(v);
		}
	}
	
	public boolean isPremium() {
		return this.usuario.isPremium();
	}
	
	public List<Video> getRecientes() {
		return this.usuario.getRecientes();
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
		adaptadorListaVideos = factoria.getListaVideosDAO();
		adaptadorEtiqueta = factoria.getEtiquetaDAO();
	}

	private void inicializarCatalogos() {
		catalogoVideo = CatalogoVideo.getUnicaInstancia();
		catalogoUsuario = CatalogoUsuarios.getUnicaInstancia();
	}


	public void registrarFiltro(Filtro filtro) {
		adaptadorFiltro.registrarFiltro(filtro);
	}
	
	public void registrarEtiqueta(Etiqueta e) {
		adaptadorEtiqueta.registrarEtiqueta(e);
	}


	public void logOut() {
		this.usuario=null;
	}
	
	private boolean filtraVideo(Video v)
	{
		if(usuario!=null)
		{
			return this.usuario.filtraVideo(v);
		}
		return true;
	}
	
	private List<Video> filtraVideos(List<Video> listaVideos)
	{
		return listaVideos.stream()
		.filter(vid->filtraVideo(vid))
		.collect(Collectors.toList());
		
	}


	public void modificarVideo(Video v) {
		this.adaptadorVideo.modificarVideo(v);
	}


	public void cargarVideos(File archivo)
	{
		this.cv.setArchivoVideos(archivo);
	}
	
	@Override
	public void enteradoCambioVideos(EventObject arg0) {
		VideoEvent ve = (VideoEvent) arg0;
		Videos videos = ve.getNuevoVideos();
		for(umu.tds.componente.Video v: videos.getVideo())
		{
			this.registrarVideo(v.getTitulo(), v.getURL(), v.getEtiqueta());
		}
	}
}
