package com.mycompany.main;


import helper.DBConnect;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserLoginController {

    @FXML private Pane panel_login;
    @FXML private TextField txt_username;
    @FXML private Button btn_login;
    @FXML private Button btn_kembali;
    @FXML private PasswordField txt_password;  

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
//    public void login() throws IOException {
//        String Username = txt_username.getText();
//        String Password = txt_password.getText();
//        
//        Alert message = new Alert(AlertType.INFORMATION);
//        if(Username.equals("nurhadi") && Password.equals("nurhadi123")) {
////            message.setContentText("Selamat Datang " + Username);
////            message.setTitle("Selamat Datang");
////            message.show();
//            
//            //untuk menyembunyikan login interface
//            btn_login.getScene().getWindow().hide();
//            
//            //untuk menampilkan main app interface
//            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("app.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setTitle("Selamat Datang");
//            stage.show();
//            
//        }
//        else {
//            message.setContentText("Invalid Login");
//            message.setTitle("Login Gagal");
//            message.show();   
//        }
        
//        txt_password.setText("");
//        txt_username.setText("");
        
    
    static String Username;
    private static String Password;
    
    public void login() throws SQLException, IOException {
        Connection conn = DBConnect.ConnDB();
        try {
            Username = txt_username.getText();
            Password = txt_password.getText();

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user where username = '"+Username+"' and password = '"+Password+"' ");
            
            Alert message = new Alert(AlertType.INFORMATION);
            if(resultSet.next()) {
                App.setRoot("user_dashboard");
            }
            
            else if(txt_username.getText().isEmpty() || txt_password.getText().isEmpty()) {
                message.setContentText("Maaf Username atau Password Tidak Boleh Kosong!");
                message.setTitle("Error!");
                message.show(); 
            }
            
            else {
                message.setContentText("Maaf Username atau Password Anda Salah!");
                message.setTitle("Error!");
                message.show(); 
            }
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    
    
    
}