package filmografia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import filmografia.beans.Director;
import filmografia.beans.Pelicula;
import filmografia.vista.ListaDirectores;
import filmografia.vista.ListaPeliculas;
import filmografia.vista.excepciones.ExcepcionAp;
import filmografia.vista.excepciones.ExcepcionDirectorNoEncontrado;
import filmografia.vista.excepciones.ExcepcionErrorConexionBaseDatos;

public class BeanDao implements Dao{

	
	
	private Connection conexion;
	
	private ListaPeliculas listPeliculas;
	
	private ListaDirectores listDirectores;
	
	private ExcepcionAp error;
	
	private DataSource ds;
	
	public BeanDao(DataSource ds){
		this.ds=ds;
		
		
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
		else
			this.conexion=null;
	}

	@Override
	public boolean existeDirector(String pNombre){
		boolean conexionOk=false;
		boolean existe = false;
		Statement st = null;
		ResultSet rs = null;
		
			
		try {
			if(this.conexion==null){
				this.getConnection();
				conexionOk=true;
			}	
			st = this.conexion.createStatement();
			rs = st.executeQuery("select director,titulo,fecha from peliculas where director='"+pNombre+"'");
		if(rs.next()){
			existe=true;
		}else{
			this.error = new ExcepcionDirectorNoEncontrado("El director no se encuentra en la base de datos");
		}
		
		if(conexionOk)
			this.close();
			} catch (SQLException e) {
			System.out.println("Error en la conexión a la base de datos");
			this.error= new ExcepcionErrorConexionBaseDatos("Se ha perdido la conexión a la base de datos.");
			}finally{
			
				try {
					if(st!=null)
					st.close();
					
					if(rs!=null)
						rs.close();
				
				} catch (SQLException e) {
					
					System.out.println("Error al cerrar la conexión");
				}
		}
		
		return existe;
	}

	@Override
	public ListaPeliculas getPeliculas(String pNombre) {
		boolean conexionOk=false;
		Statement st = null;
		ResultSet rs = null;
		
		//Debe de estar fuera del try sino devolveríamos un objeto nulo saltando un NullPointer en respuesta.jsp
		this.listPeliculas=new ListaPeliculas();
		try {
		if(this.conexion==null){
			this.getConnection();
			conexionOk=true;
		}
			st = this.conexion.createStatement();
			rs = st.executeQuery("select director,titulo,fecha from peliculas where director='"+pNombre+"'");
			while(rs.next()){
				this.listPeliculas.addPeliculas(new Pelicula(new Director(rs.getString(1)),rs.getString(2),new java.util.Date(rs.getTimestamp(3).getTime())));
							}
		if(conexionOk){
			this.close();
		}
		
			} catch (SQLException e) {
			System.out.println("Error en la conexión a la base de datos");
			this.error = 
					new ExcepcionErrorConexionBaseDatos("Se ha perdido la conexión a la base de datos.");						
			
			}
		
	finally{
			try{
				if(st!=null)
					st.close();
					
					if(rs!=null)
						rs.close();
				
				}catch(SQLException e){
				System.out.println("Error al cerrar la conexión");
									}
			}
			
		return this.listPeliculas;
	}
     
	public ExcepcionAp getError(){
    	 
    	 return this.error;
     }
     
     public ListaDirectores getDirectores(){
    	 boolean conexionOk=false;
    	 Statement st = null;
    	 ResultSet rs =null;
    	 
 		
 		//Debe de estar fuera del try sino devolveriamos un objeto nulo saltando un NullPointer en respuesta.jsp
 		this.listDirectores=new ListaDirectores();
 		try {
 		if(this.conexion==null){
 			this.getConnection();
 			conexionOk=true;
 		}
 			st = this.conexion.createStatement();
 			rs = st.executeQuery("select DISTINCT director from peliculas;");
 			while(rs.next()){
 			this.listDirectores.addDirector(new Director(rs.getString(1)));
 							}
 		
 		if(conexionOk)
 			this.close();
 			
 			} catch (SQLException e) {
 			System.out.println("Error en la conexión a la base de datos");
 			this.error = 
 		 			new ExcepcionErrorConexionBaseDatos("Se ha perdido la conexión a la base de datos.");						
 				}
 		
 	finally{
 			try{
 				if(st!=null)
 					st.close();
 					
 					if(rs!=null)
 						rs.close();
 				
 				}catch(SQLException e){
 				System.out.println("Error al cerrar la conexión");
 									}
 			}
 			
 		
 		return this.listDirectores;
 	}
    	 
    	 
     }

