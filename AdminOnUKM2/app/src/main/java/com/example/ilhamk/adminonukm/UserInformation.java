package com.example.ilhamk.adminonukm;

public class UserInformation {
    private String id_user;
    private String nama;
    private String nim;
    private String jurusan;
    private String phone;
    private Integer angkatan;
    private String role;
    private String email;

    public UserInformation() {
    }

    public UserInformation(String id_user, String nama, String nim, String jurusan, String phone, Integer angkatan, String role, String email) {
        this.id_user = id_user;
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.phone = phone;
        this.angkatan = angkatan;
        this.role = role;
        this.email = email;
    }

    public String getId_user() {
        return id_user;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getAngkatan() {
        return angkatan;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

}
