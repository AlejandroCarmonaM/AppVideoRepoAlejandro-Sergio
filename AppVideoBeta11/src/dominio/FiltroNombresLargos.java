package dominio;

public class FiltroNombresLargos implements FiltroVideo {

	@Override
	public boolean esVideoOK(Video v, Usuario usuario) {
		return (v.isLargo(17));
	}

}
