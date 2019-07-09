package app.entities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DeviceDAO {
    private DataSource dataSource;
    
    public DeviceDAO(DataSource newDataSource) throws SQLException {
    	this.dataSource = newDataSource;
        checkTable();
    }
    
    public void addDevice(Device device) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
        	PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*)"
        			+ " FROM DEVICE");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int ident=rs.getInt(1);
            ident++;
            pstmt = connection.prepareStatement("INSERT INTO DEVICE"
                    + " VALUES (?, ?, ?, ?)");
            pstmt.setInt(1, ident);
            pstmt.setInt(2, device.getOperId());
            pstmt.setString(3, device.getDevName());
            pstmt.setString(4, device.getDevCode());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void editDevice(Device device) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
            		.prepareStatement("UPDATE DEVICE SET DEV_NAME=?,"
            				+ " DEV_CODE=? WHERE DEV_ID=?");
            pstmt.setString(1, device.getDevName());
            pstmt.setString(2, device.getDevCode());
            pstmt.setInt(3, device.getDevId());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
          }  
    }
    
    public void deleteDevice(int devId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM DEVICE"
                    		+ " WHERE DEV_ID=?");
            pstmt.setInt(1, devId);
            pstmt.executeUpdate();
            
            pstmt = connection.prepareStatement("SELECT COUNT(*)"
                			+ " FROM DEVICE");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int maxIdent=rs.getInt(1);
            int delIdent=devId;
            for(Integer i=delIdent; i<=maxIdent;){
            pstmt = connection
                    .prepareStatement("UPDATE DEVICE SET DEV_ID =?"
                    		+ "  WHERE DEV_ID=?");
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
    
    public List<Device> selectAllDevices(int operId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM DEVICE WHERE OPER_ID=?");
            pstmt.setInt(1,operId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Device> list = new ArrayList<Device>();
            while (rs.next()) {
                Device d = new Device();
                d.setDevId(rs.getInt(1));
                d.setOperId(rs.getInt(2));
                d.setDevName(rs.getString(3));
                d.setDevCode(rs.getString(4));
                list.add(d);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public Device selectOneDevice(int devId) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM DEVICE WHERE DEV_ID=?");
            pstmt.setInt(1, devId);
            ResultSet rs = pstmt.executeQuery();
            Device d = new Device();
            rs.next();
            d.setDevId(rs.getInt(1));
            d.setOperId(rs.getInt(2));
            d.setDevName(rs.getString(3));
            d.setDevCode(rs.getString(4));
            return d;
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
        ResultSet rs = meta.getTables(null, null, "DEVICE", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("DEVICE")) {
                return true;
            }
        }
        return false;
    }

    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE DEVICE( DEV_ID INT,"
                		+ "OPER_ID INT,"
                		+ "DEV_NAME VARCHAR(255),"
                		+ "DEV_CODE VARCHAR(255),"
                		+ "FOREIGN KEY (OPER_ID)"
                	    + "REFERENCES TECH_OPERATION(OPER_ID) ON DELETE CASCADE,"   //дописать on update cascade
                		+ "PRIMARY KEY (DEV_ID))");
        pstmt.executeUpdate();
    }
}
