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

public class TechOperationDAO {
    private DataSource dataSource;
    
    public TechOperationDAO(DataSource newDataSource) throws SQLException {
    	this.dataSource = newDataSource;
        checkTable();
    }
    
    public void addTechOperation(TechOperation techOperation) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
        	PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*)"
        			+ " FROM TECH_OPERATION");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int ident=rs.getInt(1);
            ident++;
            pstmt = connection.prepareStatement("INSERT INTO TECH_OPERATION"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, ident);
            pstmt.setInt(2, techOperation.getTprId());
            pstmt.setString(3, techOperation.getOperName());
            pstmt.setString(4, techOperation.getWorkshopNum());
            pstmt.setString(5, techOperation.getAreaNum());
            pstmt.setString(6, techOperation.getOperNum());
            pstmt.setString(7, techOperation.getEquipment());
            pstmt.setString(8, techOperation.getCooling());
            pstmt.setInt(9, techOperation.getNumOfDetails());
            pstmt.setFloat(10, techOperation.getBasicTime());
            pstmt.setFloat(11, techOperation.getSubsidTime());
            pstmt.setFloat(12, techOperation.getPieceTime());
            InputStream is=new ByteArrayInputStream(techOperation.getSketch());
            pstmt.setBlob(13,is);
            pstmt.setString(14, techOperation.getSketchName());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void editTechOperation(TechOperation techOperation) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
            		.prepareStatement("UPDATE TECH_OPERATION SET OPER_NAME=?,"
            				+ " WORKSHOP_NUM=?, AREA_NUM=?, OPER_NUM=?,"
            				+ " EQUIPMENT=?, COOLING=?,"
            				+ " NUM_OF_DETAILS=?, BASIC_TIME=?,"
            				+ " SUBSID_TIME=?, PIECE_TIME=?"
            				+ " WHERE OPER_ID=?");
            pstmt.setString(1, techOperation.getOperName());
            pstmt.setString(2, techOperation.getWorkshopNum());
            pstmt.setString(3, techOperation.getAreaNum());
            pstmt.setString(4, techOperation.getOperNum());
            pstmt.setString(5, techOperation.getEquipment());
            pstmt.setString(6, techOperation.getCooling()); 
            pstmt.setInt(7, techOperation.getNumOfDetails());
            pstmt.setFloat(8, techOperation.getBasicTime());
            pstmt.setFloat(9, techOperation.getSubsidTime());
            pstmt.setFloat(10, techOperation.getPieceTime());
            pstmt.setInt(11, techOperation.getOperId());
            pstmt.executeUpdate();
            if(techOperation.getSketch().length>0){
            	pstmt = connection
                		.prepareStatement("UPDATE TECH_OPERATION"
                				+ " SET SKETCH=?, SKETCH_NAME=? WHERE OPER_ID=?");	
            	InputStream is=new ByteArrayInputStream(techOperation.getSketch());
                pstmt.setBlob(1,is);
                pstmt.setString(2, techOperation.getSketchName());
                pstmt.setInt(3, techOperation.getOperId());
                pstmt.executeUpdate();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
          }  
    }
    
    public void deleteTechOperation(int operId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM TECH_OPERATION"
                    		+ " WHERE OPER_ID=?");
            pstmt.setInt(1, operId);
            pstmt.executeUpdate();
            
            pstmt = connection.prepareStatement("SELECT COUNT(*)"
                			+ " FROM TECH_OPERATION");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int maxIdent=rs.getInt(1);
            int delIdent=operId;
            for(Integer i=delIdent; i<=maxIdent;){
            pstmt = connection
                    .prepareStatement("UPDATE TECH_OPERATION SET OPER_ID =?"
                    		+ "  WHERE OPER_ID=?");
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
    
    public List<TechOperation> selectAllTechOperations(int tprId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM TECH_OPERATION WHERE TPR_ID=?");
            pstmt.setInt(1,tprId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<TechOperation> list = new ArrayList<TechOperation>();
            while (rs.next()) {
                TechOperation t = new TechOperation();
                t.setOperId(rs.getInt(1));
                t.setTprId(rs.getInt(2));
                t.setOperName(rs.getString(3));
                t.setWorkshopNum(rs.getString(4));
                t.setAreaNum(rs.getString(5));
                t.setOperNum(rs.getString(6));
                t.setEquipment(rs.getString(7));
                t.setCooling(rs.getString(8));    
                t.setNumOfDetails(rs.getInt(9));
                t.setBasicTime(rs.getFloat(10));
                t.setSubsidTime(rs.getFloat(11));
                t.setPieceTime(rs.getFloat(12));
                list.add(t);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public TechOperation selectOneTechOperation(int operId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM TECH_OPERATION"
                    		+ " WHERE OPER_ID=?");
            pstmt.setInt(1, operId);
            ResultSet rs = pstmt.executeQuery();
            TechOperation t = new TechOperation();
            rs.next();
            t.setOperId(rs.getInt(1));
            t.setTprId(rs.getInt(2));
            t.setOperName(rs.getString(3));
            t.setWorkshopNum(rs.getString(4));
            t.setAreaNum(rs.getString(5));
            t.setOperNum(rs.getString(6));
            t.setEquipment(rs.getString(7));
            t.setCooling(rs.getString(8));    
            t.setNumOfDetails(rs.getInt(9));
            t.setBasicTime(rs.getFloat(10));
            t.setSubsidTime(rs.getFloat(11));
            t.setPieceTime(rs.getFloat(12));
            t.setSketch(rs.getBytes(13));
            t.setSketchName(rs.getString(14));
            return t;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public String extractSketchName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String sketchName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                sketchName = sketchName.replace("\\", "/");
                int i = sketchName.lastIndexOf('/');
                return sketchName.substring(i + 1);
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
        ResultSet rs = meta.getTables(null, null, "TECH_OPERATION", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("TECH_OPERATION")) {
                return true;
            }
        }
        return false;
    }

    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE TECH_OPERATION( OPER_ID INT,"
                		+ "TPR_ID INT,"
                		+ "OPER_NAME VARCHAR(255),"
                		+ "WORKSHOP_NUM VARCHAR(255),"
                		+ "AREA_NUM VARCHAR(255),"
                		+ "OPER_NUM VARCHAR(255),"
                		+ "EQUIPMENT VARCHAR(255),"
                		+ "COOLING VARCHAR(255),"
                		+ "NUM_OF_DETAILS INT,"
                		+ "BASIC_TIME FLOAT,"
                		+ "SUBSID_TIME FLOAT,"
                		+ "PIECE_TIME FLOAT,"
                		+ "SKETCH BLOB,"
                		+ "SKETCH_NAME VARCHAR(255),"
                		+ "FOREIGN KEY (TPR_ID)"
                	    + "REFERENCES TECH_PROCESS(TPR_ID) ON DELETE CASCADE,"   //дописать on update cascade
                		+ "PRIMARY KEY (OPER_ID))");
        pstmt.executeUpdate();
    }
}
