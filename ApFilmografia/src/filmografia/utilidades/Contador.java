package filmografia.utilidades;

public class Contador {
	
private int numAccesos=0;

	
	public Contador(){
		
	}
	
	public int getNumAccesos(){
		
		return this.numAccesos;
	}
	
	
	
	public void setNumAccesos(int pNumero){
		
		this.numAccesos+=pNumero;
	}


}
