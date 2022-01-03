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
	
	public String getInfoLista()
	{
		String infoLista =this.getNombre()+"\n";
		for(Video v: listaVideos)
		{
			infoLista+=v.getInfoVideo();
		}
		infoLista+="\n";
		return infoLista;
	}
	public void modificarListaVideos(LinkedList<Video> lv) {
        //this.listaVideos.clear();
        /*for(Video elemento : lv)
            this.listaVideos.add(elemento);*/
        this.listaVideos = lv;
    }
}
