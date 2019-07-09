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

@WebServlet("/deleteTechTransit")
public class DeleteTechTransitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private TechTransitDAO techTransitDAO;

	public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
            techTransitDAO = new TechTransitDAO(ds);
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
		int tranId= Integer.parseInt(request.getParameter("tranId").trim());
		int tran_i= Integer.parseInt(request.getParameter("tran_i").trim());
		
		TechTransit techTransit = new TechTransit();
		
		try{techTransit = techTransitDAO.selectOneTechTransit(tranId);}
		catch(SQLException e){response.getWriter().println(e);}
		
		request.setAttribute("prodId", prodId);
		request.setAttribute("prod_i", prod_i);
		request.setAttribute("tprId", tprId);
		request.setAttribute("tpr_i", tpr_i);
		request.setAttribute("operId", operId);
		request.setAttribute("oper_i", oper_i);
		request.setAttribute("techTransit", techTransit);
		request.setAttribute("tran_i", tran_i);
		
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/deleteTechTransitView.jsp");
     dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		int tprId= Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i= Integer.parseInt(request.getParameter("tpr_i").trim());
		int operId= Integer.parseInt(request.getParameter("operId").trim());
		int oper_i= Integer.parseInt(request.getParameter("oper_i").trim());
		int tranId= Integer.parseInt(request.getParameter("tranId").trim());
		try{techTransitDAO.deleteTechTransit(tranId);}
		catch(SQLException e){response.getWriter().println(e);}
		 response.sendRedirect(request.getContextPath() + "/techTransitList?prodId="+prodId
		+"&prod_i="+prod_i+"&tprId="+tprId+"&tpr_i="+tpr_i+"&operId="+operId+"&oper_i="+oper_i);
		
	}

}
