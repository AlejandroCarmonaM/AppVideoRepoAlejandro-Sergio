package dominio;

public class FiltroMisListas implements Filtro {
	//la unica solucion que se me ocurre es que FmisListas conozca a usuario, pero entonces es bidireccional y necesitamos
	//hacer un pool.
	int codigo;
	String nombre = this.getClass().getName();
	
	public FiltroMisListas() {
		this.codigo = 0; 
	}

	@Override
	public boolean esVideoOK(Video v, Usuario usuario) {
		return !usuario.isVideoListas(v);
	}

	@Override
	public int getCodigo() {
		// TODO Auto-generated method stub
		return codigo;
	}

	@Override
	public void setCodigo(int codigo) {
		// TODO Auto-generated method stub
		this.codigo=codigo;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

}
