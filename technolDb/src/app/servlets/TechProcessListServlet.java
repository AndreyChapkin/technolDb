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

@WebServlet("/techProcessList")
public class TechProcessListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDAO;
	private TechProcessDAO techProcessDAO;
	
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
            techProcessDAO = new TechProcessDAO(ds);
            productDAO = new ProductDAO(ds);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setCharacterEncoding("UTF-8");
		Product product =new Product();
		int prodId=Integer.parseInt(request.getParameter("prodId"));
		int prod_i=Integer.parseInt(request.getParameter("prod_i"));
		try{product =productDAO.selectOneProduct(prodId);}
		catch(SQLException e){response.getWriter().println(e);}
		List<TechProcess> resultList=null;
		try{
		resultList = techProcessDAO.selectAllTechProcesses(prodId);
		}
		catch(SQLException e){response.getWriter().println(e);}
		
		request.setAttribute("prod_i", prod_i);
		request.setAttribute("product", product);
		request.setAttribute("techProcessList", resultList);
		
		RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/techProcessListView.jsp");
	    dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
