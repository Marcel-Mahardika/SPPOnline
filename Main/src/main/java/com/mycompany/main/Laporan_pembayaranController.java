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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class Laporan_pembayaranController implements Initializable {


    @FXML private Button btn_logout;
    @FXML private Button menu_kelola_user;
    @FXML private Button menu_laporan;
    @FXML private Button btn_home;
            
    @FXML private ComboBox<String> cmbx_bulan;
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
    
    /**
     * Initializes the controller class.
     */
    
     //List ComboBox untuk menampilkan list bulan
    ObservableList<String> list_bulan = FXCollections.observableArrayList("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember");
    
    //Untuk menghubungkan fungsi dengan DB
    Connection conn = DBConnect.ConnDB();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbx_bulan.setItems(list_bulan); 
        showBayar();
    }  
    
    
    //menampilkan list pembayaran 
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
    
    
    //menampilkan pembayaran 
    public void showBayar() {
        ObservableList<Bayar> list = getBayarList();
        
        // col_id_pembayaran.setCellValueFactory(new PropertyValueFactory<Bayar, Integer>("id_pembayaran"));
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
   
    
    @FXML
    public void Cetak(ActionEvent event) throws JRException, SQLException {
        // Connection conn = DBConnect.ConnDB();
        String kueri = "SELECT * FROM pembayaran WHERE bulan = '"+ cmbx_bulan.getValue() +"' ";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(kueri);
            
            String reports = ("D:\\My Documents\\RPL Fixed\\SPPOnline\\Main\\src\\main\\java\\com\\mycompany\\main\\Laporan_Pembayaran.jrxml");
            HashMap hashs = new HashMap();
            hashs.put("Kodes", cmbx_bulan.getValue());
            JasperReport JRpts = JasperCompileManager.compileReport(reports);
            JasperPrint JPrints = JasperFillManager.fillReport(JRpts, hashs, conn);
            JasperViewer.viewReport(JPrints, false);
        }
        catch(SQLException | JRException e) {
            e.getMessage();
        } 
        //supaya tidak terjadi Database Lock
        finally {
            st.close();
            rs.close();
        }
    }
    
    
    @FXML
    public void logout() throws IOException {
        try {
            // Connection conn = DBConnect.CLoseDB();
            App.setRoot("admin_login");
        }
        catch(IOException e) {
        }
    }
    
    public void home() throws IOException {
        App.setRoot("admin_dashboard");
    }
    
    public void kelola_user() throws IOException {
        App.setRoot("kelola_user");
    }

}
