package filmografia.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import filmografia.dao.BeanDao;
import filmografia.vista.ListaPeliculas;

public class ValidaDirector extends HttpServlet {
		
	private BeanDao beanDao;
	
	
	public void init(ServletConfig configuracion) throws ServletException{
		
		super.init(configuracion);
	
	try {
		InitialContext inicioContexto = new InitialContext();
		
		  ServletContext aplicacion = configuracion.getServletContext();
		  this.beanDao = new BeanDao((DataSource) inicioContexto.lookup(aplicacion.getInitParameter("datasource")));
		 
	} catch (NamingException e) {
		
		System.out.println("Error en el método init.");
	}
	
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String vista="";
		
		try {
			this.beanDao.getConnection();
			
			if(this.beanDao.existeDirector(request.getParameter("nombre"))){
				
				vista="WEB-INF/respuesta.jsp";
				ListaPeliculas lista=this.beanDao.getPeliculas(request.getParameter("nombre"));
				request.setAttribute("listaPeliculas", lista);
				
				this.beanDao.close();
				RequestDispatcher rd = request.getRequestDispatcher(vista);
				rd.forward(request, response);
				
			}else{
				vista="WEB-INF/error.jsp";
				request.setAttribute("error", this.beanDao.getError());
				this.beanDao.close();
				RequestDispatcher rd = request.getRequestDispatcher(vista);
				rd.forward(request, response);
			}
				
		} catch (SQLException e) {
			System.out.println("Error en la conexión a base de datos.");
		}
		
	}
}
