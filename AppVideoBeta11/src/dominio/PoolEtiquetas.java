package dominio;

import java.util.Collection;
import java.util.HashMap;

public enum PoolEtiquetas {
	INSTANCE;
	private HashMap<String, Etiqueta> tablaEtiquetas;
	
	private PoolEtiquetas()
	{
		tablaEtiquetas = new HashMap<String, Etiqueta>();
	}
	
	public Etiqueta get(String etiqueta)
	{
		if(tablaEtiquetas.get(etiqueta)!=null) {
			return tablaEtiquetas.get(etiqueta);
		}
		Etiqueta e = new Etiqueta(etiqueta); 
		//adaptadorEtiqueta.registrarEtiqueta(e);
		tablaEtiquetas.put(etiqueta, e);
		return e;
	}
	
	public int getNumEtiquetas()
	{
		return tablaEtiquetas.size();
	}
	
	public Collection<Etiqueta> getAll()
	{
		return tablaEtiquetas.values();
	}
	
}
