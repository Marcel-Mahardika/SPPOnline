/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import com.mycompany.main.Bayar;
import com.mycompany.main.UserLoginController;
import helper.DBConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author IMahardika
 */

public class Bayar_sppController implements Initializable {
    
    @FXML private Button btn_logout;
    @FXML private Button btn_bayar;
    
    @FXML private TextField txt_id;
    @FXML private TextField txt_pembayar;
    @FXML private TextField txt_search;
    @FXML private TextField txt_nis;
    @FXML private TextField txt_siswa;
    @FXML private TextField txt_kelas;
    @FXML private Label lbl_tanggal;
    @FXML private Label lbl_tagihan;
    @FXML private ComboBox<String> cmbx_bulan;
    @FXML private TextField txt_nominal;
    @FXML private ComboBox<String> cmbx_bank;
    @FXML private TextField txt_rekening;
    @FXML private Label txt_id_pembayaran;
    
    //bagian tabel bayar (tersembunyi)
    @FXML private TableView<Bayar> tb_bayar;
    @FXML private TableColumn<Bayar, Integer> col_id_pembayaran;
    @FXML private TableColumn<Bayar, Integer> col_nis;
    @FXML private TableColumn<Bayar, String> col_siswa;
    @FXML private TableColumn<Bayar, String> col_kelas;
    @FXML private TableColumn<Bayar, String> col_tgl_bayar;
    @FXML private TableColumn<Bayar, Integer> col_tagihan;
    @FXML private TableColumn<Bayar, String> col_bulan;
    @FXML private TableColumn<Bayar, Integer> col_nominal;
    @FXML private TableColumn<Bayar, String> col_bank;
    @FXML private TableColumn<Bayar, Long> col_rekening;
    @FXML private TableColumn<Bayar, String> col_nama_pembayar;
    
    //bagian tabel siswa
    @FXML private TableView<User> tb_siswa;
    @FXML private TableColumn<User, Integer> col_id;
    @FXML private TableColumn<User, Integer> col_nis_siswa;
    @FXML private TableColumn<User, String> col_nama_siswa;
    @FXML private TableColumn<User, String> col_kelas_siswa;
    @FXML private TableColumn<User, String> col_alamat;
    @FXML private TableColumn<User, String> col_jenkel;
    @FXML private TableColumn<User, String> col_ortu;
      
    //List ComboBox untuk menampilkan list bulan
    ObservableList<String> list_bulan = FXCollections.observableArrayList("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember");
    
    //List ComboBox untuk menampilka list bank
    ObservableList<String> list_bank = FXCollections.observableArrayList("BNI", "BCA", "BRI", "Mandiri", "Danamon", "Permata", "Lainnya");
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        
        cmbx_bulan.setItems(list_bulan); 
        cmbx_bank.setItems(list_bank);
        
