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
	
	
	
	public void init(ServletConfig configuracion) throws ServletException{
		
		super.init(configuracion);
		ServletContext aplicacion=null;
	try {
		InitialContext inicioContexto = new InitialContext();
			
		  aplicacion = configuracion.getServletContext();
		  //setAttribute--> establece un parámetro en el contexto de aplicación.
		  //para modificarlo debemos recurrir de nuevo a setAttribute porque este 
		  //método si encuentra un parámetro con el mismo nombre cambiará su valor original
		  //por el que le hayamos introducido.
		  aplicacion.setAttribute("apFuncionando", true);
		  this.ds = (DataSource) inicioContexto.lookup(aplicacion.getInitParameter("datasource"));
		  
		 
	} catch (NamingException e) {
		
		System.out.println("Error en el método init.");
		
		aplicacion.setAttribute("apFuncionando", false);
	}
	
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String vista="";
		String vistaError="";
		ListaDirectores listDirectores;
		HttpSession session = request.getSession();
		BeanDao beanDao = new BeanDao(this.ds);
		ServletContext aplicacion=request.getServletContext();
		
		try {
			
			//Si salta una excepción,buscando el ds ,la controlo aqui y mando al usr a 
			//aplicacionmantenimiento.jsp sin posibilidad de seguir con la aplicación
		if((boolean)aplicacion.getAttribute("apFuncionando")!=true){
			
				
				vista="WEB-INF/aplicacionenmantenimiento.jsp";
				request.setAttribute("error","Imposible utilizar la aplicación,inténtelo más tarde.");
				RequestDispatcher rd = request.getRequestDispatcher(vista);
				rd.forward(request, response);
			
				
		}
			
			
			
			
			//Le paso al index.jsp todos los directores disponibles en la base de datos
			//Pregunto si es null para saber que el usuario acaba de iniciar la aplicación
			//ya que si quiere volver a consultar las películas de un director no se 
			//vuelve a acceder a la bd sino que trabajamos con una variable de session.
			
			if(session.getAttribute("listaCompletaDirectores")==null){
				vista="WEB-INF/index.jsp";
				beanDao.getConnection();
				listDirectores=beanDao.getDirectores();
				request.setAttribute("listaCompletaDirectores",listDirectores);
				RequestDispatcher rd = request.getRequestDispatcher(vista);
				beanDao.close();
				rd.forward(request, response);
			}
				
				
			if(request.getParameter("accion")!=null){
				vista="WEB-INF/"+request.getParameter("accion")+".jsp";
				RequestDispatcher rd = request.getRequestDispatcher(vista);
				rd.forward(request, response);
				}
				beanDao.getConnection();
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
				
				} catch (SQLException e) {
			System.out.println("Error en la conexión a base de datos.");
						}
		
		
	}
}
