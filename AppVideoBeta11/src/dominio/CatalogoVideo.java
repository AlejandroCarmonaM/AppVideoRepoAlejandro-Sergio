package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
	
	public List<Video> buscarVideoUsuario(String titulo, Usuario usuario) {
		long coincidencias = this.getVideos().stream()
								.filter(vid -> vid.getTitulo().contains(titulo)
										&& usuario.getFiltro().esVideoOK(vid, usuario))
								.count();
		if (coincidencias > 0)
			return this.getVideos().stream()
					.filter(vid -> vid.getTitulo().contains(titulo)
							&& usuario.getFiltro().esVideoOK(vid, usuario))
					.collect(Collectors.toList());
		return null;
	}
	
	public List<Video> buscarVideo(String titulo) {
		long coincidencias = this.getVideos().stream()
								.filter(vid -> vid.getTitulo().contains(titulo))
								.count();
		if (coincidencias > 0)
			return this.getVideos().stream()
					.filter(vid -> vid.getTitulo().contains(titulo))
					.collect(Collectors.toList());
		return null;
	}
	
	public List<Video> buscarVideo(String titulo, Set<Etiqueta> etiquetas) {
		if (etiquetas.isEmpty()) return buscarVideo(titulo); 
		long coincidencias = this.getVideos().stream()
								.filter(vid -> vid.getTitulo().contains(titulo) 
										&& vid.tieneEtiquetas(etiquetas))
								.count();
		if (coincidencias > 0)
			return this.getVideos().stream()
					.filter(vid -> vid.getTitulo().contains(titulo)&& vid.tieneEtiquetas(etiquetas))
					.collect(Collectors.toList());
		return null;
	}
	
	
	public List<Video> buscarVideoUsuario(String titulo, Usuario usuario, Set<Etiqueta> etiquetas) {
		if (etiquetas.isEmpty()) return buscarVideoUsuario(titulo, usuario); 
		long coincidencias = this.getVideos().stream()
								.filter(vid -> vid.getTitulo().contains(titulo) 
										&& vid.tieneEtiquetas(etiquetas)&& 
										usuario.getFiltro().esVideoOK(vid, usuario))
								.count();
		if (coincidencias > 0)
			return this.getVideos().stream()
					.filter(vid -> vid.getTitulo().contains(titulo)&& vid.tieneEtiquetas(etiquetas)
							&& usuario.getFiltro().esVideoOK(vid, usuario))
					.collect(Collectors.toList());
		return null;
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
	
}