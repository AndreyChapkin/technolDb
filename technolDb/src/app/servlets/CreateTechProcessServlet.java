package app.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.SQLException;

import app.entities.*;

@WebServlet("/createTechProcess")
public class CreateTechProcessServlet extends HttpServlet {
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
		
		 int prodId=Integer.parseInt(request.getParameter("prodId"));
		 int prod_i=Integer.parseInt(request.getParameter("prod_i"));
		 
		 request.setAttribute("prodId",prodId);
		 request.setAttribute("prod_i",prod_i);
		 RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/createTechProcessView.jsp");
	     dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int prodId=Integer.parseInt(request.getParameter("prodId"));
		int prod_i=Integer.parseInt(request.getParameter("prod_i"));
		String company = request.getParameter("company");
		String developer = request.getParameter("developer");
		String supervisor = request.getParameter("supervisor");
		String approver = request.getParameter("approver");
		String workpieceCode =request.getParameter("workpieceCode");
		String workpieceSort =request.getParameter("workpieceSort");
		String workpieceProfile =request.getParameter("workpieceProfile");
		String workpieceSizes =request.getParameter("workpieceSizes");
		String workpieceWeight =request.getParameter("workpieceWeight");
			
		TechProcess techProcess = new TechProcess();
		techProcess.setProdId(prodId);
		techProcess.setCompany(company.trim());
		techProcess.setDeveloper(developer.trim());
		techProcess.setSupervisor(supervisor.trim());
		techProcess.setApprover(approver.trim());
		techProcess.setWorkpieceCode(workpieceCode.trim());
		techProcess.setWorkpieceSort(workpieceSort.trim());
		techProcess.setWorkpieceProfile(workpieceProfile.trim());
		techProcess.setWorkpieceSizes(workpieceSizes.trim());
		if (workpieceWeight != null && !workpieceWeight.trim().isEmpty()) {
			techProcess.setWorkpieceWeight(Float.parseFloat(workpieceWeight));}
		try{
			techProcessDAO.addTechProcess(techProcess);}
		catch(SQLException e){response.getWriter().println(e);}
	    response.sendRedirect(request.getContextPath() + "/techProcessList?prodId="+prodId
	    		+"&prod_i="+prod_i);
			 
	}

}
