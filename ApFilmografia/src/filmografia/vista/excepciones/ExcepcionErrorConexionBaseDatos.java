package filmografia.vista.excepciones;

public class ExcepcionErrorConexionBaseDatos extends ExcepcionAp{

	public ExcepcionErrorConexionBaseDatos(String pMensaje) {
		super(pMensaje);
		
	}
public String getMensajeError(){
		
		return this.mensaje;
	}
}
