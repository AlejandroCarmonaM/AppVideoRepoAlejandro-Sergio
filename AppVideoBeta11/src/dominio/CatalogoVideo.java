package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorVideoDAO;

public class CatalogoVideo {
	private Map<String,Video> videos; 
	private static CatalogoVideo unicaInstancia = new CatalogoVideo();
	//
	
	private FactoriaDAO dao;
	private IAdaptadorVideoDAO adaptadorVideo;
	
	private CatalogoVideo() {
		try {
  			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
  			adaptadorVideo = dao.getVideoDAO();
  			videos = new HashMap<String,Video>();
  			this.cargarCatalogoVideos();
  		} catch (DAOException eDAO) {
  			eDAO.printStackTrace();
  		}
	}
	
	public static CatalogoVideo getUnicaInstancia(){
		return unicaInstancia;
	}
	
	public List<Video> getVideos(){
		ArrayList<Video> lista = new ArrayList<Video>();
		for (Video v : videos.values()) 
			lista.add(v);
		return lista;
	}
	
	public Video getVideo(int codigo) {
		for (Video v : videos.values()) {
			if (v.getCodigo()==codigo) return v;
		}
		return null;
	}
	
	public Video getVideo(String nombre) {
		return videos.get(nombre); 
	}
	
	public void addVideo(Video v) {
		videos.put(v.getTitulo(),v);
	}
	
	public void removeVideo(Video v) {
		videos.remove(v.getTitulo());
	}
	
	/*Recupera todos los Productos para trabajar con ellos en memoria*/
	private void cargarCatalogoVideos() throws DAOException {
		 List<Video> videosBD = adaptadorVideo.recuperarTodosVideo();
		 for (Video v: videosBD) 
			     videos.put(v.getTitulo(),v);
	}
	
	public List<Video> buscarVideo(String titulo) {
		
		List<Video> listaVideos =this.getVideos().stream()
			.filter(vid -> vid.getTitulo().contains(titulo))
			.collect(Collectors.toList());
		return listaVideos;
	}
	
	public List<Video> buscarVideo(String titulo, Set<Etiqueta> etiquetas) {
		if (etiquetas.isEmpty()) return buscarVideo(titulo); 
		List<Video> listaVideos =this.getVideos().stream()
			.filter(vid -> vid.getTitulo().contains(titulo)&& vid.tieneEtiquetas(etiquetas))
			.collect(Collectors.toList());
		return listaVideos;
		
	}
	
	
	public List<String> obtenerURLs() {
		return this.getVideos().stream()
				.map(vid -> vid.getUrl())
				.collect(Collectors.toList());
	}
	
	public List<Video> obtenerVideos() {
		return this.getVideos();
	}
	
	public void anadirVideo(String titulo, String url) {
		Video nuevoVideo = new Video(titulo, url);
		this.getVideos().add(nuevoVideo);
	}
	
	public void anadirVideo(String titulo, String url, Etiqueta...etiquetas) {
		Video nuevoVideo = new Video(titulo, url);
		for(Etiqueta e: etiquetas)
		{
			nuevoVideo.anadirEtiqueta(e);
		}
		this.getVideos().add(nuevoVideo);
	}
	
	public LinkedList<Video> getTopTen()
	{
		Supplier<LinkedList<Video>> supplier = () -> new LinkedList<Video>();
		
		return videos.values().stream()
		.sorted((v1, v2)->v2.getNumeroReproducciones()-v1.getNumeroReproducciones())
		.limit(10)
		.collect(Collectors.toCollection(supplier));
	}
	
}