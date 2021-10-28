package dominio;

import java.util.HashSet;

public class RepositorioVideo {
	private HashSet<Video> repositorio;
	
	public Video buscarVideo(String titulo) {
		return repositorio.stream()
				.filter(elem -> elem.getTitulo().equals(titulo))
				.findFirst()
				.get();
	}
	
	public void anadirVideo(String titulo, String url) {
		Video nuevoVideo = new Video(titulo, url);
		repositorio.add(nuevoVideo);
	}
	
	public RepositorioVideo() {
		this.repositorio = new HashSet<Video>();
	}
}
