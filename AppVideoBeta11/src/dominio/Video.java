package dominio;

import java.util.HashSet;

public class Video {
	private String titulo;
	private HashSet<String> etiquetas;
	private String url;
	
	public String getTitulo() {
		return titulo;
	}
	
	public HashSet<String> getEtiquetas() {
		HashSet<String> aux = new HashSet<String>(etiquetas);
		return aux;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void anadirEtiqueta(String nueva) {
		this.etiquetas.add(nueva);
	}
	
	public Video(String titulo, String url) {
		this.titulo = titulo;
		this.etiquetas = new HashSet<String>();
		this.url = url;
	}
}
