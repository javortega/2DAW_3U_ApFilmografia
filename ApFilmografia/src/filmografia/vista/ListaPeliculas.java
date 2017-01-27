package filmografia.vista;
import java.util.ArrayList;

import filmografia.beans.*;
public class ListaPeliculas {

	
	private ArrayList<Pelicula> listaPeliculas;
	
	public ListaPeliculas(){
		
		this.listaPeliculas = new ArrayList<Pelicula>();
	}
	
	
	public void addPeliculas(Pelicula pelicula){
		
		this.listaPeliculas.add(pelicula);
	}
	
	public String getTituloPeliculas(){
		
		String peliculas ="";
		int contador = 1;
		for(Pelicula pelicula:this.listaPeliculas){
		peliculas+=	contador+"."+pelicula.getTitulo()+" ";
		contador++;
		}
		
		return peliculas;
	}
	
	public Director getDirector(){
		Director director=null;
		for(Pelicula pelicula:this.listaPeliculas){
		director =	pelicula.getDirector();
		
		}
		return director;
	}
	public boolean isVacio(){
		
		return this.listaPeliculas.isEmpty();
	}
	
}
