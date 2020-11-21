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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author IMahardika
 */
public class Laporan_pembayaranController implements Initializable {


    @FXML
    private Button btn_logout;
    
    @FXML
    private Button menu_kelola_user;
    
    @FXML
    private Button menu_laporan;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void logout() throws IOException, SQLException {
        try {
            Connection conn = DBConnect.CLoseDB();
            App.setRoot("admin_login");
        }
        catch(SQLException e) {
        }
    }
    
    public void kelola_user() throws IOException {
        App.setRoot("kelola_user");
    }

}
