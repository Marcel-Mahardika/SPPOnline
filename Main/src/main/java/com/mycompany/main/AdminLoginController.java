package com.mycompany.main;

import helper.DBConnect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminLoginController {
    
    @FXML
    private Pane panel_login;
    
    @FXML
    private TextField txt_username;
    
    @FXML
    private Button btn_login;

    @FXML
    private Button btn_kembali;

    @FXML
    private PasswordField txt_password;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    
    public void login() throws SQLException, IOException{
        Connection conn = DBConnect.ConnDB();
   
        try {
            String Username = txt_username.getText();
            String Password = txt_password.getText();

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from admin where username = '"+Username+"' and password = '"+Password+"' ");
            
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            if(resultSet.next()) {
                App.setRoot("admin_dashboard");
                
//                AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("app.fxml"));
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.show();
            }
            
            else if(txt_username.getText().isEmpty() || txt_password.getText().isEmpty()) {
                message.setContentText("Maaf Username atau Password Tidak Boleh Kosong!");
                message.setTitle("Error!");
                message.show(); 
            }
           
            else {
                message.setContentText("Maaf Username atau Password Anda salah!");
                message.setTitle("Error!");
                message.show(); 
            }
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}