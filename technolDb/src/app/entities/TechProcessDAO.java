package app.entities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TechProcessDAO {
    private DataSource dataSource;
    
    public TechProcessDAO(DataSource newDataSource) throws SQLException {
    	this.dataSource = newDataSource;
        checkTable();
    }
    
    public void addTechProcess(TechProcess techProcess) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
        	PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*)"
        			+ " FROM TECH_PROCESS");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int ident=rs.getInt(1);
            ident++;
            pstmt = connection.prepareStatement("INSERT INTO TECH_PROCESS"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, ident);
            pstmt.setInt(2, techProcess.getProdId());
            pstmt.setString(3, techProcess.getCompany());
            pstmt.setString(4, techProcess.getDeveloper());
            pstmt.setString(5, techProcess.getSupervisor());
            pstmt.setString(6, techProcess.getApprover());
            pstmt.setString(7, techProcess.getWorkpieceCode());
            pstmt.setString(8, techProcess.getWorkpieceSort());
            pstmt.setString(9, techProcess.getWorkpieceProfile());
            pstmt.setString(10, techProcess.getWorkpieceSizes());
            pstmt.setFloat(11, techProcess.getWorkpieceWeight());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void editTechProcess(TechProcess techProcess) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
            		.prepareStatement("UPDATE TECH_PROCESS SET COMPANY=?,"
            				+ " DEVELOPER=?, SUPERVISOR=?, APPROVER=?,"
            				+ " WORKPIECE_CODE=?, WORKPIECE_SORT=?,"
            				+ " WORKPIECE_PROFILE=?, WORKPIECE_SIZES=?,"
            				+ "WORKPIECE_WEIGHT=? WHERE TPR_ID=?");
            pstmt.setString(1, techProcess.getCompany());
            pstmt.setString(2, techProcess.getDeveloper());
            pstmt.setString(3, techProcess.getSupervisor());
            pstmt.setString(4, techProcess.getApprover());
            pstmt.setString(5, techProcess.getWorkpieceCode());
            pstmt.setString(6, techProcess.getWorkpieceSort());
            pstmt.setString(7, techProcess.getWorkpieceProfile());
            pstmt.setString(8, techProcess.getWorkpieceSizes());
            pstmt.setFloat(9, techProcess.getWorkpieceWeight());
            pstmt.setInt(10, techProcess.getTprId());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
          }  
    }
    
    public void deleteTechProcess(int tprId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM TECH_PROCESS"
                    		+ " WHERE TPR_ID=?");
            pstmt.setInt(1, tprId);
            pstmt.executeUpdate();
            
            pstmt = connection.prepareStatement("SELECT COUNT(*)"
                			+ " FROM TECH_PROCESS");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int maxIdent=rs.getInt(1);
            int delIdent=tprId;
            for(Integer i=delIdent; i<=maxIdent;){
            pstmt = connection
                    .prepareStatement("UPDATE TECH_PROCESS SET TPR_ID =?"
                    		+ "  WHERE TPR_ID=?");
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
    
    public List<TechProcess> selectAllTechProcesses(int prodId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM TECH_PROCESS WHERE PROD_ID=?");
            pstmt.setInt(1,prodId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<TechProcess> list = new ArrayList<TechProcess>();
            while (rs.next()) {
                TechProcess t = new TechProcess();
                t.setTprId(rs.getInt(1));
                t.setProdId(rs.getInt(2));
                t.setCompany(rs.getString(3));
                t.setDeveloper(rs.getString(4));
                t.setSupervisor(rs.getString(5));
                t.setApprover(rs.getString(6));
                t.setWorkpieceCode(rs.getString(7));
                t.setWorkpieceSort(rs.getString(8));
                t.setWorkpieceProfile(rs.getString(9));
                t.setWorkpieceSizes(rs.getString(10));
                t.setWorkpieceWeight(rs.getFloat(11));
                list.add(t);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public TechProcess selectOneTechProcess(int tprId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM TECH_PROCESS"
                    		+ " WHERE TPR_ID=?");
            pstmt.setInt(1, tprId);
            ResultSet rs = pstmt.executeQuery();
            TechProcess t = new TechProcess();
            rs.next();
            t.setTprId(rs.getInt(1));
            t.setProdId(rs.getInt(2));
            t.setCompany(rs.getString(3));
            t.setDeveloper(rs.getString(4));
            t.setSupervisor(rs.getString(5));
            t.setApprover(rs.getString(6));
            t.setWorkpieceCode(rs.getString(7));
            t.setWorkpieceSort(rs.getString(8));
            t.setWorkpieceProfile(rs.getString(9));
            t.setWorkpieceSizes(rs.getString(10));
            t.setWorkpieceWeight(rs.getFloat(11));
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
        ResultSet rs = meta.getTables(null, null, "TECH_PROCESS", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("TECH_PROCESS")) {
                return true;
            }
        }
        return false;
    }

    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE TECH_PROCESS( TPR_ID INT,"
                		+ "PROD_ID INT,"
                		+ "COMPANY VARCHAR(255),"
                		+ "DEVELOPER VARCHAR(255),"
                		+ "SUPERVISOR VARCHAR(255),"
                		+ "APPROVER VARCHAR(255),"
                		+ "WORKPIECE_CODE VARCHAR(255),"
                		+ "WORKPIECE_SORT VARCHAR(255),"
                		+ "WORKPIECE_PROFILE VARCHAR(255),"
                		+ "WORKPIECE_SIZES VARCHAR(255),"
                		+ "WORKPIECE_WEIGHT FLOAT,"
                		+ "FOREIGN KEY (PROD_ID) "
                	    + "REFERENCES PRODUCT(ID) ON DELETE CASCADE,"   //дописать on update cascade
                		+ "PRIMARY KEY (TPR_ID))");
        pstmt.executeUpdate();
    }
}
