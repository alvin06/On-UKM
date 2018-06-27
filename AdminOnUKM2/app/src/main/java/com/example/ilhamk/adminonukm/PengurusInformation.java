package com.example.ilhamk.adminonukm;

public class PengurusInformation {
    private String jabatan;
    private String id_ukm;
    private String id_user;
    private String email;
    private String nama;
    private String idUKM;


    public PengurusInformation() {
    }

    public PengurusInformation(String jabatan, String id_ukm, String id_user, String email, String nama, String idUKM) {
        this.jabatan = jabatan;
        this.id_ukm = id_ukm;
        this.id_user = id_user;
        this.email = email;
        this.nama = nama;
        this.idUKM = idUKM;
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

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public void setId_ukm(String id_ukm) {
        this.id_ukm = id_ukm;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdUKM() {
        return idUKM;
    }

    public void setIdUKM(String idUKM) {
        this.idUKM = idUKM;
    }
}
