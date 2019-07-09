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

@WebServlet("/deleteControlStep")
public class DeleteControlStepServlet extends HttpServlet {
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

		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		int tprId= Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i= Integer.parseInt(request.getParameter("tpr_i").trim());
		int operId= Integer.parseInt(request.getParameter("operId").trim());
		int oper_i= Integer.parseInt(request.getParameter("oper_i").trim());
		int ctrId= Integer.parseInt(request.getParameter("ctrId").trim());
		int ctr_i= Integer.parseInt(request.getParameter("ctr_i").trim());
		int stepId= Integer.parseInt(request.getParameter("stepId").trim());
		int step_i= Integer.parseInt(request.getParameter("step_i").trim());
		
		ControlStep controlStep = new ControlStep();
		
		try{controlStep = controlStepDAO.selectOneControlStep(stepId);}
		catch(SQLException e){response.getWriter().println(e);}
		
		request.setAttribute("prodId", prodId);
		request.setAttribute("prod_i", prod_i);
		request.setAttribute("tprId", tprId);
		request.setAttribute("tpr_i", tpr_i);
		request.setAttribute("operId", operId);
		request.setAttribute("oper_i", oper_i);
		request.setAttribute("ctrId", ctrId);
		request.setAttribute("ctr_i", ctr_i);
		request.setAttribute("controlStep", controlStep);
		request.setAttribute("step_i", step_i);
		
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/deleteControlStepView.jsp");
     dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		int tprId= Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i= Integer.parseInt(request.getParameter("tpr_i").trim());
		int operId= Integer.parseInt(request.getParameter("operId").trim());
		int oper_i= Integer.parseInt(request.getParameter("oper_i").trim());
		int ctrId=Integer.parseInt(request.getParameter("ctrId".trim()));
		int ctr_i=Integer.parseInt(request.getParameter("ctr_i".trim()));
		int stepId= Integer.parseInt(request.getParameter("stepId").trim());
		int step_i=Integer.parseInt(request.getParameter("step_i".trim()));
		
		try{controlStepDAO.deleteControlStep(stepId);}
		catch(SQLException e){response.getWriter().println(e);}
		 response.sendRedirect(request.getContextPath() + "/controlStepList?prodId="+prodId
		+"&prod_i="+prod_i+"&tprId="+tprId+"&tpr_i="+tpr_i+"&operId="+operId+"&oper_i="+oper_i
		+"&ctrId="+ctrId+"&ctr_i="+ctr_i+"#"+(step_i-1));
		
	}

}
