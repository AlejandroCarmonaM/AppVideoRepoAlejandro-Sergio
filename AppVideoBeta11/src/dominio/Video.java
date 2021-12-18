package dominio;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
	
	public void anadirEtiquetas(List<Etiqueta> etiquetas) {
		etiquetas.stream()
		.forEach(e->this.etiquetas.add(e));
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
	
	public void setNumeroReproducciones(int numeroRep) {
		this.numRepro = numeroRep;
	}
	
	public void actualizarNumRepro()
	{
		this.numRepro++;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo, etiquetas, numRepro, titulo, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		return codigo == other.codigo && Objects.equals(etiquetas, other.etiquetas) && numRepro == other.numRepro
				&& Objects.equals(titulo, other.titulo) && Objects.equals(url, other.url);
	}

	public boolean isLargo(int limite)
	{
		return (this.titulo.length()<limite);
	}

	public boolean tieneEtiquetas(Set<Etiqueta> setE) {
		if (etiquetas.isEmpty()) return false;
		return etiquetas.stream()
		.anyMatch(setE::contains);
	}
	
	public String getInfoVideo()
	{
		return "\""+this.getTitulo()+"\", numero de reproducciones: "+this.getNumeroReproducciones()+"\n";
	}
}
