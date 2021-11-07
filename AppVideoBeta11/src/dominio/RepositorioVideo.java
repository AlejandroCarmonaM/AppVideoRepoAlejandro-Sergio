package dominio;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class RepositorioVideo {
	private LinkedList<Video> repositorio;
	
	public List<Video> buscarVideo(String titulo) {
		long coincidencias = repositorio.stream()
								.filter(vid -> vid.getTitulo().equals(titulo))
								.count();
		if (coincidencias > 0)
			return repositorio.stream()
					.filter(elem -> elem.getTitulo().equals(titulo))
					.collect(Collectors.toList());
		return null;
	}
	
	public List<String> obtenerURLs() {
		return repositorio.stream()
				.map(vid -> vid.getUrl())
				.collect(Collectors.toList());
	}
	
	public void anadirVideo(String titulo, String url) {
		Video nuevoVideo = new Video(titulo, url);
		repositorio.add(nuevoVideo);
	}
	
	public RepositorioVideo() {
		this.repositorio = new LinkedList<Video>();
	}
}
