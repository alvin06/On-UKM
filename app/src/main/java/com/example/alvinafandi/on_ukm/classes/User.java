package com.example.alvinafandi.on_ukm.classes;

/**
 * Created by rizky on 4/21/2018.
 */

public class User {
    private String idUser;
    private String nama;
    private String nim;
    private String nomorHp;
    private int role;
    private int angkatan;

    public User() {
    }

    public User(String idUser, String nama, String nim, String nomorHp, int role, int angkatan) {
        this.idUser = idUser;
        this.nama = nama;
        this.nim = nim;
        this.nomorHp = nomorHp;
        this.role = role;
        this.angkatan = angkatan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(int angkatan) {
        this.angkatan = angkatan;
    }
}
