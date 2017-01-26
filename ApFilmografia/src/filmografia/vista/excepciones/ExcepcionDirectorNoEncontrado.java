package filmografia.vista.excepciones;

public class ExcepcionDirectorNoEncontrado extends ExcepcionAp {
	
	public ExcepcionDirectorNoEncontrado(String mensaje){
		super(mensaje);
		
	}

	public String getMensajeError(){
		
		return this.mensaje;
	}
}
