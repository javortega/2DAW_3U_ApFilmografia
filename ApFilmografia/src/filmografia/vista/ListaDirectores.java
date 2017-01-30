package filmografia.vista;

import java.util.HashSet;
import java.util.Iterator;

import filmografia.beans.Director;

public class ListaDirectores {

	
	private HashSet<Director>listaDirectores;
	
	
	
	
	public ListaDirectores(){
		
		this.listaDirectores = new HashSet<Director>();
	}
	
	
	
	public void addDirector(Director director){
		
		this.listaDirectores.add(director);
	}
	
	public Iterator<Director> getListaDirectores(){
		
		return this.listaDirectores.iterator();
	}
	

}
