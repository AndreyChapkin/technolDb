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

@WebServlet("/editTechTransit")
public class EditTechTransitServlet extends HttpServlet {
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
		response.setCharacterEncoding("UTF-8");
		int prodId = Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i = Integer.parseInt(request.getParameter("prod_i").trim());
		int tprId = Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i = Integer.parseInt(request.getParameter("tpr_i").trim());
		int operId = Integer.parseInt(request.getParameter("operId").trim());
		int oper_i = Integer.parseInt(request.getParameter("oper_i").trim());
		int tranId = Integer.parseInt(request.getParameter("tranId").trim());
		int tran_i = Integer.parseInt(request.getParameter("tran_i").trim());
		TechTransit techTransit = new TechTransit();
		try{
			techTransit=techTransitDAO.selectOneTechTransit(tranId);
		}
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
	                .getRequestDispatcher("/WEB-INF/views/editTechTransitView.jsp");
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
		
		String tranId = request.getParameter("tranId");
		String transitNum = request.getParameter("transitNum");
		String transitCont = request.getParameter("transitCont");
		String subsidTool = request.getParameter("subsidTool");
		String cutTool = request.getParameter("cutTool");
		String measTool =request.getParameter("measTool");
		String width =request.getParameter("width");
		String length =request.getParameter("length");
		String depth =request.getParameter("depth");
		String numOfSteps =request.getParameter("numOfSteps");
		String feed =request.getParameter("feed");
		String rotSpeed =request.getParameter("rotSpeed");
		String cutSpeed =request.getParameter("cutSpeed");
			
		TechTransit techTransit = new TechTransit();
		techTransit.setTranId(Integer.parseInt(tranId));
		techTransit.setOperId(operId);
		techTransit.setTransitNum(transitNum.trim());
		techTransit.setTransitCont(transitCont.trim());
		techTransit.setSubsidTool(subsidTool.trim());
		techTransit.setCutTool(cutTool.trim());
		techTransit.setMeasTool(measTool.trim());
		techTransit.setWidth(Float.parseFloat(width));
		techTransit.setLength(Float.parseFloat(length));
		techTransit.setDepth(Float.parseFloat(depth));
		techTransit.setNumOfSteps(Integer.parseInt(numOfSteps));
		techTransit.setFeed(Float.parseFloat(feed));
		techTransit.setRotSpeed(Float.parseFloat(rotSpeed));
		techTransit.setCutSpeed(Float.parseFloat(cutSpeed));
			try{
			techTransitDAO.editTechTransit(techTransit);}
			catch(SQLException e){response.getWriter().println(e);}		
		response.sendRedirect(request.getContextPath() + "/techTransitList?prodId="+prodId
		+"&prod_i="+prod_i+"&tprId="+tprId+"&tpr_i="+tpr_i+"&operId="+operId+"&oper_i="+oper_i);		 	    
	}
}