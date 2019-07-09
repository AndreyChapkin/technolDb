package app.entities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TechControlDAO {
    private DataSource dataSource;
    
    public TechControlDAO(DataSource newDataSource) throws SQLException {
    	this.dataSource = newDataSource;
        checkTable();
    }
    
    public void addTechControl(TechControl techControl) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
        	PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*)"
        			+ " FROM TECH_CONTROL");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int ident=rs.getInt(1);
            ident++;
            pstmt = connection.prepareStatement("INSERT INTO TECH_CONTROL"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, ident);
            pstmt.setInt(2, techControl.getOperId());
            pstmt.setString(3, techControl.getCtrName());
            pstmt.setString(4, techControl.getCtrDeveloper());
            pstmt.setString(5, techControl.getCtrSupervisor());
            pstmt.setString(6, techControl.getCtrEquipment());
            pstmt.setString(7, techControl.getSafeInstr());
            pstmt.setFloat(8, techControl.getCtrBasicTime());
            pstmt.setFloat(9, techControl.getCtrSubsidTime());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void editTechControl(TechControl techControl) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
            		.prepareStatement("UPDATE TECH_CONTROL SET CTR_NAME=?,"
            				+ " CTR_DEVELOPER=?, CTR_SUPERVISOR=?, CTR_EQUIPMENT=?,"
            				+ " SAFE_INSTR=?, CTR_BASIC_TIME=?, CTR_SUBSID_TIME=?"
            				+ " WHERE CTR_ID=?");
            pstmt.setString(1, techControl.getCtrName());
            pstmt.setString(2, techControl.getCtrDeveloper());
            pstmt.setString(3, techControl.getCtrSupervisor());
            pstmt.setString(4, techControl.getCtrEquipment());
            pstmt.setString(5, techControl.getSafeInstr());
            pstmt.setFloat(6, techControl.getCtrBasicTime());
            pstmt.setFloat(7, techControl.getCtrSubsidTime());
            pstmt.setInt(8, techControl.getCtrId());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
          }  
    }
    
    public void deleteTechControl(int ctrId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM TECH_CONTROL"
                    		+ " WHERE CTR_ID=?");
            pstmt.setInt(1, ctrId);
            pstmt.executeUpdate();
            pstmt = connection.prepareStatement("SELECT COUNT(*)"
                			+ " FROM TECH_CONTROL");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int maxIdent=rs.getInt(1);
            int delIdent=ctrId;
            for(Integer i=delIdent; i<=maxIdent;){
            pstmt = connection
                    .prepareStatement("UPDATE TECH_CONTROL SET CTR_ID =?"
                    		+ "  WHERE CTR_ID=?");
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
    
    public List<TechControl> selectAllTechControls(int operId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM TECH_CONTROL WHERE OPER_ID=?");
            pstmt.setInt(1,operId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<TechControl> list = new ArrayList<TechControl>();
            while (rs.next()) {
                TechControl t = new TechControl();
                t.setCtrId(rs.getInt(1));
                t.setOperId(rs.getInt(2));
                t.setCtrName(rs.getString(3));
                t.setCtrDeveloper(rs.getString(4));
                t.setCtrSupervisor(rs.getString(5));
                t.setCtrEquipment(rs.getString(6));
                t.setSafeInstr(rs.getString(7));
                t.setCtrBasicTime(rs.getFloat(8));
                t.setCtrSubsidTime(rs.getFloat(9));
                list.add(t);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public TechControl selectOneTechControl(int ctrId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM TECH_CONTROL WHERE CTR_ID=?");
            pstmt.setInt(1, ctrId);
            ResultSet rs = pstmt.executeQuery();
            TechControl t = new TechControl();
            rs.next();
            t.setCtrId(rs.getInt(1));
            t.setOperId(rs.getInt(2));
            t.setCtrName(rs.getString(3));
            t.setCtrDeveloper(rs.getString(4));
            t.setCtrSupervisor(rs.getString(5));
            t.setCtrEquipment(rs.getString(6));
            t.setSafeInstr(rs.getString(7));
            t.setCtrBasicTime(rs.getFloat(8));
            t.setCtrSubsidTime(rs.getFloat(9));
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
        ResultSet rs = meta.getTables(null, null, "TECH_CONTROL", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("TECH_CONTROL")) {
                return true;
            }
        }
        return false;
    }

    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE TECH_CONTROL( CTR_ID INT,"
                		+ "OPER_ID INT,"
                		+ "CTR_NAME VARCHAR(255),"
                		+ "CTR_DEVELOPER VARCHAR(255),"
                		+ "CTR_SUPERVISOR VARCHAR(255),"
                		+ "CTR_EQUIPMENT VARCHAR(255),"
                		+ "SAFE_INSTR VARCHAR(255),"
                		+ "CTR_BASIC_TIME FLOAT,"
                		+ "CTR_SUBSID_TIME FLOAT, "
                		+ "FOREIGN KEY (OPER_ID) "
                	    + "REFERENCES TECH_OPERATION(OPER_ID) ON DELETE CASCADE,"   //дописать on update cascade
                		+ "PRIMARY KEY (CTR_ID))");
        pstmt.executeUpdate();
    }
}
