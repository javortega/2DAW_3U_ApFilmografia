package filmografia.vista.excepciones;

public abstract class ExcepcionAp extends Exception {
	protected String mensaje;
	
	
	public ExcepcionAp(String pMensaje){
		
		this.mensaje=pMensaje;
		
		
	}
	
	public String getMensajeError(){
		
		return this.mensaje;
	}

}
