/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

;

/**
 *
 * @author ahmed
 */
public class DbConnection {

    private static DbConnection adbConnection;
    private Connection aConnection;
    private Statement aStatement;

    private DbConnection() {
    }

    public static DbConnection getdbConnection() {
        if (adbConnection == null) {
            adbConnection = new DbConnection();
        }
        return adbConnection;
    }

    private void createStatement() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.aConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javadata",
                    "root",
                    ""
            );
            this.aStatement = this.aConnection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public void closeConnection() {
        try {
            this.aStatement.close();
            this.aConnection.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public int addInvoice(Invoice v) {
        createStatement();
        try {
            PreparedStatement ps = this.aConnection.prepareStatement(
                    "INSERT INTO invoices (number,description,quantity,price) VALUES(?,?,?,?)"
            );
            ps.setInt(1, v.getPartNumber());
            ps.setString(2, v.getPartDescription());
            ps.setInt(3, v.getQuantity());
            ps.setDouble(4, v.getPrice());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return -1;
    }
    
    public ArrayList<Invoice> getInvoices(){
        createStatement();
        ArrayList<Invoice> list = new ArrayList<>();
        try {
            ResultSet rs = this.aStatement.executeQuery("SELECT * FROM invoices");
            while(rs.next()){
                Invoice v = new Invoice(
                        rs.getInt("number"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                list.add(v);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
    
}
