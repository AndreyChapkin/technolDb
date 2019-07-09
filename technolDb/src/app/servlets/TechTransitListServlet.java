package app.servlets;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/techTransitList")
public class TechTransitListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ProductDAO productDAO;
	private TechProcessDAO techProcessDAO;
	private TechOperationDAO techOperationDAO;
	private TechTransitDAO techTransitDAO;
	private DeviceDAO deviceDAO;
	
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
            techProcessDAO = new TechProcessDAO(ds);
            productDAO = new ProductDAO(ds);
            techOperationDAO = new TechOperationDAO(ds);
            techTransitDAO = new TechTransitDAO(ds);
            deviceDAO = new DeviceDAO(ds);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setCharacterEncoding("UTF-8");
		Product product = new Product();
		TechProcess techProcess = new TechProcess();
		TechOperation techOperation = new TechOperation();
		List<Device> deviceList=null;
		int prodId=Integer.parseInt(request.getParameter("prodId".trim()));
		int prod_i=Integer.parseInt(request.getParameter("prod_i".trim()));
		int tprId = Integer.parseInt(request.getParameter("tprId".trim()));
		int tpr_i=Integer.parseInt(request.getParameter("tpr_i".trim()));
		int operId=Integer.parseInt(request.getParameter("operId".trim()));
		int oper_i=Integer.parseInt(request.getParameter("oper_i".trim()));
		try{
			product =productDAO.selectOneProduct(prodId);
			techProcess = techProcessDAO.selectOneTechProcess(tprId);
			techOperation = techOperationDAO.selectOneTechOperation(operId);
			deviceList = deviceDAO.selectAllDevices(operId);
		}
		catch(SQLException e){response.getWriter().println(e);}
		List<TechTransit> resultList=null;
		try{
		resultList = techTransitDAO.selectAllTechTransits(operId);
		}
		catch(SQLException e){response.getWriter().println(e);}
		
		request.setAttribute("product", product);
		request.setAttribute("prod_i", prod_i);
		request.setAttribute("techProcess", techProcess);
		request.setAttribute("tpr_i", tpr_i);
		request.setAttribute("techOperation", techOperation);
		request.setAttribute("oper_i", oper_i);
		request.setAttribute("deviceList", deviceList);
		request.setAttribute("techTransitList", resultList);
		
		RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/techTransitListView.jsp");
	    dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}