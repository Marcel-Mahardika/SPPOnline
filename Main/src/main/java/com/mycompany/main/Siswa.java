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
public class Siswa {
  
    private int id;
    private int nis;
    private String nama_siswa;
    private String kelas;
    private String alamat;
    private String jenis_kelamin;
    private String orang_tua;
    
    public Siswa(int id, int nis, String nama_siswa, String kelas, String alamat, String jenis_kelamin, String orang_tua) {
        this.id = id;
        this.nis = nis;
        this.nama_siswa = nama_siswa;
        this.kelas = kelas;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.orang_tua = orang_tua;
    }
    
    public int getId() {
        return id;
    }

    public int getNis() {
        return nis;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public String getKelas() {
        return kelas;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getOrang_tua() {
        return orang_tua;
    }
    
}
