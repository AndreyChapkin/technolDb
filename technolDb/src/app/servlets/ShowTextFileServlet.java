package app.servlets;

import java.io.IOException;

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

@WebServlet("/showTextFile")
public class ShowTextFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RemarkDAO remarkDAO;

	public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
            remarkDAO = new RemarkDAO(ds);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		String remId=(String) request.getParameter("remId");
		Remark remark=new Remark();
		try{
			remark = remarkDAO.selectOneRemark(Integer.parseInt(remId));
		}
		catch(SQLException e){response.getWriter().println(e);}
      
		String fileName = remark.getFileName();   
        String contentType = this.getServletContext().getMimeType(fileName);
        
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", String.valueOf(remark.getTextFile().length));
        response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
        
        response.getOutputStream().write(remark.getTextFile());
        } 
		catch (Exception e) {  throw new ServletException(e); }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);		 
	}
}