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

@WebServlet("/deleteTechProcess")
public class DeleteTechProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private TechProcessDAO techProcessDAO;

	public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
            techProcessDAO = new TechProcessDAO(ds);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int tprId= Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i= Integer.parseInt(request.getParameter("tpr_i").trim());
		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		
		TechProcess techProcess = new TechProcess();
		
		try{techProcess = techProcessDAO.selectOneTechProcess(tprId);}
		catch(SQLException e){response.getWriter().println(e);}
		
		request.setAttribute("prodId", prodId);
		request.setAttribute("prod_i", prod_i);
		request.setAttribute("techProcess", techProcess);
		request.setAttribute("tpr_i", tpr_i);
		
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/deleteTechProcessView.jsp");
     dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int tprId= Integer.parseInt(request.getParameter("tprId").trim());
		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		
		try{techProcessDAO.deleteTechProcess(tprId);}
		catch(SQLException e){response.getWriter().println(e);}
		 response.sendRedirect(request.getContextPath() + "/techProcessList?prodId="+prodId
				 +"&prod_i="+prod_i);
		
	}

}