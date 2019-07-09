package app.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/deleteDesignDocument")
public class DeleteDesignDocumentServlet extends HttpServlet {
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

		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		int desId= Integer.parseInt(request.getParameter("desId").trim());
		int des_i= Integer.parseInt(request.getParameter("des_i").trim());
		
		DesignDocument designDocument = new DesignDocument();
		
		try{designDocument = designDocumentDAO.selectOneDesignDocument(desId);}
		catch(SQLException e){response.getWriter().println(e);}
		
		request.setAttribute("prodId", prodId);
		request.setAttribute("prod_i", prod_i);
		request.setAttribute("designDocument", designDocument);
		request.setAttribute("des_i", des_i);
		
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/deleteDesignDocumentView.jsp");
     dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		int desId= Integer.parseInt(request.getParameter("desId").trim());
		
		try{designDocumentDAO.deleteDesignDocument(desId);}
		catch(SQLException e){response.getWriter().println(e);}
		
		 response.sendRedirect(request.getContextPath() + "/designDocumentList?prodId="+prodId
				 +"&prod_i="+prod_i);
		
	}
}