        showUser();
        showBayar();
        
    }    
    
    //menampilkan list siswa
     ObservableList<User> userList = FXCollections.observableArrayList();  
     public ObservableList<User> getUserList() {
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
    
    //menampilkan siswa pada tabel
    public void showUser() {
        ObservableList<User> list = getUserList();
        
        col_id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        col_nis_siswa.setCellValueFactory(new PropertyValueFactory<User, Integer>("nis"));
        col_nama_siswa.setCellValueFactory(new PropertyValueFactory<User, String>("nama_siswa"));
        col_kelas_siswa.setCellValueFactory(new PropertyValueFactory<User, String>("kelas"));
        col_alamat.setCellValueFactory(new PropertyValueFactory<User, String>("alamat"));
        col_jenkel.setCellValueFactory(new PropertyValueFactory<User, String>("jenis_kelamin"));
        col_ortu.setCellValueFactory(new PropertyValueFactory<User, String>("nama_user"));
        
        tb_siswa.setItems(list);
        
        //Fungsi untuk mencari data siswa padad tabel siswa
        FilteredList<User> filteredData = new FilteredList<>(userList, s -> true);
            txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(siswa -> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if(String.valueOf(siswa.getNis()).indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else if(siswa.getNama_siswa().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                    
                });
            });
            
            SortedList<User> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tb_siswa.comparatorProperty());
            tb_siswa.setItems(sortedData);
    }  
    
    public void click_action(MouseEvent event) {
        User user = tb_siswa.getSelectionModel().getSelectedItem(); 
        
        //cetak di textfield
        //yang berisi tanda "" karena nilainya adalah integer
        txt_nis.setText("" + user.getNis());
        txt_siswa.setText(user.getNama_siswa());
        txt_kelas.setText(user.getKelas());
        txt_pembayar.setText(user.getNama_user());
        lbl_tagihan.setText("300000");   //karena biaya SPP per bulannya Rp. 300000
        txt_nominal.setText("");
        txt_rekening.setText("");
        txt_search.setText("");
        
        //mengatur bagian ttanggal supaya mengikuti tanggal yang ada di sistem pengguna
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        lbl_tanggal.setText(dtf.format(now));
       
    }
    
    
    //menampilkan list pembayaran (tersembunyi)
    public ObservableList<Bayar> getBayarList() {
    ObservableList<Bayar> bayarList = FXCollections.observableArrayList();
        Connection conn = DBConnect.ConnDB();
        String query = "SELECT * FROM pembayaran";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Bayar bayar;
            while(rs.next()) {
                bayar = new Bayar(rs.getInt("id_pembayaran"), rs.getInt("nis"), rs.getString("nama_anak"), rs.getString("kelas"), rs.getString("tanggal_bayar"), rs.getInt("tagihan"), rs.getString("bulan"), rs.getInt("nominal"), rs.getString("bank"), rs.getLong("no_rekening"), rs.getString("nama_pembayar"));
                bayarList.add(bayar);
            } 
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return bayarList;
    }
    
    //menampilkan pembayaran (tersembunyi)
    public void showBayar() {
        ObservableList<Bayar> list = getBayarList();
        
        col_id_pembayaran.setCellValueFactory(new PropertyValueFactory<Bayar, Integer>("id_pembayaran"));
        col_nis.setCellValueFactory(new PropertyValueFactory<Bayar, Integer>("nis"));
        col_siswa.setCellValueFactory(new PropertyValueFactory<Bayar, String>("nama_anak"));
        col_kelas.setCellValueFactory(new PropertyValueFactory<Bayar, String>("kelas"));
        col_tgl_bayar.setCellValueFactory(new PropertyValueFactory<Bayar, String>("tanggal_bayar"));
        col_tagihan.setCellValueFactory(new PropertyValueFactory<Bayar, Integer>("tagihan"));
        col_bulan.setCellValueFactory(new PropertyValueFactory<Bayar, String>("bulan"));
        col_nominal.setCellValueFactory(new PropertyValueFactory<Bayar, Integer>("nominal"));
        col_bank.setCellValueFactory(new PropertyValueFactory<Bayar, String>("bank"));
        col_rekening.setCellValueFactory(new PropertyValueFactory<Bayar, Long>("rekening"));
        col_nama_pembayar.setCellValueFactory(new PropertyValueFactory<Bayar, String>("nama_pembayar"));
        
        tb_bayar.setItems(list);
    }  
    
    //Supaya fungsi bayar bisa berfungsi
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
     
    //Fungsi untuk insert pembayaran baru ke database
    private void bayar() throws IOException {
        
        //Bagian Error Handling
        Alert message = new Alert(AlertType.WARNING);
        if(txt_nis.getText().isEmpty() || txt_siswa.getText().isEmpty() || txt_kelas.getText().isEmpty() || cmbx_bulan.getValue().isEmpty() || txt_nominal.getText().isEmpty() || cmbx_bank.getValue().isEmpty() || txt_rekening.getText().isEmpty() || txt_pembayar.getText().isEmpty()) {
            message.setContentText("Maaf, Isian Tidak Boleh Ada yang Kosong!");
            message.setTitle("Error!");
            message.show(); 
        }
        else if(!lbl_tagihan.getText().equals(txt_nominal.getText())) {
            message.setContentText("Maaf, Jumlah Nominal Harus Sama Dengan Jumlah Tagihan!");
            message.setTitle("Error!");
            message.show(); 
        }
        else {
            String query = "INSERT INTO pembayaran (nis,nama_anak,kelas,tanggal_bayar,tagihan,bulan,nominal,bank,no_rekening,nama_pembayar) VALUES (" + txt_nis.getText() + ",'" + txt_siswa.getText() +"','" + txt_kelas.getText() + "','" + lbl_tanggal.getText() + "'," + lbl_tagihan.getText() + ",'" + cmbx_bulan.getValue() + "'," + txt_nominal.getText() + ",'" + cmbx_bank.getValue() + "'," + txt_rekening.getText() + ",'" + txt_pembayar.getText() + "')";
            executeQuery(query);  
            showBayar();
        }
    }
    
    //untuk button bayarnya supaya bekerja
    //dengan library ActionEvent
    //kalau tanpa ActionEvent nanti buttonnya tidak berfungsi
    public void button_action(ActionEvent event) throws IOException {
        if(event.getSource() == btn_bayar) {
            bayar();
        }
    }
    
    //Fungsi untuk button logout
    @FXML
    public void logout() throws IOException, SQLException {
        try {
            Connection conn = DBConnect.CLoseDB();
            App.setRoot("user_login");
        }
        catch(SQLException e) {
            //kosong
        }
    }

}
