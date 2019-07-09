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

@WebServlet("/createTechTransit")
public class CreateTechTransitServlet extends HttpServlet {
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
		int prodId=Integer.parseInt(request.getParameter("prodId"));
		int prod_i=Integer.parseInt(request.getParameter("prod_i"));
		int tprId=Integer.parseInt(request.getParameter("tprId"));
		int tpr_i=Integer.parseInt(request.getParameter("tpr_i"));
		int operId=Integer.parseInt(request.getParameter("operId"));
		int oper_i=Integer.parseInt(request.getParameter("oper_i"));
		 request.setAttribute("prodId",prodId);
		 request.setAttribute("prod_i",prod_i);
		 request.setAttribute("tprId",tprId);
		 request.setAttribute("tpr_i",tpr_i);
		 request.setAttribute("operId",operId);
		 request.setAttribute("oper_i",oper_i);
		 RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/createTechTransitView.jsp");
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
		techTransit.setOperId(operId);
		techTransit.setTransitNum(transitNum.trim());
		techTransit.setTransitCont(transitCont.trim());
		techTransit.setSubsidTool(subsidTool.trim());
		techTransit.setCutTool(cutTool.trim());
		techTransit.setMeasTool(measTool.trim());
		if (width != null && !width.trim().isEmpty()) {
			techTransit.setWidth(Float.parseFloat(width));}
		if (length != null && !length.trim().isEmpty()) {
			techTransit.setLength(Float.parseFloat(length));}
		if (depth != null && !depth.trim().isEmpty()) {
			techTransit.setDepth(Float.parseFloat(depth));}
		if (numOfSteps != null && !numOfSteps.trim().isEmpty()) {
			techTransit.setNumOfSteps(Integer.parseInt(numOfSteps));}
		if (feed != null && !feed.trim().isEmpty()) {
			techTransit.setFeed(Float.parseFloat(feed));}
		if (rotSpeed != null && !rotSpeed.trim().isEmpty()) {
			techTransit.setRotSpeed(Float.parseFloat(rotSpeed));}
		if (cutSpeed != null && !cutSpeed.trim().isEmpty()) {
			techTransit.setCutSpeed(Float.parseFloat(cutSpeed));}
		try{
			techTransitDAO.addTechTransit(techTransit);}
		catch(SQLException e){response.getWriter().println(e);}
	    response.sendRedirect(request.getContextPath() + "/techTransitList?prodId="+prodId
	    		+"&prod_i="+prod_i+"&tprId="+tprId+"&tpr_i="+tpr_i+"&operId="+operId+"&oper_i="+oper_i);
			 
	}

}
