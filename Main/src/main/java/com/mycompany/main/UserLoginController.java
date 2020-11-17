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
import javafx.stage.Stage;

public class UserLoginController {

    @FXML
    private Pane panel_login;

    @FXML
    private TextField txt_username;

    @FXML
    private Button btn_login;

    @FXML
    private PasswordField txt_password;  

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    private void login() throws SQLException, IOException{
        String Username = txt_username.getText();
        String Password = txt_password.getText();
        
        Connection conn = DBConnect.ConnDB();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user where username = '"+Username+"' and password = '"+Password+"' ");
        
//        if(resultSet.next()) {
//            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/com/mycompany/main/app.fxml"));
//            Node node = (Node) event.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();
//            stage.setScene(new Scene(root));
//        }
        
    }
    
    
    
}