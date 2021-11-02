package dominio;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RepositorioVideo {
	private HashSet<Video> repositorio;
	
	public Video buscarVideo(String titulo) {
		long coincidencias = repositorio.stream()
								.filter(vid -> vid.getTitulo().equals(titulo))
								.count();
		if (coincidencias > 0)
			return repositorio.stream()
					.filter(elem -> elem.getTitulo().equals(titulo))
					.findFirst()
					.get();
		return null;
	}
	
	public Set<String> obtenerURLs() {
		return repositorio.stream()
				.map(vid -> vid.getUrl())
				.collect(Collectors.toSet());
	}
	
	public void anadirVideo(String titulo, String url) {
		Video nuevoVideo = new Video(titulo, url);
		repositorio.add(nuevoVideo);
	}
	
	public RepositorioVideo() {
		this.repositorio = new HashSet<Video>();
	}
}
