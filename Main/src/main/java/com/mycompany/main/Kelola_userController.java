/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import com.mycompany.main.User;
import helper.DBConnect;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author IMahardika
 */
public class Kelola_userController implements Initializable {

    @FXML private Button btn_logout;
    @FXML private Button btn_kembali;
    @FXML private TextField txt_id;
    @FXML private TextField txt_nama;
    @FXML private TextField txt_anak;
    @FXML private TextField txt_alamat;
    @FXML private TextField txt_telepon;
    @FXML private TextField txt_username;
    @FXML private TextField txt_password;
    @FXML private TableView<User> tb_user;
    @FXML private TableColumn<User, Integer> col_id;
    @FXML private TableColumn<User, String> col_nama;
    @FXML private TableColumn<User, String> col_anak;
    @FXML private TableColumn<User, String> col_alamat;
    @FXML private TableColumn<User, String> col_telepon;
    @FXML private TableColumn<User, String> col_username;
    @FXML private TableColumn<User, String> col_password;
    @FXML private Button btn_insert;
    @FXML private Button btn_update;
    @FXML private Button btn_delete;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showUser();
    } 
    
    //fungsi untuk mencari list user pada database
    public ObservableList<User> getUserList() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        Connection conn = DBConnect.ConnDB();
        String query = "SELECT * FROM user";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            User user;
            while(rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("nama_user"), rs.getString("nama_anak_user"), rs.getString("alamat"), rs.getString("username"), rs.getString("password"), rs.getString("no_telp"));
                userList.add(user);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return userList;    
    }
    
    //fungsi untuk menampilkan list user yang ada dalam database
    public void showUser() {
        ObservableList<User> list = getUserList();
        
        col_id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        col_nama.setCellValueFactory(new PropertyValueFactory<User, String>("nama_user"));
        col_anak.setCellValueFactory(new PropertyValueFactory<User, String>("nama_anak_user"));
        col_alamat.setCellValueFactory(new PropertyValueFactory<User, String>("alamat"));
        col_username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        col_telepon.setCellValueFactory(new PropertyValueFactory<User, String>("no_telp"));
        
        tb_user.setItems(list);
    }
    
    //fungsi supaya command query bisa di execute
    private void executeQuery(String query) {
        Connection conn = DBConnect.ConnDB();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    //fungsi untuk insert user baru
    private void insert() {
        String query = "INSERT INTO user VALUES (" + txt_id.getText() + ",'" + txt_nama.getText() + "','" + txt_anak.getText() + "','" 
                + txt_alamat.getText() + "','" + txt_username.getText() + "','" + txt_password.getText() + "','" + txt_telepon.getText() +"')"; 
        executeQuery(query);    
        showUser();
    }
    
    //fungsi untuk update user
    private void update() {
        String query = "UPDATE user SET nama_user = '" + txt_nama.getText() + "', nama_anak_user = '" + txt_anak.getText() + 
                "', alamat = '" + txt_alamat.getText() + "', username = '" + txt_username.getText() + "', password = '" + txt_password.getText() + 
                "', no_telp = '" + txt_telepon.getText() + "' WHERE id = " + txt_id.getText() + " ";
        executeQuery(query);
        showUser();
    }
    
    //fungsi untuk delete user
    private void delete() {
        String query = "DELETE FROM user WHERE id = " + txt_id.getText() + " ";
        executeQuery(query);
        showUser();
    }
    
    //untuk button insert, update, deletenya supaya bekerja
    //dengan library ActionEvent
    public void button_action(ActionEvent event) {
        if(event.getSource() == btn_insert) {
            insert();
        }
        else if(event.getSource() == btn_update) {
            update();
        }
        else if(event.getSource() == btn_delete) {
            delete();
        }
    }
    
    //untuk ketika admin klik salah satu data user dalam list maka data tersebut akan otomatis tertampil pada textfield :)
    //dengan library MouseEvent
    public void click_action(MouseEvent event) {
        User user = tb_user.getSelectionModel().getSelectedItem();  //
        
        //cetak di textfield
        //yang berisi tanda "" karena nilainya adalah integer
        txt_id.setText("" + user.getId());
        txt_nama.setText(user.getNama_user());
        txt_anak.setText(user.getNama_anak_user());
        txt_alamat.setText(user.getAlamat());
        txt_username.setText(user.getUsername());
        txt_password.setText(user.getPassword());
        txt_telepon.setText(user.getNo_telp());
    }
    
    //fungsi untuk logout
    public void logout() throws IOException, SQLException {
        try {
            Connection conn = DBConnect.CLoseDB();
            App.setRoot("admin_login");
        }
        catch(SQLException e) {
        }
    }
    
//    //fungsi untuk tombol kembali
//    public void kembali() throws IOException {
//        App.setRoot("app_admins");
//    }
    
}
