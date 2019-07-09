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

@WebServlet("/createControlStep")
public class CreateControlStepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControlStepDAO controlStepDAO;
	
	public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
            controlStepDAO = new ControlStepDAO(ds);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int prodId=Integer.parseInt(request.getParameter("prodId"));
		int prod_i=Integer.parseInt(request.getParameter("prod_i"));
		int tprId=Integer.parseInt(request.getParameter("tprId"));
		int tpr_i=Integer.parseInt(request.getParameter("tpr_i"));
		int operId=Integer.parseInt(request.getParameter("operId"));
		int oper_i=Integer.parseInt(request.getParameter("oper_i"));
		int ctrId=Integer.parseInt(request.getParameter("ctrId"));
		int ctr_i=Integer.parseInt(request.getParameter("ctr_i"));
		int step_i=Integer.parseInt(request.getParameter("step_i"));
		
		request.setAttribute("prodId",prodId);
		request.setAttribute("prod_i",prod_i);
		request.setAttribute("tprId",tprId);
		request.setAttribute("tpr_i",tpr_i);
		request.setAttribute("operId",operId);
		request.setAttribute("oper_i",oper_i);
		request.setAttribute("ctrId",ctrId);
		request.setAttribute("ctr_i",ctr_i);
		request.setAttribute("step_i",step_i);
		 RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/createControlStepView.jsp");
	     dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int prodId=Integer.parseInt(request.getParameter("prodId"));
		int prod_i=Integer.parseInt(request.getParameter("prod_i"));
		int tprId=Integer.parseInt(request.getParameter("tprId"));
		int tpr_i=Integer.parseInt(request.getParameter("tpr_i"));
		int operId=Integer.parseInt(request.getParameter("operId"));
		int oper_i=Integer.parseInt(request.getParameter("oper_i"));
		int ctrId=Integer.parseInt(request.getParameter("ctrId"));
		int ctr_i=Integer.parseInt(request.getParameter("ctr_i"));
		int step_i=Integer.parseInt(request.getParameter("step_i"));
		
		String ctrParam = request.getParameter("ctrParam");
		String techEqName = request.getParameter("techEqName");
		String techEqCode = request.getParameter("techEqCode");
		String baseSubTime = request.getParameter("baseSubTime");
		String ctrValue = request.getParameter("ctrValue");
			
		ControlStep controlStep = new ControlStep();
		controlStep.setCtrId(ctrId);
		controlStep.setCtrParam(ctrParam.trim());
		controlStep.setTechEqName(techEqName.trim());
		controlStep.setTechEqCode(techEqCode.trim());
		if (baseSubTime != null && !baseSubTime.trim().isEmpty()) {
			controlStep.setBaseSubTime(baseSubTime);}
		else {controlStep.setBaseSubTime("0/0");}
		if (ctrValue != null && !ctrValue.trim().isEmpty()) {
			controlStep.setCtrValue(Integer.parseInt(ctrValue));}
		try{
			controlStepDAO.addControlStep(controlStep);}
		catch(SQLException e){response.getWriter().println(e);}
	    response.sendRedirect(request.getContextPath() + "/controlStepList?prodId="+prodId
	    		+"&prod_i="+prod_i+"&tprId="+tprId+"&tpr_i="+tpr_i+"&operId="+operId+"&oper_i="+oper_i
	    		+"&ctrId="+ctrId+"&ctr_i="+ctr_i+"#"+step_i); 
	}

}