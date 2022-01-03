package dominio;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {
	private Map<String,Usuario> usuarios; 
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	
	private CatalogoUsuarios() {
		try {
  			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
  			adaptadorUsuario = dao.getUsuaioDAO();
  			usuarios = new HashMap<String,Usuario>();
  			this.cargarCatalogo();
  		} catch (DAOException eDAO) {
  			eDAO.printStackTrace();
  		}
	}
	
	public static CatalogoUsuarios getUnicaInstancia(){
		return unicaInstancia;
	}
	
	/*devuelve todos los clientes*/
	public List<Usuario> getUsuarios(){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario c:usuarios.values()) 
			lista.add(c);
		return lista;
	}
	
	public Usuario getUsuario(int codigo) {
		for (Usuario u:usuarios.values()) {
			if (u.getCodigo()==codigo) return u;
		}
		return null;
	}
	
	public Usuario getUsuario(String nombreUsuario) {
		return usuarios.get(nombreUsuario); 
	}
	
	public void addUsuario(Usuario u) {
		usuarios.put(u.getUsuario(),u);
	}
	
	public void removeUsuario (Usuario u) {
		usuarios.remove(u.getUsuario());
	}

	
	public void registrarUsuario(Usuario usuario) {
		this.addUsuario(usuario);
	}
	
	public boolean existeUsuario(String nombreUsuario)
	{
		for (Usuario elemento : this.getUsuarios()) {
			if (elemento.getNombre().equals(nombreUsuario))
				return true;
		}
		return false;
	}

	public void modificarUsuario(Usuario usuario)
	{
		adaptadorUsuario.modificarUsuario(usuario);
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
	
	/*Recupera todos los clientes para trabajar con ellos en memoria*/
	private void cargarCatalogo() throws DAOException {
		 List<Usuario> usuariosBD = adaptadorUsuario.recuperarTodosUsuarios();
		 for (Usuario u: usuariosBD) 
			     usuarios.put(u.getUsuario(),u);
	}
	
}
