package dominio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class RepositorioUsuarios {
	//Atributos
	private HashSet<Usuario> repositorio;
	
	private Usuario admin;
	private List<Usuario> usuarios;
	
	//Metodos
	public boolean registrarUsuario(String nombre, String fechaNacimiento, String nombreUsuario, String contrasena, String contrasenaRep) {
		
		boolean existeUsuario = false;
		boolean coincideContrasena = true;
		
		for (Usuario elemento : repositorio) {
			if (elemento.getNombre().equals(nombreUsuario))
				existeUsuario = true;
		}
		
		if (!contrasena.equals(contrasenaRep))
			coincideContrasena = false;
		
		if (!existeUsuario && coincideContrasena) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date fecha = formato.parse(fechaNacimiento);
				Usuario nuevoUsuario = new Usuario(nombre, fecha, nombreUsuario, contrasena);
				repositorio.add(nuevoUsuario);
			} catch (ParseException e) {
				return false;
			}
			return true;
		}
		return false;
	}
	
public boolean registrarUsuarioCompleto(String nombre, String apellidos, String fechaNacimiento, String email, String nombreUsuario, String contrasena, String contrasenaRep) {
		
		boolean existeUsuario = false;
		boolean coincideContrasena = true;
		
		for (Usuario elemento : repositorio) {
			if (elemento.getNombre().equals(nombreUsuario))
				existeUsuario = true;
		}
		
		if (!contrasena.equals(contrasenaRep))
			coincideContrasena = false;
		
		if (!existeUsuario && coincideContrasena) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date fecha = formato.parse(fechaNacimiento);
				Usuario nuevoUsuario = new Usuario(nombre, apellidos, fecha, email, nombreUsuario, contrasena);
				repositorio.add(nuevoUsuario);
			} catch (ParseException e) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	//Constructor
	public RepositorioUsuarios() {
		this.repositorio = new HashSet<Usuario>();
		
		admin = new Usuario("admin", "admin", new Date(10/10/2010), "admin", "admin", "admin");
		Usuario root = new Usuario("root", "root", new Date(10/10/2010), "root", "root", "root");
		usuarios = new LinkedList<Usuario>();
		usuarios.add(root);
		usuarios.add(admin);
	}
	
	private List<Usuario> getUsuarios() {
		return usuarios;
	}

	public boolean isLoginOK(String nombreUsuario, String contrasena)
	{
		if(nombreUsuario==null || contrasena ==null) return false;
		
		for(Usuario user: this.getUsuarios())
		{
			if (nombreUsuario.equals(user.getUsuario()) && contrasena.equals(user.getContrasena())) return true;
		}
		
		return false;
		
	}


	public Usuario getAdmin() {
		return admin;
	}
	
}
