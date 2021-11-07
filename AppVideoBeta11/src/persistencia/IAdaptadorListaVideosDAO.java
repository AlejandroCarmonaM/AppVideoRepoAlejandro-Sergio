package persistencia;

import java.util.List;

import dominio.ListaVideos;

public interface IAdaptadorListaVideosDAO {
	public void registrarListaVideos(ListaVideos lv);
	public void borrarListaVideos(ListaVideos lv);
	public void modificarListaVideos(ListaVideos lv);
	public ListaVideos recuperarListaVideos(int codigo);
	public List<ListaVideos> recuperarTodasListasVideos();
}
