package app.entities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ProductDAO {
    private DataSource dataSource;
    
    public ProductDAO(DataSource newDataSource) throws SQLException {
    	this.dataSource = newDataSource;
        checkTable();
    }
    
    public void addProduct(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
        	Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM PRODUCT");
            rs.next();
            int ident=rs.getInt(1);
            ident++;
            PreparedStatement pstmt = connection
                    .prepareStatement("INSERT INTO PRODUCT"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, ident);
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getSignName());
            pstmt.setString(4, product.getMaterial());
            pstmt.setString(5, product.getMaterialBrand());
            pstmt.setFloat(6, product.getWeight());
            pstmt.setInt(7, product.getBatchSize());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void editProduct(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
            		.prepareStatement("UPDATE PRODUCT SET NAME=?, SIGNNAME=?, MATERIAL=?,"
            				+ " MATERIALBRAND=?, WEIGHT=?, BATCHSIZE=? WHERE ID=?");
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getSignName());
            pstmt.setString(3, product.getMaterial());
            pstmt.setString(4, product.materialBrand);
            pstmt.setFloat(5, product.getWeight());
            pstmt.setInt(6, product.getBatchSize());
            pstmt.setInt(7, product.getId());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
          }  
    }
    
    public void deleteProduct(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("DELETE FROM PRODUCT WHERE ID=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM PRODUCT");
            rs.next();
            int ident=rs.getInt(1);
            int delident=id;
            for(Integer i=delident; i<=ident;){
            pstmt = connection
                    .prepareStatement("UPDATE PRODUCT SET ID =?  WHERE ID=?");
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
    
    public List<Product> selectAllProducts() throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM PRODUCT");
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Product> list = new ArrayList<Product>();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setSignName(rs.getString(3));
                p.setMaterial(rs.getString(4));
                p.setMaterialBrand(rs.getString(5));
                p.setWeight(rs.getFloat(6));
                p.setBatchSize(rs.getInt(7));
                list.add(p);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public Product selectOneProduct(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM PRODUCT WHERE ID=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            Product p = new Product();
            rs.next();
            p.setId(rs.getInt(1));
            p.setName(rs.getString(2));
            p.setSignName(rs.getString(3));
            p.setMaterial(rs.getString(4));
            p.setMaterialBrand(rs.getString(5));
            p.setWeight(rs.getFloat(6));
            p.setBatchSize(rs.getInt(7));
            return p;
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
        ResultSet rs = meta.getTables(null, null, "PRODUCT", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("PRODUCT")) {
                return true;
            }
        }
        return false;
    }

    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE PRODUCT "
                        + "(ID INT PRIMARY KEY, "
                        + "NAME VARCHAR (255),"
                        + "SIGNNAME VARCHAR (255),"
                        + "MATERIAL VARCHAR (255),"
                        +"MATERIALBRAND VARCHAR (255),"
                        +"WEIGHT FLOAT,"
                        +"BATCHSIZE INT)");
        pstmt.executeUpdate();
    }
}
