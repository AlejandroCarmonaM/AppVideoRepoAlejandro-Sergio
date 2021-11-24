package persistencia;

import java.util.List;

import dominio.Filtro;
import dominio.ListaVideos;

public interface IAdaptadorFiltroDAO {
	public void registrarFiltro(Filtro filtro);
	public void borrarFiltro(Filtro filtro);
	public void modificarFiltro(Filtro filtro);
	public Filtro recuperarFiltro(int codigo);
	public List<Filtro> recuperarTodosFiltros();
}
