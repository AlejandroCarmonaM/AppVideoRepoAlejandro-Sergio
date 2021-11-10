package dominio;

public class NoFiltro implements FiltroVideo {

	@Override
	public boolean esVideoOK(Video v, Usuario usuario) {
		return true;
	}

}
