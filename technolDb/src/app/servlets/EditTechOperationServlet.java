package app.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;

import java.sql.SQLException;

import app.entities.*;

@WebServlet("/editTechOperation")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 20, // 20MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditTechOperationServlet extends HttpServlet {
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
		response.setCharacterEncoding("UTF-8");
		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		int tprId= Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i= Integer.parseInt(request.getParameter("tpr_i").trim());
		int operId= Integer.parseInt(request.getParameter("operId").trim());
		int oper_i= Integer.parseInt(request.getParameter("oper_i").trim());
		TechOperation techOperation=new TechOperation();
		try{
			techOperation=techOperationDAO.selectOneTechOperation(operId);
		}
		catch(SQLException e){response.getWriter().println(e);}
		
		 request.setAttribute("prodId", prodId);
		 request.setAttribute("prod_i", prod_i);
		 request.setAttribute("tprId", tprId);
		 request.setAttribute("tpr_i", tpr_i);
		 request.setAttribute("techOperation", techOperation);
		 request.setAttribute("oper_i", oper_i);
		
		 RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/editTechOperationView.jsp");
	     dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int prodId= Integer.parseInt(request.getParameter("prodId").trim());
		int prod_i= Integer.parseInt(request.getParameter("prod_i").trim());
		int tprId= Integer.parseInt(request.getParameter("tprId").trim());
		int tpr_i= Integer.parseInt(request.getParameter("tpr_i").trim());
		
		String operId = request.getParameter("operId");
		String operName = request.getParameter("operName");
		String workshopNum = request.getParameter("workshopNum");
		String areaNum = request.getParameter("areaNum");
		String operNum = request.getParameter("operNum");
		String equipment =request.getParameter("equipment");
		String cooling =request.getParameter("cooling");
		String numOfDetails =request.getParameter("numOfDetails");
		String basicTime =request.getParameter("basicTime");
		String subsidTime =request.getParameter("subsidTime");
		String pieceTime =request.getParameter("pieceTime");
		Part sketch =request.getPart("sketch");
		byte[] b=IOUtils.toByteArray(sketch.getInputStream());
		String sketchName =techOperationDAO.extractSketchName(sketch);
		
		TechOperation techOperation = new TechOperation();
		techOperation.setOperId(Integer.parseInt(operId));
		techOperation.setTprId(tprId);
		techOperation.setOperName(operName.trim());
		techOperation.setWorkshopNum(workshopNum.trim());
		techOperation.setAreaNum(areaNum.trim());
		techOperation.setOperNum(operNum.trim());
		techOperation.setEquipment(equipment.trim());
		techOperation.setCooling(cooling.trim());
		techOperation.setNumOfDetails(Integer.parseInt(numOfDetails));
		techOperation.setBasicTime(Float.parseFloat(basicTime));
		techOperation.setSubsidTime(Float.parseFloat(subsidTime));
		techOperation.setPieceTime(Float.parseFloat(pieceTime));
		techOperation.setSketch(b);
		techOperation.setSketchName(sketchName.trim());
			try{
			techOperationDAO.editTechOperation(techOperation);}
			catch(SQLException e){response.getWriter().println(e);}		
		response.sendRedirect(request.getContextPath() + "/techOperationList?prodId="+prodId
	    		+"&prod_i="+prod_i+"&tprId="+tprId+"&tpr_i="+tpr_i);		 	    
	}
}