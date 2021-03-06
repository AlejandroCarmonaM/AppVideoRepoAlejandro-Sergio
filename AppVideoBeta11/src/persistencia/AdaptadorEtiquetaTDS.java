package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import dominio.Etiqueta;
import dominio.PoolEtiquetas;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorEtiquetaTDS implements IAdaptadorEtiquetaDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorEtiquetaTDS unicaInstancia = null;

	public static AdaptadorEtiquetaTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorEtiquetaTDS();
		} else
			return unicaInstancia;
	}

	private AdaptadorEtiquetaTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public void registrarEtiqueta(Etiqueta etiqueta) {
		Entidad eEtiqueta = null;
		try {
			eEtiqueta = servPersistencia.recuperarEntidad(etiqueta.getCodigo());
		} catch (NullPointerException e) {}
		if (eEtiqueta != null) return;
		
		eEtiqueta = new Entidad();
		eEtiqueta.setNombre("etiqueta");
		eEtiqueta.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("nombre", etiqueta.getNombre()))));
		
		eEtiqueta = servPersistencia.registrarEntidad(eEtiqueta);
		etiqueta.setCodigo(eEtiqueta.getId());  
	}

	public void borrarEtiqueta(Etiqueta etiqueta) {
		Entidad eEtiqueta = servPersistencia.recuperarEntidad(etiqueta.getCodigo());
		servPersistencia.borrarEntidad(eEtiqueta);
	}

	public void modificarEtiqueta(Etiqueta etiqueta) {
		Entidad eEtiqueta = servPersistencia.recuperarEntidad(etiqueta.getCodigo());

		for (Propiedad prop : eEtiqueta.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(etiqueta.getCodigo()));
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(etiqueta.getNombre());
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public Etiqueta recuperarEtiqueta(int codigo) {
		Entidad eEtiqueta;
		String nombre;

		eEtiqueta = servPersistencia.recuperarEntidad(codigo);
		nombre = servPersistencia.recuperarPropiedadEntidad(eEtiqueta, "nombre");

		Etiqueta etiqueta = PoolEtiquetas.INSTANCE.get(nombre);
		etiqueta.setCodigo(codigo);
		return etiqueta;
	}

	public List<Etiqueta> recuperarTodasEtiquetas() {
		List<Etiqueta> etiquetas = new LinkedList<Etiqueta>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("etiqueta");

		for (Entidad eEtiquetas : entidades) {
			etiquetas.add(recuperarEtiqueta(eEtiquetas.getId()));
		}
		return etiquetas;
	}
}
