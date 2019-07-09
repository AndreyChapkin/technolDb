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

@WebServlet("/editTechControl")
public class EditTechControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private TechControlDAO techControlDAO;

	public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
            techControlDAO = new TechControlDAO(ds);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		int prodId = Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i = Integer.parseInt(request.getParameter("prod_i").trim());
		int tprId = Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i = Integer.parseInt(request.getParameter("tpr_i").trim());
		int operId = Integer.parseInt(request.getParameter("operId").trim());
		int oper_i = Integer.parseInt(request.getParameter("oper_i").trim());
		int ctrId = Integer.parseInt(request.getParameter("ctrId").trim());
		int ctr_i = Integer.parseInt(request.getParameter("ctr_i").trim());
		TechControl techControl = new TechControl();
		try{
			techControl=techControlDAO.selectOneTechControl(ctrId);
		}
		catch(SQLException e){response.getWriter().println(e);}
		 request.setAttribute("prodId", prodId);
		 request.setAttribute("prod_i", prod_i);
		 request.setAttribute("tprId", tprId);
		 request.setAttribute("tpr_i", tpr_i);
		 request.setAttribute("operId", operId);
		 request.setAttribute("oper_i", oper_i);
		 request.setAttribute("techControl", techControl);
		 request.setAttribute("ctr_i", ctr_i);
		
		 RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/editTechControlView.jsp");
	     dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int prodId = Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i = Integer.parseInt(request.getParameter("prod_i").trim());
		int tprId = Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i = Integer.parseInt(request.getParameter("tpr_i").trim());
		int operId = Integer.parseInt(request.getParameter("operId").trim());
		int oper_i = Integer.parseInt(request.getParameter("oper_i").trim());
		
		String ctrId = request.getParameter("ctrId");
		String ctrDeveloper = request.getParameter("ctrDeveloper");
		String ctrSupervisor = request.getParameter("ctrSupervisor");
		String ctrName = request.getParameter("ctrName");
		String ctrEquipment = request.getParameter("ctrEquipment");
		String safeInstr =request.getParameter("safeInstr");
		String ctrBasicTime =request.getParameter("ctrBasicTime");
		String ctrSubsidTime =request.getParameter("ctrSubsidTime");
			
		TechControl techControl = new TechControl();
		techControl.setCtrId(Integer.parseInt(ctrId));
		techControl.setOperId(operId);
		techControl.setCtrDeveloper(ctrDeveloper.trim());
		techControl.setCtrSupervisor(ctrSupervisor.trim());
		techControl.setCtrName(ctrName.trim());
		techControl.setCtrEquipment(ctrEquipment.trim());
		techControl.setSafeInstr(safeInstr.trim());
		techControl.setCtrBasicTime(Float.parseFloat(ctrBasicTime));
		techControl.setCtrSubsidTime(Float.parseFloat(ctrSubsidTime));
		try{
			techControlDAO.editTechControl(techControl);}
			catch(SQLException e){response.getWriter().println(e);}		
		response.sendRedirect(request.getContextPath() + "/techControlList?prodId="+prodId
		+"&prod_i="+prod_i+"&tprId="+tprId+"&tpr_i="+tpr_i+"&operId="+operId+"&oper_i="+oper_i);		 	    
	}
}
