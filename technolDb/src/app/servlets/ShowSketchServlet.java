package app.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.SQLException;

import app.entities.*;

@WebServlet("/showSketch")
public class ShowSketchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private TechOperationDAO techOperationDAO;

	public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
            techOperationDAO = new TechOperationDAO(ds);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		String operId=(String) request.getParameter("operId");
		TechOperation techOperation=new TechOperation();
		try{
			techOperation=techOperationDAO.selectOneTechOperation(Integer.parseInt(operId));
		}
		catch(SQLException e){response.getWriter().println(e);}
      
		String sketchName = techOperation.getSketchName();   
        String contentType = this.getServletContext().getMimeType(sketchName);
        
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", String.valueOf(techOperation.getSketch().length));
        response.setHeader("Content-Disposition", "inline; filename=\"" + sketchName + "\"");
		
        response.getOutputStream().write(techOperation.getSketch());
        } 
		catch (Exception e) {  throw new ServletException(e); }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);		 
	}
}
