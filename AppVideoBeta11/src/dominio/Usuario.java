package dominio;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	//Atributos
	private int codigo = 0;
	private String nombre;
	private String apellidos;
	private Date fecha;
	private String email;
	private String usuario;
	private String contrasena;
	private boolean premium;
	private List<ListaVideos> listasVideos;
	private List<Video> recientes;
	private Filtro filtro;
	//Lo que si tenemos que anadir es un atributo filtro
	
	//constructor
	public Usuario(String nombre, String apellidos, Date fecha, String email, String usuario, String contrasena) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha = fecha;
		this.email = email;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.listasVideos = new LinkedList<ListaVideos>();
		this.recientes = new ArrayList<Video>();
		this.premium = false;
		this.filtro = new NoFiltro();
	}
	
	public Usuario(String nombre, String apellidos, Date fecha, String email, String usuario, String contrasena,
			boolean premium, Filtro filtro) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha = fecha;
		this.email = email;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.listasVideos = new LinkedList<ListaVideos>();
		this.recientes = new ArrayList<Video>();
		this.premium = premium;
		this.filtro = filtro;
	}
	

	public Usuario(String nombre, Date fechaNacimiento, String nombreUsuario, String contrasena) {
		this.nombre = nombre;
		this.fecha = fechaNacimiento;
		this.usuario = nombreUsuario;
		this.contrasena = contrasena;
		this.listasVideos = new LinkedList<ListaVideos>();
		this.recientes = new ArrayList<Video>();
		this.premium = false;
		this.filtro = new NoFiltro();
	}

	//metodos get y set
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public Filtro getFiltro() {
		return filtro;
	}
	
	public void setFiltro(Filtro filtro) {
		this.filtro=filtro;
	}

	public List<ListaVideos> getListasVideos() {
		LinkedList<ListaVideos> copia = new LinkedList<ListaVideos>(listasVideos);
		return copia;
	}
	
	public void addListaVideos(ListaVideos lv) {
		this.listasVideos.add(lv);
	}
	
	public void borraLista(ListaVideos lv)
	{
		this.listasVideos.remove(lv);
	}
	
	public void addVideoRecientes(Video v) {
		recientes.add(0, v);
		if (recientes.size() > 10)
			recientes.remove(10);
	}

	public List<Video> getRecientes() {
		LinkedList<Video> copia = new LinkedList<Video>(recientes);
		return copia;
	}
	
	public boolean isVideoListas(Video v)
	{
		return listasVideos.stream()
		.flatMap(l->l.getListaVideos().stream())
		.anyMatch(video->video.equals(v));
	}
	
	public List<String> getNombreMisListas() {
		LinkedList<String> listaNombres = new LinkedList<String>();
		for(ListaVideos elemento : listasVideos) {
			listaNombres.add(elemento.getNombre());
		}
		return listaNombres;
	}
	
	public ListaVideos getListaVideosPorNombre(String nombreLista) {
		for(ListaVideos elemento : listasVideos) {
			if (elemento.getNombre().equals(nombreLista))
				return elemento;
		}
		return null;
	}
	
	public boolean filtraVideo(Video v)
	{
		return this.filtro.esVideoOK(v, this);
	}
	
	public String getInfoListas()
	{
		String infoListas="Listas de videos de "+this.getUsuario()+"\n\n";
		for(ListaVideos lv: listasVideos)
		{
			infoListas+=lv.getInfoLista();
		}
		return infoListas;
		
	}

}
