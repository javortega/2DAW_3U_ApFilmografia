package filmografia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import filmografia.beans.Director;
import filmografia.beans.Pelicula;
import filmografia.vista.ListaPeliculas;
import filmografia.vista.excepciones.ExcepcionAp;
import filmografia.vista.excepciones.ExcepcionDirectorNoEncontrado;

public class BeanDao implements Dao{

	private DataSource ds;
	
	private Connection conexion;
	
	private ListaPeliculas listPeliculas;
	
	private ExcepcionAp error;
	

	
	public BeanDao(DataSource dataSource){
		
		this.ds = dataSource;
		
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		this.conexion = this.ds.getConnection();
		return this.conexion;
	}

	@Override
	public void close() throws SQLException {
		if(this.conexion!=null)
			this.conexion.close();
		
	}

	@Override
	public boolean existeDirector(String pNombre){
		
		boolean existe = false;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = this.conexion.createStatement();
			rs = st.executeQuery("select director,titulo,fecha from peliculas where director='"+pNombre+"'");
		if(rs.next()){
			existe=true;
		}else{
			this.error = new ExcepcionDirectorNoEncontrado("El director no se encuentra en la base de datos");
		}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return existe;
	}

	@Override
	public ListaPeliculas getPeliculas(String pNombre) {
		
		Statement st = null;
		ResultSet rs = null;
		
		try {
			this.listPeliculas=new ListaPeliculas();
			st = this.conexion.createStatement();
			rs = st.executeQuery("select director,titulo,fecha from peliculas where director='"+pNombre+"'");
		while(rs.next()){
			this.listPeliculas.addPeliculas(new Pelicula(new Director(rs.getString(1)),rs.getString(2),new java.util.Date(rs.getTimestamp(3).getTime())));
		}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return this.listPeliculas;
	}
     public ExcepcionAp getError(){
    	 
    	 return this.error;
     }
}
