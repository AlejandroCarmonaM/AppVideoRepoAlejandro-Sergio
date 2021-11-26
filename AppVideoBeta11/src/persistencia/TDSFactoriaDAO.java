package persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO () {
	}
	
	@Override
	public IAdaptadorUsuarioDAO getUsuaioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorVideoDAO getVideoDAO() {
		return AdaptadorVideoTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorFiltroDAO getFiltroDAO() {
		return AdaptadorFiltroTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorListaVideosDAO getListaVideosDAO() {
		return AdaptadorListaVideosTDS.getUnicaInstancia();
	}
	
	@Override
	public IAdaptadorEtiquetaDAO getEtiquetaDAO() {
		return AdaptadorEtiquetaTDS.getUnicaInstancia();
	}
}