/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import helper.DBConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author IMahardika
 */
public class AppController implements Initializable {


    @FXML
    private Button btn_logout;
    
    @FXML
    private Button btn_bayarSPP;
    
    @FXML
    private Button btn_history;
    
    @FXML
    private Label lbl_user;
 
    @FXML
    private Button btn_bayar;

    @FXML
    private Button btn_history_p;

    /**
     * Initializes the controller class.
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    
    //Untuk menghubungkan fungsi dengan DB
    Connection conn = DBConnect.ConnDB();
    
    @FXML
    public void logout() throws IOException, SQLException {
        try {
            // Connection conn = DBConnect.CLoseDB();
            App.setRoot("user_login");
        }
        
        catch(Exception e) {
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {  
            showGreetings();
        } catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
    
    
    //Fungsi untuk menampilkan nama pengguna di bagian pojok kanan atas aplikasi
    public void showGreetings() throws SQLException {
        UserLoginController sh = new UserLoginController();
        String ambilUser = sh.Username;
        String query = "SELECT nama_user FROM user WHERE username = '"+ ambilUser +"'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query); 
        try {
            while(rs.next()) {
                lbl_user.setText(rs.getString("nama_user"));
            }
        }
        catch(SQLException e) {
            e.getMessage();
        }
    }
    
    
    public void bayarSPP() throws IOException {
        App.setRoot("bayar_spp");
    }
    public void History() throws IOException {
        App.setRoot("history_pembayaran");
    }
    public void bayarHome() throws IOException {
        App.setRoot("bayar_spp");
    }
    public void historyHome() throws IOException {
        App.setRoot("history_pembayaran");
    }
    
}
