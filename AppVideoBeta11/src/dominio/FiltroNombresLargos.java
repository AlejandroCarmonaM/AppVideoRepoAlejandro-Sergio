package dominio;

public class FiltroNombresLargos implements FiltroVideo {

	@Override
	public boolean esVideoOK(Video v) {
		return (v.isLargo(17));
	}

}
