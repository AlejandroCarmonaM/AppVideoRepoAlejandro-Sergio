package dominio;

import java.util.HashSet;

public class Video {

	private int codigo = 0;
	private String titulo;
	private HashSet<Etiqueta> etiquetas;
	private String url;
	private int numRepro;
	
	public String getTitulo() {
		return titulo;
	}
	
	public HashSet<Etiqueta> getEtiquetas() {
		HashSet<Etiqueta> aux = new HashSet<Etiqueta>(etiquetas);
		return aux;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void anadirEtiqueta(String nueva) {
		this.etiquetas.add(new Etiqueta(nueva));
	}
	
	public void anadirEtiqueta(Etiqueta nueva) {
		this.etiquetas.add(nueva);
	}
	
	public Video(String titulo, String url) {
		this.titulo = titulo;
		this.etiquetas = new HashSet<Etiqueta>();
		this.url = url;
		this.numRepro = 0;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public int getNumeroReproducciones() {
		return numRepro;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
