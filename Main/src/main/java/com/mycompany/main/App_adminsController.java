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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author IMahardika
 */

public class App_adminsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private Button btn_logout;
    @FXML private Label lbl_admin; 
    @FXML private Button menu_kelola_user;
    @FXML private Button menu_laporan;
    
    public void logout() throws IOException, SQLException {
        try {
            Connection conn = DBConnect.CLoseDB();
            App.setRoot("admin_login");
        }
        catch(SQLException e) { 
        }
    }
    
    //untuk pindah ke menu kelola user
    public void kelola_user() throws IOException {
        App.setRoot("kelola_user");
    }
    
    public void laporan() throws IOException {
        App.setRoot("laporan_pembayaran");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
