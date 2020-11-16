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
    private static Connection conn = null;
    
    public static Connection ConnDB() {
        String db = "jdbc:sqlite:SPPOnline.sqlite";
        try {
//            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(db);
            System.err.println("Berhasil Terkoneksi");
            return conn;
        }
        catch (SQLException e) {
            return null;
        }
        
    }
    
    public static void close() {
        
    }
    
}
