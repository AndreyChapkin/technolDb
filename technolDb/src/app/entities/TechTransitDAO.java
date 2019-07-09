package app.entities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TechTransitDAO {
    private DataSource dataSource;
    
    public TechTransitDAO(DataSource newDataSource) throws SQLException {
    	this.dataSource = newDataSource;
        checkTable();
    }
    
    public void addTechTransit(TechTransit techTransit) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
        	PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*)"
        			+ " FROM TECH_TRANSIT");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int ident=rs.getInt(1);
            ident++;
            pstmt = connection.prepareStatement("INSERT INTO TECH_TRANSIT"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, ident);
            pstmt.setInt(2, techTransit.getOperId());
            pstmt.setString(3, techTransit.getTransitNum());
            pstmt.setString(4, techTransit.getTransitCont());
            pstmt.setString(5, techTransit.getSubsidTool());
            pstmt.setString(6, techTransit.getCutTool());
            pstmt.setString(7, techTransit.getMeasTool());
            pstmt.setFloat(8, techTransit.getWidth());
            pstmt.setFloat(9, techTransit.getLength());
            pstmt.setFloat(10, techTransit.getDepth());
            pstmt.setInt(11, techTransit.getNumOfSteps());
            pstmt.setFloat(12, techTransit.getFeed());
            pstmt.setFloat(13, techTransit.getRotSpeed());
            pstmt.setFloat(14, techTransit.getCutSpeed());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void editTechTransit(TechTransit techTransit) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
            		.prepareStatement("UPDATE TECH_TRANSIT SET TRANSIT_NUM=?,"
            				+ " TRANSIT_CONT=?, SUBSID_TOOL=?, CUT_TOOL=?,"
            				+ " MEAS_TOOL=?, WIDTH=?, LENGTH=?,"
            				+ " DEPTH=?, NUM_OF_STEPS=?,"
            				+ " FEED=?, ROT_SPEED=?,"
            				+ " CUT_SPEED=? WHERE TRAN_ID=?");
            pstmt.setString(1, techTransit.getTransitNum());
            pstmt.setString(2, techTransit.getTransitCont());
            pstmt.setString(3, techTransit.getSubsidTool());
            pstmt.setString(4, techTransit.getCutTool());
            pstmt.setString(5, techTransit.getMeasTool());
            pstmt.setFloat(6, techTransit.getWidth());
            pstmt.setFloat(7, techTransit.getLength());
            pstmt.setFloat(8, techTransit.getDepth());
            pstmt.setInt(9, techTransit.getNumOfSteps());
            pstmt.setFloat(10, techTransit.getFeed());
            pstmt.setFloat(11, techTransit.getRotSpeed());
            pstmt.setFloat(12, techTransit.getCutSpeed());
            pstmt.setInt(13, techTransit.getTranId());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
          }  
    }
    
    public void deleteTechTransit(int tranId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM TECH_TRANSIT"
                    		+ " WHERE TRAN_ID=?");
            pstmt.setInt(1, tranId);
            pstmt.executeUpdate();
            
            pstmt = connection.prepareStatement("SELECT COUNT(*)"
                			+ " FROM TECH_TRANSIT");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int maxIdent=rs.getInt(1);
            int delIdent=tranId;
            for(Integer i=delIdent; i<=maxIdent;){
            pstmt = connection
                    .prepareStatement("UPDATE TECH_TRANSIT SET TRAN_ID =?"
                    		+ "  WHERE TRAN_ID=?");
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
    
    public List<TechTransit> selectAllTechTransits(int operId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM TECH_TRANSIT WHERE OPER_ID=?");
            pstmt.setInt(1,operId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<TechTransit> list = new ArrayList<TechTransit>();
            while (rs.next()) {
                TechTransit t = new TechTransit();
                t.setTranId(rs.getInt(1));
                t.setOperId(rs.getInt(2));
                t.setTransitNum(rs.getString(3));
                t.setTransitCont(rs.getString(4));
                t.setSubsidTool(rs.getString(5));
                t.setCutTool(rs.getString(6));
                t.setMeasTool(rs.getString(7));
                t.setWidth(rs.getFloat(8));
                t.setLength(rs.getFloat(9));
                t.setDepth(rs.getFloat(10));
                t.setNumOfSteps(rs.getInt(11));
                t.setFeed(rs.getFloat(12));
                t.setRotSpeed(rs.getFloat(13));
                t.setCutSpeed(rs.getFloat(14));
                list.add(t);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public TechTransit selectOneTechTransit(int tranId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM TECH_TRANSIT WHERE TRAN_ID=?");
            pstmt.setInt(1, tranId);
            ResultSet rs = pstmt.executeQuery();
            TechTransit t = new TechTransit();
            rs.next();
            t.setTranId(rs.getInt(1));
            t.setOperId(rs.getInt(2));
            t.setTransitNum(rs.getString(3));
            t.setTransitCont(rs.getString(4));
            t.setSubsidTool(rs.getString(5));
            t.setCutTool(rs.getString(6));
            t.setMeasTool(rs.getString(7));
            t.setWidth(rs.getFloat(8));
            t.setLength(rs.getFloat(9));
            t.setDepth(rs.getFloat(10));
            t.setNumOfSteps(rs.getInt(11));
            t.setFeed(rs.getFloat(12));
            t.setRotSpeed(rs.getFloat(13));
            t.setCutSpeed(rs.getFloat(14));
            return t;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
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
        ResultSet rs = meta.getTables(null, null, "TECH_TRANSIT", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("TECH_TRANSIT")) {
                return true;
            }
        }
        return false;
    }

    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE TECH_TRANSIT( TRAN_ID INT,"
                		+ "OPER_ID INT,"
                		+ "TRANSIT_NUM VARCHAR(255),"
                		+ "TRANSIT_CONT VARCHAR(255),"
                		+ "SUBSID_TOOL VARCHAR(255),"
                		+ "CUT_TOOL VARCHAR(255),"
                		+ "MEAS_TOOL VARCHAR(255),"
                		+ "WIDTH FLOAT,"
                		+ "LENGTH FLOAT,"
                		+ "DEPTH FLOAT,"
                		+ "NUM_OF_STEPS INT,"
                		+ "FEED FLOAT,"
                		+ "ROT_SPEED FLOAT,"
                		+ "CUT_SPEED FLOAT,"
                		+ "FOREIGN KEY (OPER_ID)"
                	    + "REFERENCES TECH_OPERATION(OPER_ID) ON DELETE CASCADE,"   //дописать on update cascade
                		+ "PRIMARY KEY (TRAN_ID))");
        pstmt.executeUpdate();
    }
}
