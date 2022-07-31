/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Connection {
    int i;
    java.sql.Connection connection;
    Statement statement;
    String SQL;
    String url;
    String username;
    String password;
    String Host;
    int Port;
    
    public String eksekusiUpdate(String sql) {
        ConnectionDb();
        String result = "";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            result = ex.toString();
            javax.swing.JOptionPane.showMessageDialog(null,result);
            //javax.swing.JOptionPane.showMessageDialog(null, ex); //Pesan Error jika Duplikat data  
        }
       return result;
    }
    
    public String queryInsert(String nameTable, String[] namecolumn, String[] value) {
        SQL = "INSERT INTO " + nameTable +" (";
        for (i = 0; i <= namecolumn.length - 1; i++) {
            SQL +=namecolumn[i];
            if (i < namecolumn.length - 1) {
                SQL += ",";
            }
        }
        SQL+=") VALUES(";
        for (i = 0; i <= value.length - 1; i++) {
            SQL += "'" + value[i] + "'";
            if (i < value.length - 1) {
                SQL += ",";
            }
        }
        SQL += ")";
        return this.eksekusiUpdate(SQL);
    }
    public Connection(String url, String username, String password, String Host, int Port)
    {
        System.out.println(Host+Port);
        this.url = url;
        this.username = username;
        this.password = password;
        this.Host = Host;
        this.Port = Port;
    }
    public java.sql.Connection ConnectionDb() {
        try {
            Class.forName("com.mysql.jbde.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Belum Terkoneksi Database");
        }
    return connection;
}

public java.sql.Connection ClosedDb() {
    try {
        connection.close();
    } catch (Exception e) {
    }
    return connection;
    }

public ResultSet eksekusiQuery(String sql) {
    ConnectionDb();
    ResultSet resultSet = null;
    //System.out.println(sql);
    try { 
        statement = connection.createStatement();
        resultSet = statement.executeQuery(SQL);
    } catch (SQLException ex) {
    }
    System.out.println(resultSet);
    return resultSet;
}

//overload fungsi untuk eksekusi query select semua kolom dengan where
    public ResultSet querySelectAll(String nameTable, String condition) {
        SQL= "SELECT * FROM " + nameTable + " WHERE " + condition;
        System.out.println(SQL);
        return this.eksekusiQuery(SQL);
    }
    
public void close() {
    throw new UnsupportedOperationException("Not Supported Yet.");//To change body of generated methods, choose Tools | Templates.
}

public Statement createStatement() {
    throw new UnsupportedOperationException ("Not Supported Yet.");//To change body of generated methods, choose Tools | Templates.
    }
}