package dominio;

public class FiltroNombresLargos implements Filtro {

	int codigo;
	String nombre = this.getClass().getName();
	
	public FiltroNombresLargos() {
		this.codigo = 0;
	}

	@Override
	public boolean esVideoOK(Video v, Usuario usuario) {
		return (v.isLargo(17));
	}

	@Override
	public int getCodigo() {
		return codigo;
	}

	@Override
	public void setCodigo(int codigo) {
		this.codigo=codigo;
		
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

	/*@Override
	public void setNombre(String nombre) {
		this.nombre=nombre;
		
	}*/
}
