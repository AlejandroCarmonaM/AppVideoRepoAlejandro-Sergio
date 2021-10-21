package dominio;

import java.time.LocalDate;
import java.util.Date;

public class Usuario {
	
	//Atributos
	private String nombre;
	private String apellidos;
	private Date fecha;
	private String email;
	private String usuario;
	private String contrasena;
	private boolean premium;
	
	//constructor
	public Usuario(String nombre, String apellidos, Date fecha, String email, String usuario, String contrasena) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha = fecha;
		this.email = email;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	public Usuario(String nombre, Date fechaNacimiento, String nombreUsuario, String contrasena) {
		this.nombre = nombre;
		this.fecha = fechaNacimiento;
		this.nombre = nombreUsuario;
		this.contrasena = contrasena;
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

	public Date getFecha() {
		return fecha;
	}
	
	
	
	
	
	
	

}
