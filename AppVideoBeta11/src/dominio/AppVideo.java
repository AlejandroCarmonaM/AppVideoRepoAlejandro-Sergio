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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public enum AppVideo implements VideosListener {
	
	INSTANCE; //instancia del enumerado
	//private static final String[] ETIQUETAS = {"Videoclips", "Peliculas", "Series", "Comedia", "Drama"};
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
	
	private AppVideo() {
		cv = new CargadorVideos();
		cv.addSueldoListener(this);
		this.etiquetasHabituales = new LinkedList<String>();
		/*for(String etiqueta: ETIQUETAS)
		{
			etiquetasHabituales.add(etiqueta);
		}*/
		/*se hace asi porque no podemos tener un array de etiquetas estatico ya que 
		los campos estaticos se inicializan despues del constructor*/
		etiquetasHabituales.add("Videoclips");
		etiquetasHabituales.add("Peliculas");
		etiquetasHabituales.add("Series");
		etiquetasHabituales.add("Comedia");
		etiquetasHabituales.add("Drama");
		inicializarAdaptadores();
		inicializarCatalogos();
	}

	
	public boolean registrarUser(String nombre, Date fechaNacimiento, String nombreUsuario, String contrasena, String contrasenaRep, String apellidos, String email) {
		if(nombre.isBlank() || nombreUsuario.isBlank() || fechaNacimiento==null || contrasena.isBlank()) return false;
		if (!catalogoUsuario.existeUsuario(nombreUsuario) && (contrasena.equals(contrasenaRep))) {
			Usuario usuarioAux = new Usuario(nombre, fechaNacimiento, nombreUsuario, contrasena);
			if (!apellidos.isBlank())
				usuarioAux.setApellidos(apellidos);
			if (!email.isBlank())
				usuarioAux.setEmail(email);
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
	
	private String infoListas()
	{
		return this.usuario.getInfoListas();
	}
	
	public boolean generaPDF() throws FileNotFoundException
	{
		FileOutputStream archivo=null;
		try {
			archivo = new FileOutputStream("C:\\listasAppVideoDe_"+this.getUser().getUsuario()+".pdf");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	    Document documento = new Document();
	     try {
			PdfWriter.getInstance(documento, archivo);
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
	    documento.open();
	    try {
			documento.add(new Paragraph(this.infoListas()));
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
	    documento.close();
	    return true;
	}
	
	public void borrarFiltroUsuarioActivo()
	{
		Usuario usuarioActivo = this.getUser();
		if(usuarioActivo!=null) this.borrarFiltro(usuarioActivo.getFiltro());
	}
	
	public void borrarFiltro(Filtro filtro)
	{
		this.adaptadorFiltro.borrarFiltro(filtro);
	}

	public void setUsuarioNoPremium(Filtro noFiltro)
	{
		Usuario usuarioActivo = this.getUser();
		if(usuarioActivo!=null) {
			usuarioActivo.setPremium(false);
			this.registrarFiltro(noFiltro);
			usuarioActivo.setFiltro(noFiltro);
			this.modificarUsuarioAppVideo();
		}
	}
	
	public void cambioFiltroPremium(Filtro filtro) 
	{
		Usuario usuarioActivo = this.getUser();
		if(usuarioActivo!=null) {
			this.registrarFiltro(filtro);
			this.borrarFiltroUsuarioActivo();
			usuarioActivo.setPremium(true);
			usuarioActivo.setFiltro(filtro);
			this.modificarUsuarioAppVideo();
		}
	}
}
