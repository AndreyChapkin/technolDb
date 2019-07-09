package app.entities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.servlet.http.Part;

public class RemarkDAO {
    private DataSource dataSource;
    
    public RemarkDAO(DataSource newDataSource) throws SQLException {
    	this.dataSource = newDataSource;
        checkTable();
    }
    
    public void addRemark(Remark remark) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
        	PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*)"
        			+ " FROM REMARK");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int ident=rs.getInt(1);
            ident++;
            pstmt = connection.prepareStatement("INSERT INTO REMARK"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, ident);
            pstmt.setInt(2, remark.getProdId());
            pstmt.setDate(3, remark.getRemDate());
            pstmt.setTime(4, remark.getRemTime());
            pstmt.setString(5, remark.getWriter());
            pstmt.setString(6, remark.getPost());
            pstmt.setString(7, remark.getRemCompany());
            pstmt.setString(8, remark.getComment());
            InputStream is=new ByteArrayInputStream(remark.getTextFile());
            pstmt.setBlob(9, is);
            pstmt.setString(10, remark.getFileName());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void editRemark(Remark remark) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
            		.prepareStatement("UPDATE REMARK SET REM_DATE=?,"
            				+ " REM_TIME=?, WRITER=?,"
            				+ " POST=?, REM_COMPANY=?,"
            				+ " COMMENT=? WHERE REM_ID=?");
            pstmt.setDate(1, remark.getRemDate());
            pstmt.setTime(2, remark.getRemTime());
            pstmt.setString(3, remark.getWriter());
            pstmt.setString(4, remark.getPost());
            pstmt.setString(5, remark.getRemCompany());
            pstmt.setString(6, remark.getComment());
            pstmt.setInt(7, remark.getRemId()); 
            pstmt.executeUpdate();
            if(remark.getTextFile().length>0){
            	pstmt = connection
                		.prepareStatement("UPDATE REMARK SET TEXT_FILE=?,"
                				+ " FILE_NAME=? WHERE REM_ID=?");	
            	InputStream is=new ByteArrayInputStream(remark.getTextFile());
                pstmt.setBlob(1,is);
                pstmt.setString(2, remark.getFileName());
                pstmt.setInt(3, remark.getRemId());
                pstmt.executeUpdate();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
          }  
    }
    
    public void deleteRemark(int remId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM REMARK"
                    		+ " WHERE REM_ID=?");
            pstmt.setInt(1, remId);
            pstmt.executeUpdate();
            
            pstmt = connection.prepareStatement("SELECT COUNT(*)"
                			+ " FROM REMARK");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int maxIdent=rs.getInt(1);
            int delIdent=remId;
            for(Integer i=delIdent; i<=maxIdent;){
            pstmt = connection
                    .prepareStatement("UPDATE REMARK SET REM_ID =?"
                    		+ "  WHERE REM_ID=?");
            pstmt.setInt(1, i);
            i++;
            pstmt.setInt(2, i);
            pstmt.executeUpdate();}
        } finally {
            if (connection != null) {
                connection.close();
            }
          }
    }
    
    public List<Remark> selectAllRemarks(int prodId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM REMARK WHERE PROD_ID=?");
            pstmt.setInt(1,prodId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Remark> list = new ArrayList<Remark>();
            while (rs.next()) {
            	Remark r = new Remark();
                r.setRemId(rs.getInt(1));
                r.setProdId(rs.getInt(2));
                r.setRemDate(rs.getDate(3));
                r.setRemTime(rs.getTime(4));
                r.setWriter(rs.getString(5));
                r.setPost(rs.getString(6));
                r.setRemCompany(rs.getString(7));
                r.setComment(rs.getString(8));    
                list.add(r);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public Remark selectOneRemark(int remId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM REMARK"
                    		+ " WHERE REM_ID=?");
            pstmt.setInt(1, remId);
            ResultSet rs = pstmt.executeQuery();
            Remark r = new Remark();
            rs.next();
            r.setRemId(rs.getInt(1));
            r.setProdId(rs.getInt(2));
            r.setRemDate(rs.getDate(3));
            r.setRemTime(rs.getTime(4));
            r.setWriter(rs.getString(5));
            r.setPost(rs.getString(6));
            r.setRemCompany(rs.getString(7));
            r.setComment(rs.getString(8));
            r.setTextFile(rs.getBytes(9));
            r.setFileName(rs.getString(10));
            return r;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                fileName = fileName.replace("\\", "/");
                int i = fileName.lastIndexOf('/');
                return fileName.substring(i + 1);
            }
        }
        return null;
    }

    private void checkTable() throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            if (!existsTable(connection)) {
                createTable(connection);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private boolean existsTable(Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "REMARK", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("REMARK")) {
                return true;
            }
        }
        return false;
    }

    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE REMARK( REM_ID INT,"
                		+ "PROD_ID INT,"
                		+ "REM_DATE DATE,"
                		+ "REM_TIME TIME,"
                		+ "WRITER VARCHAR(255),"
                		+ "POST VARCHAR(255),"
                		+ "REM_COMPANY VARCHAR(255),"
                		+ "COMMENT VARCHAR(255),"
                		+ "TEXT_FILE BLOB,"
                		+ "FILE_NAME VARCHAR(255),"
                		+ "FOREIGN KEY (PROD_ID)"
                	    + "REFERENCES PRODUCT(ID) ON DELETE CASCADE,"   //дописать on update cascade
                		+ "PRIMARY KEY (REM_ID))");
        pstmt.executeUpdate();
    }
}
