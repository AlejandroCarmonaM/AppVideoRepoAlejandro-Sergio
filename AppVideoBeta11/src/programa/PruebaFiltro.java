package programa;

import dominio.Filtro;
import dominio.NoFiltro;

public class PruebaFiltro {
	public static void main(String[] args) {
		
		Filtro filtro = new NoFiltro();
		String nombre = filtro.getClass().getName();
		try {
			Filtro filtro2 =(Filtro)Class.forName(nombre).getDeclaredConstructor().newInstance();
			System.out.println(filtro2.getClass().getName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
