package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dominio.Etiqueta;
import dominio.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorVideoTDS implements IAdaptadorVideoDAO{
	private static ServicioPersistencia servPersistencia;

	private static AdaptadorVideoTDS unicaInstancia;

	public static AdaptadorVideoTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorVideoTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorVideoTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	/* cuando se registra una venta se le asigna un identificador unico */
	public void registrarVideo(Video video) {
		Entidad eVideo = null;
		try {
			eVideo = servPersistencia.recuperarEntidad(video.getCodigo());
		} catch (NullPointerException e) {}
		if (eVideo != null)	return;
		
		// registrar primero los atributos que son objetos
		AdaptadorEtiquetaTDS adaptadorEtiqueta = AdaptadorEtiquetaTDS.getUnicaInstancia();
		for (Etiqueta v : video.getEtiquetas())
			adaptadorEtiqueta.registrarEtiqueta(v);
		
		// registrar primero los atributos que son objetos
		// Crear entidad venta
		eVideo = new Entidad();

		eVideo.setNombre("video");
		eVideo.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("titulo", String.valueOf(video.getTitulo())),
						new Propiedad("numRepro", String.valueOf(video.getNumeroReproducciones())),
						new Propiedad("url", String.valueOf(video.getUrl())),
						new Propiedad("etiquetas", obtenerCodigosEtiquetas(video.getEtiquetas())))));
		// registrar entidad venta
		eVideo = servPersistencia.registrarEntidad(eVideo);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		video.setCodigo(eVideo.getId());
	}

	public void borrarVideo(Video video) {
		// No se comprueban restricciones de integridad con Cliente
		Entidad eVideo;
		AdaptadorEtiquetaTDS adaptadorE = AdaptadorEtiquetaTDS.getUnicaInstancia();

		for (Etiqueta etiqueta : video.getEtiquetas()) {
			adaptadorE.borrarEtiqueta(etiqueta);
		}
		eVideo = servPersistencia.recuperarEntidad(video.getCodigo());
		servPersistencia.borrarEntidad(eVideo);

	}

	public void modificarVideo(Video video) {
		Entidad eVideo = servPersistencia.recuperarEntidad(video.getCodigo());

		for (Propiedad prop : eVideo.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(video.getCodigo()));
			} else if (prop.getNombre().equals("titulo")) {
				prop.setValor(String.valueOf(video.getTitulo()));
			} else if (prop.getNombre().equals("url")) {
				prop.setValor(String.valueOf(video.getUrl()));
			} else if (prop.getNombre().equals("numRepro")) {
				prop.setValor(String.valueOf(video.getNumeroReproducciones()));
			} else if (prop.getNombre().equals("lineasventa")) {
				String lineas = obtenerCodigosEtiquetas(video.getEtiquetas());
				prop.setValor(lineas);
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public Video recuperarVideo(int codigo) {
		// si no, la recupera de la base de datos
		// recuperar entidad
		Entidad eVideo = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		
		String titulo = servPersistencia.recuperarPropiedadEntidad(eVideo, "titulo");
		String url = servPersistencia.recuperarPropiedadEntidad(eVideo, "url");
		int numRepro = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eVideo, "numRepro"));
		
		Video video = new Video(titulo, url);
		video.setNumeroReproducciones(numRepro);
		video.setCodigo(codigo);

		// recuperar propiedades que son objetos llamando a adaptadores
		// cliente
		/*AdaptadorEtiquetaTDS adaptadorEtiqueta = AdaptadorEtiquetaTDS.getUnicaInstancia();
		int codigoEtiqueta = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eVideo, "etiquetas"));
		
		Etiqueta etiqueta = adaptadorEtiqueta.recuperarEtiqueta(codigoEtiqueta);
		video.anadirEtiqueta(etiqueta);*/
		
		Set<Etiqueta> etiquetas = obtenerEtiquetasDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eVideo, "etiquetas"));

		for (Etiqueta e : etiquetas)
			video.anadirEtiqueta(e);

		// devolver el objeto venta
		return video;
	}

	public List<Video> recuperarTodosVideo() {
		List<Video> videos = new LinkedList<Video>();
		List<Entidad> eVideos = servPersistencia.recuperarEntidades("video");

		for (Entidad eVideo : eVideos) {
			videos.add(recuperarVideo(eVideo.getId()));
		}
		return videos;
	}

	// -------------------Funciones auxiliares-----------------------------
	private String obtenerCodigosEtiquetas(Set<Etiqueta> etiquetas) {
		String lineas = "";
		for (Etiqueta etiqueta : etiquetas) {
			lineas += etiqueta.getCodigo() + " ";
		}
		return lineas.trim();
	}

	private Set<Etiqueta> obtenerEtiquetasDesdeCodigos(String lineas) {
		Set<Etiqueta> etiquetas = new HashSet<Etiqueta>();
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		AdaptadorEtiquetaTDS adaptadorE = AdaptadorEtiquetaTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			etiquetas.add(adaptadorE.recuperarEtiqueta(Integer.valueOf((String) strTok.nextElement())));
		}
		return etiquetas;
	}
}
