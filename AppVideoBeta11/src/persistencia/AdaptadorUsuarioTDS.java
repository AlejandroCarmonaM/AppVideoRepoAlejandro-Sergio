package persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dominio.Filtro;
import dominio.ListaVideos;
import dominio.Usuario;
import dominio.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO{
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private SimpleDateFormat dateFormat;

	public static AdaptadorUsuarioTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	/* cuando se registra un cliente se le asigna un identificador ï¿½nico */
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {}
		if (eUsuario != null) return;

		// registrar primero los atributos que son objetos
		AdaptadorListaVideosTDS adaptadorLV = AdaptadorListaVideosTDS.getUnicaInstancia();
		for (ListaVideos lv : usuario.getListasVideos())
			adaptadorLV.registrarListaVideos(lv);
		
		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();
		for (Video v : usuario.getRecientes())
			adaptadorVideo.registrarVideo(v);
		
		AdaptadorFiltroTDS adaptadorFiltroTDS = AdaptadorFiltroTDS.getUnicaInstancia();
		adaptadorFiltroTDS.registrarFiltro(usuario.getFiltro());

		// crear entidad Cliente
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList( 
						new Propiedad("nombre", usuario.getNombre()),
						new Propiedad("apellidos", usuario.getApellidos()), 
						new Propiedad("fecha", dateFormat.format(usuario.getFecha())),
						new Propiedad("email", usuario.getEmail()),
						new Propiedad("usuario", usuario.getUsuario()),
						new Propiedad("contrasena", usuario.getContrasena()),
						new Propiedad("premium", String.valueOf(usuario.isPremium())),
						new Propiedad("filtro", String.valueOf(usuario.getFiltro().getCodigo())), //repasar en caso de fallo
						new Propiedad("listasVideos", obtenerCodigosListasVideos(usuario.getListasVideos())),
						new Propiedad("recientes", obtenerCodigosVideos(usuario.getRecientes())))));
						

		// registrar entidad cliente
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		usuario.setCodigo(eUsuario.getId());
	}

	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();

		for (Video video : usuario.getRecientes()) {
			adaptadorVideo.borrarVideo(video);
		}
		
		AdaptadorListaVideosTDS adaptadorLV = AdaptadorListaVideosTDS.getUnicaInstancia();

		for (ListaVideos listaVideo : usuario.getListasVideos()) {
			adaptadorLV.borrarListaVideos(listaVideo);
		}
		
		AdaptadorFiltroTDS adaptadorFiltroTDS = AdaptadorFiltroTDS.getUnicaInstancia();
		adaptadorFiltroTDS.borrarFiltro(usuario.getFiltro());
		
		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.borrarEntidad(eUsuario);

	}

	public void modificarUsuario(Usuario usuario) { //antes de modificar un objeto, si lo que hago es cambiar ese objeto
		//por otro, debería registrarlo, no?

		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(usuario.getCodigo()));
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals("apellidos")) {
				prop.setValor(usuario.getApellidos());
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(dateFormat.format(usuario.getFecha()));
			} else if (prop.getNombre().equals("email")) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals("usuario")) {
				prop.setValor(usuario.getUsuario());
			} else if (prop.getNombre().equals("contrasena")) {
				prop.setValor(usuario.getContrasena());
			} else if (prop.getNombre().equals("premium")) {
				prop.setValor(String.valueOf(usuario.isPremium()));
			} else if (prop.getNombre().equals("filtro")) {
				prop.setValor(String.valueOf(usuario.getFiltro().getCodigo())); 
			} else if (prop.getNombre().equals("listasVideos")) {
				String lineas = obtenerCodigosListasVideos(usuario.getListasVideos());
				prop.setValor(lineas);
			} else if (prop.getNombre().equals("recientes")) {
				String lineas = obtenerCodigosVideos(usuario.getRecientes());
				prop.setValor(lineas);
			}
			servPersistencia.modificarPropiedad(prop);
		}

	}

	public Usuario recuperarUsuario(int codigo) {
		Entidad eUsuario;
		List<ListaVideos> listasVideos = new LinkedList<ListaVideos>();
		List<Video> recientes = new LinkedList<Video>();
		String nombre;
		String apellidos;
		String email;
		String usuario;
		String contrasena;
		boolean premium;
		Filtro filtro;

		// recuperar entidad
		eUsuario = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		Date fecha = null;
		try {
			fecha = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		usuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "usuario");
		contrasena = servPersistencia.recuperarPropiedadEntidad(eUsuario, "contrasena");
		premium = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));

		Usuario usuarioFinal = new Usuario(nombre, apellidos, fecha, email, usuario, contrasena);
		usuarioFinal.setPremium(premium);
		usuarioFinal.setCodigo(codigo);

		// recuperar propiedades que son objetos llamando a adaptadores
		AdaptadorFiltroTDS adaptadorFiltroTDS = AdaptadorFiltroTDS.getUnicaInstancia();
		//prop.setValor(String.valueOf(usuario.getFiltro()));
		filtro = adaptadorFiltroTDS.recuperarFiltro
				(Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eUsuario, "filtro"))); //da fallo al cambiar el filtro
		usuarioFinal.setFiltro(filtro);
		listasVideos = obtenerListasVideosDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "listasVideos"));
		recientes = obtenerRecientesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "recientes"));

		for (ListaVideos lv : listasVideos)
			usuarioFinal.addListaVideos(lv);
		
		for (Video v : recientes)
			usuarioFinal.addVideoRecientes(v);

		return usuarioFinal;
	}

	public List<Usuario> recuperarTodosUsuarios() {

		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();

		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		return usuarios;
	}

	// -------------------Funciones auxiliares-----------------------------
	private String obtenerCodigosListasVideos(List<ListaVideos> listaListasVideos) {
		String aux = "";
		for (ListaVideos listaVideo : listaListasVideos) {
			aux += listaVideo.getCodigo() + " ";
		}
		return aux.trim();
	}
	
	private String obtenerCodigosVideos(List<Video> listaVideos) {
		String aux = "";
		for (Video video : listaVideos) {
			aux += video.getCodigo() + " ";
		}
		return aux.trim();
	}

	private List<ListaVideos> obtenerListasVideosDesdeCodigos(String lineas) {
		List<ListaVideos> listasVideos = new LinkedList<ListaVideos>();
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		AdaptadorListaVideosTDS adaptadorLV = AdaptadorListaVideosTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listasVideos.add(adaptadorLV.recuperarListaVideos(Integer.valueOf((String) strTok.nextElement())));
		}
		return listasVideos;
	}
	
	private List<Video> obtenerRecientesDesdeCodigos(String lineas) {
		List<Video> video = new LinkedList<Video>();
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			video.add(adaptadorVideo.recuperarVideo(Integer.valueOf((String) strTok.nextElement())));
		}
		return video;
	}
}
