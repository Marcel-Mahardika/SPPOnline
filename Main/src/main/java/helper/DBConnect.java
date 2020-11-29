 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author IMahardika
 */
public class DBConnect {
    static Connection conn = null;
    public static Connection ConnDB() {
        
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:SPPOnline.db");
            System.out.println("Berhasil Terkoneksi");
            
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } 
        return conn;
    }  
    
    public static Connection CLoseDB() throws SQLException {
        try {
            conn.close();
            System.out.println("Database Berhasil Terputus");
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
