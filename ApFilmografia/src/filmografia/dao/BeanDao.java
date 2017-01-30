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
	

	
	public BeanDao(){
		
		
		
	}
	
	@Override
	public Connection getConnection(DataSource ds) throws SQLException {
		this.conexion = ds.getConnection();
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
		
		boolean existe = false;
		Statement st = null;
		ResultSet rs = null;
		
		if(this.conexion!=null){
			
		try {
			
			st = this.conexion.createStatement();
			rs = st.executeQuery("select director,titulo,fecha from peliculas where director='"+pNombre+"'");
		if(rs.next()){
			existe=true;
		}else{
			this.error = new ExcepcionDirectorNoEncontrado("El director no se encuentra en la base de datos");
		}
		
		
			} catch (SQLException e) {
			System.out.println("Error en la conexión a la base de datos");
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
		
		}else{
			this.error= new ExcepcionErrorConexionBaseDatos("Se ha perdido la conexión a la base de datos.");
		}
		
		
		
		
		return existe;
	}

	@Override
	public ListaPeliculas getPeliculas(String pNombre) {
		
		Statement st = null;
		ResultSet rs = null;
		
		//Debe de estar fuera del try sino devolveriamos un objeto nulo saltando un NullPointer en respuesta.jsp
		this.listPeliculas=new ListaPeliculas();
		
		if(this.conexion!=null){
		
		try {
				
			st = this.conexion.createStatement();
			rs = st.executeQuery("select director,titulo,fecha from peliculas where director='"+pNombre+"'");
			while(rs.next()){
				this.listPeliculas.addPeliculas(new Pelicula(new Director(rs.getString(1)),rs.getString(2),new java.util.Date(rs.getTimestamp(3).getTime())));
							}
		
		
			} catch (SQLException e) {
			System.out.println("Error en la conexión a la base de datos");
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
			
		
		}else{
			this.error = 
			new ExcepcionErrorConexionBaseDatos("Se ha perdido la conexión a la base de datos.");
			}
		

		
		return this.listPeliculas;
	}
     public ExcepcionAp getError(){
    	 
    	 return this.error;
     }
     
     public ListaDirectores getDirectores(){
    	 
    	 Statement st = null;
    	 ResultSet rs =null;
    	 
 		
 		//Debe de estar fuera del try sino devolveriamos un objeto nulo saltando un NullPointer en respuesta.jsp
 		this.listDirectores=new ListaDirectores();
 		
 		if(this.conexion!=null){
 		
 		try {
 				
 			st = this.conexion.createStatement();
 			rs = st.executeQuery("select DISTINCT director from peliculas;");
 			while(rs.next()){
 			this.listDirectores.addDirector(new Director(rs.getString(1)));
 							}
 		
 		
 			} catch (SQLException e) {
 			System.out.println("Error en la conexión a la base de datos");
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
 			
 		
 		}else{
 			this.error = 
 			new ExcepcionErrorConexionBaseDatos("Se ha perdido la conexión a la base de datos.");
 			}
 		

 		
 		return this.listDirectores;
 	}
    	 
    	 
     }

