package com.mycompany.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import helper.DBConnect;    //import package DBConnect

import java.io.IOException;

/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("primary"), 640, 480);
//        scene = new Scene(loadFXML("bayar_spp"), 1115, 600);
//        scene = new Scene(loadFXML("user_login"), 1115, 755);
        scene = new Scene(loadFXML("primary"), 1115, 625);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("SPP-Online");
    }
    
//    @Override
//    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/com/mycompany/main/user_login.fxml"));
//        Scenescene = new Scene(root);
//        stage.scene = scene;
//        stage.show();
//    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(); 
        DBConnect.ConnDB();
    }

}