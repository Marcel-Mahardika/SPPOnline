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
    
    public static Connection ConnDB() {
        Connection conn = null;
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
}
