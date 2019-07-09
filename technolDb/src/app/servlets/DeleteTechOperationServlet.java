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

@WebServlet("/deleteTechOperation")
public class DeleteTechOperationServlet extends HttpServlet {
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
		
		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		int tprId= Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i= Integer.parseInt(request.getParameter("tpr_i").trim());
		int operId= Integer.parseInt(request.getParameter("operId").trim());
		int oper_i= Integer.parseInt(request.getParameter("oper_i").trim());
		
		TechOperation techOperation = new TechOperation();
		
		try{techOperation = techOperationDAO.selectOneTechOperation(operId);}
		catch(SQLException e){response.getWriter().println(e);}
		
		request.setAttribute("prodId", prodId);
		request.setAttribute("prod_i", prod_i);
		request.setAttribute("tprId", tprId);
		request.setAttribute("tpr_i", tpr_i);
		request.setAttribute("techOperation", techOperation);
		request.setAttribute("oper_i", oper_i);
		
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/deleteTechOperationView.jsp");
     dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		int tprId= Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i= Integer.parseInt(request.getParameter("tpr_i").trim());
		int operId= Integer.parseInt(request.getParameter("operId").trim());
		try{techOperationDAO.deleteTechOperation(operId);}
		catch(SQLException e){response.getWriter().println(e);}
		 response.sendRedirect(request.getContextPath() + "/techOperationList?prodId="+prodId
				 +"&prod_i="+prod_i+"&tprId="+tprId+"&tpr_i="+tpr_i);
		
	}
}