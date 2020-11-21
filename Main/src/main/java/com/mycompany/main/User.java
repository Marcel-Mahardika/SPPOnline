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
    private String nama_anak_user;
    private String alamat;
    private String no_telp;
    private String username;
    private String password;
    
    public User(int id, String nama_user, String nama_anak_user, String alamat, String username, String password, String no_telp) {
        this.id = id;
        this.nama_user = nama_user;
        this.nama_anak_user = nama_anak_user;
        this.alamat = alamat;
        this.username = username;
        this.password = password;
        this.no_telp = no_telp;
    }

    public int getId() {
        return id;
    }

    public String getNama_user() {
        return nama_user;
    }

    public String getNama_anak_user() {
        return nama_anak_user;
    }

    public String getAlamat() {
        return alamat;
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
    
}
