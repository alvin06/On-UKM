package com.example.ilhamk.adminonukm;

public class PengurusInformation {
    private String jabatan;
    private String id_ukm;
    private String id_user;
    private String email;
    private String nama;


    public PengurusInformation() {
    }

    public PengurusInformation(String jabatan, String id_ukm, String id_user, String email, String nama) {
        this.jabatan = jabatan;
        this.id_ukm = id_ukm;
        this.id_user = id_user;
        this.email = email;
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public String getId_ukm() {
        return id_ukm;
    }

    public String getId_user() {
        return id_user;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() { return nama; }
}
