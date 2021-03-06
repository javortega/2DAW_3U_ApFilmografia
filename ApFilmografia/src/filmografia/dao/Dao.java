package filmografia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import filmografia.vista.ListaDirectores;
import filmografia.vista.ListaPeliculas;

public interface Dao {
	
	
	public Connection getConnection()throws SQLException;
	
	public void close()throws SQLException;
	
	
	public boolean existeDirector(String pNombre);
	
	
	public ListaPeliculas getPeliculas(String pNombre);
	
	public ListaDirectores getDirectores();
	
}
