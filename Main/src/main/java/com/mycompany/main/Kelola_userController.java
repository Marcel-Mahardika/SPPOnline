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
import javafx.scene.control.ComboBox;
/**
 * FXML Controller class
 *
 * @author IMahardika
 */
public class Kelola_userController implements Initializable {

    @FXML private Button btn_logout;
    @FXML private Button btn_kembali;
    
    //Textfield untuk user / orang tua
    @FXML private TextField txt_id;
    @FXML private TextField txt_nama;
    @FXML private TextField txt_username;
    @FXML private TextField txt_password;
    @FXML private TextField txt_telepon;
    
    //Textfield untuk anak user / pelajar / siswa
    @FXML private TextField txt_nis;
    @FXML private TextField txt_anak;
    @FXML private TextField txt_kelas;
    @FXML private TextField txt_usia;
    @FXML private ComboBox<String> cmbx_jenkel;
    @FXML private TextField txt_alamat;
    
    //Untuk tabel kolom
    @FXML private TableView<User> tb_user;
    @FXML private TableColumn<User, Integer> col_id;
    @FXML private TableColumn<User, String> col_nama;
    @FXML private TableColumn<User, String> col_username;
    @FXML private TableColumn<User, String> col_password;
    @FXML private TableColumn<User, String> col_telepon;
    @FXML private TableColumn<User, Integer> col_nis;
    @FXML private TableColumn<User, String> col_anak;
    @FXML private TableColumn<User, String> col_kelas;
    @FXML private TableColumn<User, Integer> col_usia;
    @FXML private TableColumn<User, String> col_jenkel;
    @FXML private TableColumn<User, String> col_alamat;
    
    //Untuk button CRUD User
    @FXML private Button btn_insert;
    @FXML private Button btn_update;
    @FXML private Button btn_delete;
    
    @FXML private Button menu_kelola_user;
    @FXML private Button menu_laporan;
    
    //List ComboBox untuk menampilkan jenis kelamin 
    ObservableList<String> jk_list = FXCollections.observableArrayList("Laki-Laki", "Perempuan");
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //memanggil list ComboBox
        cmbx_jenkel.setItems(jk_list);
        
        //Fungsi showUser dipanggil disini supaya nanti bisa terlihat di tabel
        showUser();
    } 
    
    //Fungsi untuk mencari list user pada database
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
                user = new User(rs.getInt("id"), rs.getString("nama_user"), rs.getString("username"), rs.getString("password"), rs.getString("no_telp"), rs.getInt("nis"), rs.getString("nama_siswa"), rs.getString("kelas"), rs.getInt("usia"), rs.getString("jenis_kelamin"), rs.getString("alamat"));
                userList.add(user);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return userList;    
    }
    
    //Fungsi untuk menampilkan list user yang ada dalam database
    public void showUser() {
        ObservableList<User> list = getUserList();
        
        col_id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        col_nama.setCellValueFactory(new PropertyValueFactory<User, String>("nama_user"));
        col_username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        col_telepon.setCellValueFactory(new PropertyValueFactory<User, String>("no_telp"));
        
        col_nis.setCellValueFactory(new PropertyValueFactory<User, Integer>("nis"));
        col_anak.setCellValueFactory(new PropertyValueFactory<User, String>("nama_siswa"));
        col_kelas.setCellValueFactory(new PropertyValueFactory<User, String>("kelas"));
        col_usia.setCellValueFactory(new PropertyValueFactory<User, Integer>("usia"));
        col_jenkel.setCellValueFactory(new PropertyValueFactory<User, String>("jenis_kelamin"));
        col_alamat.setCellValueFactory(new PropertyValueFactory<User, String>("alamat"));
        
        tb_user.setItems(list);
    }
    
    //Fungsi supaya command query untuk CRUD bisa di execute
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
    
    //Fungsi untuk insert user baru
    private void insert() {
        String query = "INSERT INTO user VALUES (" + txt_id.getText() + ",'" + txt_nama.getText() + "','" + txt_username.getText() + "','" + txt_password.getText() + "','" + txt_telepon.getText() + "'," + txt_nis.getText() + ",'" + txt_anak.getText() + "','" + txt_kelas.getText() + "'," + txt_usia.getText() + ",'" + cmbx_jenkel.getValue() + "','" + txt_alamat.getText() + "')"; 
        executeQuery(query);    
        showUser();
    }
    
    //Fungsi untuk update user
    private void update() {
        String query = "UPDATE user SET nama_user = '" + txt_nama.getText() + "', username = '" + txt_username.getText() + "', password = '" + txt_password.getText() + 
                "', no_telp = '" + txt_telepon.getText() + "', nis = " + txt_nis.getText() + ", nama_siswa = '" + txt_anak.getText() +"', kelas = '" + txt_kelas.getText() +"', usia = " + txt_usia.getText() +", jenis_kelamin = '" + cmbx_jenkel.getValue() +"', alamat = '" + txt_alamat.getText() + "' WHERE id = " + txt_id.getText() + " ";
        executeQuery(query);
        showUser();
    }
    
    //Fungsi untuk delete user
    private void delete() {
        String query = "DELETE FROM user WHERE id = " + txt_id.getText() + " ";
        executeQuery(query);
        showUser();
    }
    
    //untuk button insert, update, deletenya supaya bekerja
    //dengan library ActionEvent
    //kalau tanpa ActionEvent nanti buttonnya tidak berfungsi
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
        User user = tb_user.getSelectionModel().getSelectedItem();  
        
        
        //cetak di textfield
        //yang berisi tanda "" karena nilainya adalah integer
        txt_id.setText("" + user.getId());
        txt_nama.setText(user.getNama_user());
        txt_username.setText(user.getUsername());
        txt_password.setText(user.getPassword());
        txt_telepon.setText(user.getNo_telp());
        txt_nis.setText("" + user.getNis());
        txt_anak.setText(user.getNama_siswa());
        txt_kelas.setText(user.getKelas());
        txt_usia.setText("" + user.getUsia());
        cmbx_jenkel.setValue(col_jenkel.getCellData(user));
        txt_alamat.setText(user.getAlamat());
    }
    
    //Fungsi untuk logout
    public void logout() throws IOException, SQLException {
        try {
            Connection conn = DBConnect.CLoseDB();
            App.setRoot("admin_login");
        }
        catch(SQLException e) {
        }
    }
    
    public void laporan() throws IOException {
        App.setRoot("laporan_pembayaran");
    }
    
}
