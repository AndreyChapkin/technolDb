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

import javax.servlet.http.Part;
import javax.sql.DataSource;

public class DesignDocumentDAO {
    private DataSource dataSource;
    
    public DesignDocumentDAO(DataSource newDataSource) throws SQLException {
    	this.dataSource = newDataSource;
        checkTable();
    }
    
    public void addDesignDocument(DesignDocument designDocument) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
        	PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*)"
        			+ " FROM DESIGN_DOCUMENT");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int ident=rs.getInt(1);
            ident++;
            pstmt = connection.prepareStatement("INSERT INTO DESIGN_DOCUMENT"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, ident);
            pstmt.setInt(2, designDocument.getProdId());
            pstmt.setString(3, designDocument.getDocType());
            pstmt.setString(4, designDocument.getDocSign());
            InputStream is=new ByteArrayInputStream(designDocument.getDocument());
            pstmt.setBlob(5, is);
            pstmt.setString(6, designDocument.getDocFileName());
            pstmt.setString(7, designDocument.getDocDeveloper());
            pstmt.setString(8, designDocument.getDocSupervisor());
            pstmt.setString(9, designDocument.getDocApprover());
            pstmt.setString(10, designDocument.getCompanyName());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void editDesignDocument(DesignDocument designDocument) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
            		.prepareStatement("UPDATE DESIGN_DOCUMENT SET DOC_TYPE=?,"
            				+ " DOC_SIGN=?, DOC_DEVELOPER=?,"
            				+ " DOC_SUPERVISOR=?, DOC_APPROVER=?,"
            				+ " COMPANY_NAME=? WHERE DES_ID=?");
            pstmt.setString(1, designDocument.getDocType());
            pstmt.setString(2, designDocument.getDocSign());
            pstmt.setString(3, designDocument.getDocDeveloper());
            pstmt.setString(4, designDocument.getDocSupervisor());
            pstmt.setString(5, designDocument.getDocApprover());
            pstmt.setString(6, designDocument.getCompanyName());
            pstmt.setInt(7, designDocument.getDesId()); 
            pstmt.executeUpdate();
            if(designDocument.getDocument().length>0){
            	pstmt = connection
                		.prepareStatement("UPDATE DESIGN_DOCUMENT SET DOCUMENT=?, "
                				+ "DOC_FILE_NAME=? WHERE DES_ID=?");	
            	InputStream is=new ByteArrayInputStream(designDocument.getDocument());
                pstmt.setBlob(1,is);
                pstmt.setString(2, designDocument.getDocFileName());
                pstmt.setInt(3, designDocument.getDesId());
                pstmt.executeUpdate();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
          }  
    }
    
    public void deleteDesignDocument(int desId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM DESIGN_DOCUMENT"
                    		+ " WHERE DES_ID=?");
            pstmt.setInt(1, desId);
            pstmt.executeUpdate();
            
            pstmt = connection.prepareStatement("SELECT COUNT(*)"
                			+ " FROM DESIGN_DOCUMENT");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int maxIdent=rs.getInt(1);
            int delIdent=desId;
            for(Integer i=delIdent; i<=maxIdent;){
            pstmt = connection
                    .prepareStatement("UPDATE DESIGN_DOCUMENT SET DES_ID =?"
                    		+ "  WHERE DES_ID=?");
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
    
    public List<DesignDocument> selectAllDesignDocuments(int prodId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM DESIGN_DOCUMENT WHERE PROD_ID=?");
            pstmt.setInt(1,prodId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<DesignDocument> list = new ArrayList<DesignDocument>();
            while (rs.next()) {
            	DesignDocument d = new DesignDocument();
                d.setDesId(rs.getInt(1));
                d.setProdId(rs.getInt(2));
                d.setDocType(rs.getString(3));
                d.setDocSign(rs.getString(4));
                d.setDocDeveloper(rs.getString(7));
                d.setDocSupervisor(rs.getString(8));
                d.setDocApprover(rs.getString(9));
                d.setCompanyName(rs.getString(10));    
                list.add(d);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public DesignDocument selectOneDesignDocument(int desId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM DESIGN_DOCUMENT"
                    		+ " WHERE DES_ID=?");
            pstmt.setInt(1, desId);
            ResultSet rs = pstmt.executeQuery();
            DesignDocument d = new DesignDocument();
            rs.next();
            d.setDesId(rs.getInt(1));
            d.setProdId(rs.getInt(2));
            d.setDocType(rs.getString(3));
            d.setDocSign(rs.getString(4));
            d.setDocument(rs.getBytes(5));
            d.setDocFileName(rs.getString(6));
            d.setDocDeveloper(rs.getString(7));
            d.setDocSupervisor(rs.getString(8));
            d.setDocApprover(rs.getString(9));
            d.setCompanyName(rs.getString(10));
            return d;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public String extractDocumentName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String docFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                docFileName = docFileName.replace("\\", "/");
                int i = docFileName.lastIndexOf('/');
                return docFileName.substring(i + 1);
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
        ResultSet rs = meta.getTables(null, null, "DESIGN_DOCUMENT", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("DESIGN_DOCUMENT")) {
                return true;
            }
        }
        return false;
    }

    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE DESIGN_DOCUMENT( DES_ID INT,"
                		+ "PROD_ID INT,"
                		+ "DOC_TYPE VARCHAR(255),"
                		+ "DOC_SIGN VARCHAR(255),"
                		+ "DOCUMENT BLOB,"
                		+ "DOC_FILE_NAME VARCHAR(255),"
                		+ "DOC_DEVELOPER VARCHAR(255),"
                		+ "DOC_SUPERVISOR VARCHAR(255),"
                		+ "DOC_APPROVER VARCHAR(255),"
                		+ "COMPANY_NAME VARCHAR(255),"
                		+ "FOREIGN KEY (PROD_ID)"
                	    + "REFERENCES PRODUCT(ID) ON DELETE CASCADE,"   //дописать on update cascade
                		+ "PRIMARY KEY (DES_ID))");
        pstmt.executeUpdate();
    }
}
