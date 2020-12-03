/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

/**
 *
 * @author IMahardika
 */
public class Bayar {
    private int id_pembayaran;
    private int nis;
    private String nama_anak;
    private String kelas;
    private String tanggal_bayar;
    private int tagihan;
    private String bulan;
    private int nominal;
    private String bank;
    private long rekening;
    private String nama_pembayar;
    
    public Bayar(int id_pembayaran, int nis, String nama_anak, String kelas, String tanggal_bayar, int tagihan, String bulan, int nominal, String bank, long rekening, String nama_pembayar) {
        this.id_pembayaran = id_pembayaran;
        this.nis = nis;
        this.nama_anak = nama_anak;
        this.kelas = kelas;
        this.tanggal_bayar = tanggal_bayar;
        this.tagihan = tagihan;
        this.bulan = bulan;
        this.nominal = nominal;
        this.bank = bank;
        this.rekening = rekening;
        this.nama_pembayar = nama_pembayar;
    }
    
    public int getId_pembayaran() {
        return id_pembayaran;
    }
    
    public int getNis() {
        return nis;
    }

    public String getNama_anak() {
        return nama_anak;
    }

    public String getKelas() {
        return kelas;
    }

    public String getTanggal_bayar() {
        return tanggal_bayar;
    }

    public int getTagihan() {
        return tagihan;
    }

    public String getBulan() {
        return bulan;
    }

    public int getNominal() {
        return nominal;
    }
    
    public String getBank() {
        return bank;
    }

    public long getRekening() {
        return rekening;
    }

    public String getNama_pembayar() {
        return nama_pembayar;
    }
    
    
}
