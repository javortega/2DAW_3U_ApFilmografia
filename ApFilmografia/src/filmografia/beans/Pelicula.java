package filmografia.beans;

public class Pelicula {
	
	private java.util.Date fecha;
	
	private String titulo;
	
	private Director director;
	
	
	public Pelicula(Director director,String titulo,java.util.Date fecha){
		
		this.director=director;
		this.titulo=titulo;
		this.fecha=fecha;
	}
	
	
	public java.util.Date getFecha() {
		return fecha;
	}

	public String getTitulo() {
		return titulo;
	}

	public Director getDirector() {
		return director;
	}

	

}
