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
public class User {
    private int id;
    private String nama_user;
    private String username;
    private String password;
    private String no_telp;
    private int nis;
    private String nama_siswa;
    private String kelas;
    private int usia;
    private String jenis_kelamin;
    private String alamat;
    
    public User(int id, String nama_user, String username, String password, String no_telp, int nis, String nama_siswa, String kelas, int usia, String jenis_kelamin, String alamat) {
        this.id = id;
        this.nama_user = nama_user;
        this.username = username;
        this.password = password;
        this.no_telp = no_telp;
        this.nis = nis;
        this.nama_siswa = nama_siswa;
        this.kelas = kelas;
        this.usia = usia;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
    }
    
    public int getId() {
        return id;
    }

    public String getNama_user() {
        return nama_user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNo_telp() {
        return no_telp;
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

    public int getUsia() {
        return usia;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }
    
       
//    public User(int id, String nama_user, String nama_anak_user, String alamat, String username, String password, String no_telp) {
//        this.id = id;
//        this.nama_user = nama_user;
//        this.nama_anak_user = nama_anak_user;
//        this.alamat = alamat;
//        this.username = username;
//        this.password = password;
//        this.no_telp = no_telp;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getNama_user() {
//        return nama_user;
//    }
//
//    public String getNama_anak_user() {
//        return nama_anak_user;
//    }
//
//    public String getAlamat() {
//        return alamat;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//    
//    public String getNo_telp() {
//        return no_telp;
//    }
    
}
