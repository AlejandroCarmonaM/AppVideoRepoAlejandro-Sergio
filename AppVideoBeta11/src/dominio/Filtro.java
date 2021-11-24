package dominio;

public interface Filtro {
	
	public boolean esVideoOK(Video v, Usuario usuario);
	public int getCodigo();
	public void setCodigo(int codigo);
	//el nombre es la clase del filtro
	public String getNombre();
	//public void setNombre(String nombre);

}
