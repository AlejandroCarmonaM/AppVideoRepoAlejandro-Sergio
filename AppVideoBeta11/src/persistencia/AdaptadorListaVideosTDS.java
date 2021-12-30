package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dominio.ListaVideos;
import dominio.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorListaVideosTDS implements IAdaptadorListaVideosDAO{
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaVideosTDS unicaInstancia;

	public static AdaptadorListaVideosTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorListaVideosTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorListaVideosTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public void registrarListaVideos(ListaVideos lv) {
		Entidad eLv = null;
		try {
			eLv = servPersistencia.recuperarEntidad(lv.getCodigo());
		} catch (NullPointerException e) {}
		if (eLv != null)	return;

		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();
		for (Video video : lv.getListaVideos())
			adaptadorVideo.registrarVideo(video);
		
		eLv = new Entidad();

		eLv.setNombre("listaVideos");
		eLv.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", String.valueOf(lv.getNombre())),
						new Propiedad("listaVideos", obtenerCodigosListaVideo(lv.getListaVideos())))));
		eLv = servPersistencia.registrarEntidad(eLv);
		lv.setCodigo(eLv.getId());
	}

	public void borrarListaVideos(ListaVideos lv) {
		Entidad eLv;
		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();

		for (Video video : lv.getListaVideos()) {
			adaptadorVideo.borrarVideo(video);
		}
		eLv = servPersistencia.recuperarEntidad(lv.getCodigo());
		servPersistencia.borrarEntidad(eLv);

	}

	public void modificarListaVideos(ListaVideos lv) {
		Entidad eLv = servPersistencia.recuperarEntidad(lv.getCodigo());

		for (Propiedad prop : eLv.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(lv.getCodigo()));
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(String.valueOf(lv.getNombre()));
			} else if (prop.getNombre().equals("listaVideos")) {
				String lineas = obtenerCodigosListaVideo(lv.getListaVideos());
				prop.setValor(lineas);
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public ListaVideos recuperarListaVideos(int codigo) {
		Entidad eLv = servPersistencia.recuperarEntidad(codigo);

		String nombre = String.valueOf(servPersistencia.recuperarPropiedadEntidad(eLv, "nombre"));
		
		ListaVideos nuevaListaVideos = new ListaVideos(nombre);
		nuevaListaVideos.setCodigo(codigo);
		List<Video> videos = obtenerListaVideosDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eLv, "listaVideos"));

		for (Video video : videos)
			nuevaListaVideos.addVideo(video);

		return nuevaListaVideos;
	}

	public List<ListaVideos> recuperarTodasListasVideos() {
		List<ListaVideos> listasVideos = new LinkedList<ListaVideos>();
		List<Entidad> eListasVideos = servPersistencia.recuperarEntidades("listaVideos");

		for (Entidad eListaVideo : eListasVideos) {
			listasVideos.add(recuperarListaVideos(eListaVideo.getId()));
		}
		return listasVideos;
	}

	private String obtenerCodigosListaVideo(List<Video> listaVideos) {
		String lineas = "";
		for (Video video : listaVideos) {
			lineas += video.getCodigo() + " ";
		}
		return lineas.trim();
	}

	private List<Video> obtenerListaVideosDesdeCodigos(String lineas) {
		List<Video> video = new LinkedList<Video>();
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			video.add(adaptadorVideo.recuperarVideo(Integer.valueOf((String) strTok.nextElement())));
		}
		return video;
	}
}
