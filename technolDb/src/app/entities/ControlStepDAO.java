package app.entities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ControlStepDAO {
    private DataSource dataSource;
    
    public ControlStepDAO(DataSource newDataSource) throws SQLException {
    	this.dataSource = newDataSource;
        checkTable();
    }
    
    public void addControlStep(ControlStep controlStep) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
        	PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*)"
        			+ " FROM CONTROL_STEP");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int ident=rs.getInt(1);
            ident++;
            pstmt = connection.prepareStatement("INSERT INTO CONTROL_STEP"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, ident);
            pstmt.setInt(2, controlStep.getCtrId());
            pstmt.setString(3, controlStep.getCtrParam());
            pstmt.setString(4, controlStep.getTechEqName());
            pstmt.setString(5, controlStep.getTechEqCode());
            pstmt.setString(6, controlStep.getBaseSubTime());
            pstmt.setInt(7, controlStep.getCtrValue());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void editControlStep(ControlStep controlStep) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
            		.prepareStatement("UPDATE CONTROL_STEP SET CTR_PARAM=?,"
            				+ " TECH_EQ_NAME=?, TECH_EQ_CODE=?, BASE_SUB_TIME=?,"
            				+ " CTR_VALUE=? WHERE STEP_ID=?");
            pstmt.setString(1, controlStep.getCtrParam());
            pstmt.setString(2, controlStep.getTechEqName());
            pstmt.setString(3, controlStep.getTechEqCode());
            pstmt.setString(4, controlStep.getBaseSubTime());
            pstmt.setInt(5, controlStep.getCtrValue());
            pstmt.setInt(6, controlStep.getStepId());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
          }  
    }
    
    public void deleteControlStep(int stepId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM CONTROL_STEP"
                    		+ " WHERE STEP_ID=?");
            pstmt.setInt(1, stepId);
            pstmt.executeUpdate();
            pstmt = connection.prepareStatement("SELECT COUNT(*)"
                			+ " FROM CONTROL_STEP");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int maxIdent=rs.getInt(1);
            int delIdent=stepId;
            for(Integer i=delIdent; i<=maxIdent;){
            pstmt = connection
                    .prepareStatement("UPDATE CONTROL_STEP SET STEP_ID =?"
                    		+ "  WHERE STEP_ID=?");
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
    
    public List<ControlStep> selectAllControlSteps(int ctrId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM CONTROL_STEP WHERE CTR_ID=?");
            pstmt.setInt(1,ctrId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<ControlStep> list = new ArrayList<ControlStep>();
            while (rs.next()) {
                ControlStep t = new ControlStep();
                t.setStepId(rs.getInt(1));
                t.setCtrId(rs.getInt(2));
                t.setCtrParam(rs.getString(3));
                t.setTechEqName(rs.getString(4));
                t.setTechEqCode(rs.getString(5));
                t.setBaseSubTime(rs.getString(6));
                t.setCtrValue(rs.getInt(7));
                list.add(t);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public ControlStep selectOneControlStep(int stepId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM CONTROL_STEP WHERE STEP_ID=?");
            pstmt.setInt(1, stepId);
            ResultSet rs = pstmt.executeQuery();
            ControlStep c = new ControlStep();
            rs.next();
            c.setStepId(rs.getInt(1));
            c.setCtrId(rs.getInt(2));
            c.setCtrParam(rs.getString(3));
            c.setTechEqName(rs.getString(4));
            c.setTechEqCode(rs.getString(5));
            c.setBaseSubTime(rs.getString(6));
            c.setCtrValue(rs.getInt(7));
            return c;
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
        ResultSet rs = meta.getTables(null, null, "CONTROL_STEP", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("CONTROL_STEP")) {
                return true;
            }
        }
        return false;
    }

    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE CONTROL_STEP( STEP_ID INT,"
                		+ "CTR_ID INT,"
                		+ "CTR_PARAM VARCHAR(255),"
                		+ "TECH_EQ_NAME VARCHAR(255),"
                		+ "TECH_EQ_CODE VARCHAR(255),"
                		+ "BASE_SUB_TIME VARCHAR(255),"
                		+ "CTR_VALUE INT, "
                		+ "FOREIGN KEY (CTR_ID) "
                	    + "REFERENCES TECH_CONTROL(CTR_ID) ON DELETE CASCADE,"   //дописать on update cascade
                		+ "PRIMARY KEY (STEP_ID))");
        pstmt.executeUpdate();
    }
}
