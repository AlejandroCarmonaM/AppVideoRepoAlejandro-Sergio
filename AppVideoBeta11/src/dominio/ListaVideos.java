package dominio;

import java.util.LinkedList;

public class ListaVideos {
	String nombre;
	int codigo = 0;
	LinkedList<Video> listaVideos;
	
	public ListaVideos(String nombre) {
		this.nombre = nombre;
		this.listaVideos = new LinkedList<Video>();
	}
	
	public String getNombre() {
		return nombre;
	}
	public LinkedList<Video> getListaVideos() {
		LinkedList<Video> copia = new LinkedList<Video>(listaVideos);
		return copia;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void addVideo(Video video) {
		listaVideos.add(video);
	}
	
}
