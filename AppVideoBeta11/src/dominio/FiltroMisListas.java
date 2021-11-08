package dominio;

public class FiltroMisListas implements FiltroVideo {
	//la unica solucion que se me ocurre es que FmisListas conozca a usuario, pero entonces es bidireccional y necesitamos
	//hacer un pool.
	@Override
	public boolean esVideoOK(Video v) {
		// TODO Auto-generated method stub
		return false;
	}

}
