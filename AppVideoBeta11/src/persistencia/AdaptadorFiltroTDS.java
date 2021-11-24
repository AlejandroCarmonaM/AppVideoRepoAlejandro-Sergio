package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import dominio.Filtro;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorFiltroTDS implements IAdaptadorFiltroDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorFiltroTDS unicaInstancia = null;

	public static AdaptadorFiltroTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorFiltroTDS();
		} else
			return unicaInstancia;
	}

	private AdaptadorFiltroTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	/* cuando se registra un producto se le asigna un identificador unico */
	public void registrarFiltro(Filtro filtro) {
		Entidad eFiltro = null;
		try {
			eFiltro = servPersistencia.recuperarEntidad(filtro.getCodigo());
		} catch (NullPointerException e) {}
		if (eFiltro != null) return;
		
		// crear entidad producto
		eFiltro = new Entidad();
		eFiltro.setNombre("filtro");
		eFiltro.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("nombre", filtro.getNombre()))));
		
		// registrar entidad producto
		eFiltro = servPersistencia.registrarEntidad(eFiltro);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		filtro.setCodigo(eFiltro.getId());
	}

	public void borrarFiltro(Filtro filtro) {
		// No se comprueba integridad con lineas de venta
		Entidad eFiltro = servPersistencia.recuperarEntidad(filtro.getCodigo());
		servPersistencia.borrarEntidad(eFiltro);
	}

	public void modificarFiltro(Filtro filtro) {
		Entidad eFiltro = servPersistencia.recuperarEntidad(filtro.getCodigo());

		for (Propiedad prop : eFiltro.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(filtro.getCodigo()));
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(filtro.getNombre());
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public Filtro recuperarFiltro(int codigo) {
		Entidad eFiltro;
		String nombre;

		eFiltro = servPersistencia.recuperarEntidad(codigo);
		nombre = servPersistencia.recuperarPropiedadEntidad(eFiltro, "nombre");
		//filtro nulo->no se registra el filtro

		Filtro filtro;
		try {
			//System.out.println("holaa");
			filtro =(Filtro)Class.forName(nombre).getDeclaredConstructor().newInstance();
			filtro.setCodigo(codigo);
			return filtro;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null; //comprobar esto
	}

	public List<Filtro> recuperarTodosFiltros() {
		List<Filtro> filtros = new LinkedList<Filtro>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("filtro");

		for (Entidad eFiltro : entidades) {
			filtros.add(recuperarFiltro(eFiltro.getId()));
		}
		return filtros;
	}

}
