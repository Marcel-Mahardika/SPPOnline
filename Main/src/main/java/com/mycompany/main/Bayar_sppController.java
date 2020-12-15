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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author IMahardika
 */

public class Bayar_sppController implements Initializable {
    
    @FXML private Button btn_logout;
    @FXML private Button btn_bayar;
    @FXML private Button btn_home;
    @FXML private Button btn_cetak;
    @FXML private Button btn_history;
    
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
    @FXML private Label lbl_user;
    
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
    
    //Untuk menghubungkan fungsi dengan DB
    Connection conn = DBConnect.ConnDB();
    
    
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
        
        //untuk menyembunyikan button cetak
        btn_cetak.setDisable(true);
        
        try {
            showSiswa();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Bayar_sppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        showBayar();
        
        try {
            showGreetings();
        } catch (SQLException ex) {
            Logger.getLogger(Bayar_sppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    
    //menampilkan list siswa
     ObservableList<User> userList = FXCollections.observableArrayList();  
     public ObservableList<User> getUserList() {
        // Connection conn = DBConnect.ConnDB();
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
       
    
    //Fungsi untuk menampilkan siswa berdasarkan username yang diinputkan saat login
    public void showSiswa() throws SQLException {
        // Connection conn = DBConnect.ConnDB();
        UserLoginController sh = new UserLoginController();
        String ambilUser = sh.Username;
        String query = "SELECT * FROM user WHERE username = '"+ ambilUser +"'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        try {
            while(rs.next()) {
                txt_nis.setText("" + rs.getInt("nis"));
                txt_siswa.setText(rs.getString("nama_siswa"));
                txt_kelas.setText(rs.getString("kelas"));
                txt_pembayar.setText(rs.getString("nama_user"));
                lbl_tagihan.setText("300000");   //karena biaya SPP per bulannya Rp. 300000
            }
        }
        catch(SQLException e) {
            e.getMessage();
        }
        
        // mengatur bagian ttanggal supaya mengikuti tanggal yang ada di sistem pengguna
        // source format : https://docs.oracle.com/javase/10/docs/api/index.html?java/text/SimpleDateFormat.html
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        lbl_tanggal.setText(dtf.format(now));
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
    
    
    //menampilkan list pembayaran (tersembunyi)
    public ObservableList<Bayar> getBayarList() {
    ObservableList<Bayar> bayarList = FXCollections.observableArrayList();
        // Connection conn = DBConnect.ConnDB();
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
        boolean cekBulan = cmbx_bulan.getSelectionModel().isEmpty();
        boolean cekBank = cmbx_bank.getSelectionModel().isEmpty();
        //Bagian Error Handling
        Alert message = new Alert(AlertType.WARNING);
        if(txt_nominal.getText().isEmpty() && txt_rekening.getText().isEmpty() && cekBulan == true && cekBank == true) {
            message.setContentText("Maaf, Form pembayaran tidak boleh ada yang kosong!");
            message.setHeaderText("Kesalahan Input!");
            message.setTitle("Error!");
            message.show(); 
        }
        else if(txt_nominal.getText().isEmpty()) {
            message.setContentText("Maaf, Nominal tidak boleh kosong!");
            message.setHeaderText("Kesalahan Input!");
            message.setTitle("Error!");
            message.show(); 
        }
        else if(txt_rekening.getText().isEmpty()) {
            message.setContentText("Maaf, Nomor Rekening tidak boleh kosong!");
            message.setHeaderText("Kesalahan Input!");
            message.setTitle("Error!");
            message.show(); 
        }
        else if(cekBulan == true) {
            message.setContentText("Maaf, Bulan tidak boleh kosong!");
            message.setHeaderText("Kesalahan Input!");
            message.setTitle("Error!");
            message.show();
        }
        else if(cekBank == true) {
            message.setContentText("Maaf, Bank tidak boleh kosong!");
            message.setHeaderText("Kesalahan Input!");
            message.setTitle("Error!");
            message.show();
        }
        else if(!lbl_tagihan.getText().equals(txt_nominal.getText())) {
            message.setContentText("Maaf, jumlah nominal harus sama dengan jumlah tagihan!");
            message.setHeaderText("Kesalahan Input!");
            message.setTitle("Error!");
            message.show(); 
        }
        else {
            Alert message2 = new Alert(AlertType.CONFIRMATION);
            message2.setTitle("Confirmation");
            message2.setContentText("Apakah Anda sudah yakin dengan pembayaran Anda?");
            message2.setHeaderText("Konfirmasi Pembayaran");
            
            ButtonType typeOK = new ButtonType("Sudah");
            ButtonType typeCancel = new ButtonType("Belum");
            message2.getButtonTypes().setAll(typeOK, typeCancel);
            
            Optional<ButtonType> result = message2.showAndWait();
            if(result.get() == typeOK) {
                try {
                String query = "INSERT INTO pembayaran (nis,nama_anak,kelas,tanggal_bayar,tagihan,bulan,nominal,bank,no_rekening,"
                    + "nama_pembayar) VALUES (" + txt_nis.getText() + ",'" + txt_siswa.getText() +"','" + txt_kelas.getText()
                    + "','" + lbl_tanggal.getText() + "'," + lbl_tagihan.getText() + ",'" + cmbx_bulan.getValue()
                    + "'," + txt_nominal.getText() + ",'" + cmbx_bank.getValue() + "'," + txt_rekening.getText()
                    + ",'" + txt_pembayar.getText() + "')";
                executeQuery(query);  
                showBayar();
                }
                catch(Exception e) {
                    //kosong
                }
                //menampilkan kembali button cetak
                btn_cetak.setDisable(false);
            }
            else {
                // alert akan tertutup jika pengguna menekan button "belum"
            }
        }
    }
    
    
    // Fungsi untuk mencetak bukti dengan Jasper Report
    public void CetakBukti() throws SQLException, JRException {
        String cetak = "SELECT * FROM pembayaran WHERE nis = '"+ txt_nis.getText() +"' ORDER BY id_pembayaran desc limit 1";   //mengambil data secara descending
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(cetak);
            String report = ("D:\\My Documents\\Test RPL\\Main\\src\\main\\java\\com\\mycompany\\main\\Bukti_Pembayaran.jrxml");
            HashMap hash = new HashMap();
            hash.put("Kode", txt_nis.getText());
            JasperReport JRpt = JasperCompileManager.compileReport(report);
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, conn);
            JasperViewer.viewReport(JPrint, false);
        }                           
        catch(SQLException e) {
            e.getMessage();
        }
        //supaya tidak terjadi Database Lock
        finally {
            st.close();
            rs.close();
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
    public void logout() throws IOException {
        try {
            // Connection conn = DBConnect.CLoseDB();
            App.setRoot("user_login");
        }
        catch(Exception e) {
            //kosong
        }
    }
    
    // Fungsi untuk menu button homme
    @FXML
    public void Home() throws IOException {
        App.setRoot("user_dashboard");
    }
    
    // Fungsi untuk menu button history pembayaran
    @FXML
    public void History() throws IOException {
        App.setRoot("history_pembayaran");
    }
}
