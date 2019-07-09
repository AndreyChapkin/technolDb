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

@WebServlet("/showDocument")
public class ShowDocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DesignDocumentDAO designDocumentDAO;

	public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
            designDocumentDAO = new DesignDocumentDAO(ds);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		String desId=(String) request.getParameter("desId");
		DesignDocument designDocument=new DesignDocument();
		try{
			designDocument=designDocumentDAO.selectOneDesignDocument(Integer.parseInt(desId));
		}
		catch(SQLException e){response.getWriter().println(e);}
      
		String docFileName = designDocument.getDocFileName();   
        String contentType = this.getServletContext().getMimeType(docFileName);
        
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", String.valueOf(designDocument.getDocument().length));
        response.setHeader("Content-Disposition", "inline; filename=\"" + docFileName + "\"");
		
        response.getOutputStream().write(designDocument.getDocument());
        } 
		catch (Exception e) {  throw new ServletException(e); }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);		 
	}
}