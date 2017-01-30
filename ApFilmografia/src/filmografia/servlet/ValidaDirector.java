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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import filmografia.dao.BeanDao;
import filmografia.vista.ListaDirectores;
import filmografia.vista.ListaPeliculas;

public class ValidaDirector extends HttpServlet {
		
	private DataSource ds;
	
	private HttpSession session;
	
	public void init(ServletConfig configuracion) throws ServletException{
		
		super.init(configuracion);
		ServletContext aplicacion=null;
	try {
		InitialContext inicioContexto = new InitialContext();
		
		  aplicacion = configuracion.getServletContext();
		  this.ds = (DataSource) inicioContexto.lookup(aplicacion.getInitParameter("datasource"));
		 
	} catch (NamingException e) {
		
		System.out.println("Error en el método init.");
		aplicacion.setInitParameter("apFuncionando", "false");
		
	}
	
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String vista="";
		String vistaError="";
		ListaDirectores listDirectores;
		this.session = request.getSession();
		BeanDao beanDao = new BeanDao();
		ServletContext aplicacion=request.getServletContext();
		
		try {
			//Le paso al index.jsp todos los directores disponibles en la base de datos
			//Pregunto si es null para saber que el usuario acaba de iniciar la aplicación
			//ya que si quiere volver a consultar las películas de un director no se 
			//vuelve a acceder a la bd sino que trabajamos con una variable de session.
			
			if(this.session.getAttribute("listaCompletaDirectores")==null){
				vista="index.jsp";
				beanDao.getConnection(this.ds);
				listDirectores=beanDao.getDirectores();
				request.setAttribute("listaCompletaDirectores",listDirectores);
				RequestDispatcher rd = request.getRequestDispatcher(vista);
				beanDao.close();
				rd.forward(request, response);
			}
				
				
			
				
				
			//Si salta una excepción,buscando el ds ,la controlo aqui y mando al usr a 
			//aplicacionmantenimiento.jsp sin posibilidad de seguir con la aplicación
		if(aplicacion.getInitParameter("apFuncionando")!=null){
			
				if(aplicacion.getInitParameter("apFuncionando").equals("false")){
				vista="WEB-INF/aplicacionenmantenimiento.jsp";
				request.setAttribute("error","Imposible utilizar la aplicación,inténtelo más tarde.");
				RequestDispatcher rd = request.getRequestDispatcher(vista);
				rd.forward(request, response);
			
				}
		}else{
				
				
				if(request.getParameter("accion")!=null){
				vista="WEB-INF/"+request.getParameter("accion")+".jsp";
				RequestDispatcher rd = request.getRequestDispatcher(vista);
				rd.forward(request, response);
				}
				beanDao.getConnection(this.ds);
				//Consulta innecesaria
				if(beanDao.existeDirector(request.getParameter("directores"))){
				
				vista="WEB-INF/respuesta.jsp";
				ListaPeliculas lista=beanDao.getPeliculas(request.getParameter("directores"));
				request.setAttribute("listaPeliculas", lista);
				
				beanDao.close();
				RequestDispatcher rd = request.getRequestDispatcher(vista);
				rd.forward(request, response);
				
				}else{
				vistaError="WEB-INF/error.jsp";
				request.setAttribute("error", beanDao.getError());
				beanDao.close();
				RequestDispatcher rd = request.getRequestDispatcher(vistaError);
				rd.forward(request, response);
					}
			}	
				} catch (SQLException e) {
			System.out.println("Error en la conexión a base de datos.");
						}
		
		
	}
}
