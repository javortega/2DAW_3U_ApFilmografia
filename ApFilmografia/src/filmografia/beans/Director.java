package filmografia.beans;

public class Director {
	
	
	String nombre;
	
	public Director(String nombre){
		this.nombre = nombre;
	}

	public String getNombre(){
		
		return this.nombre;
	}
	
	
	public int hashCode(){
		
		return this.nombre.hashCode();
	}
	
	public boolean equals(Object obj){
		  if (obj instanceof Director) {
			    Director director = (Director)obj;
			    return this.nombre.equals(director.nombre);
			  } else {
			    return false;
			  }
		
		
	}
}
