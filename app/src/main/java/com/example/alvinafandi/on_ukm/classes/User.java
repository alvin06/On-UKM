package com.example.alvinafandi.on_ukm.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rizky on 4/21/2018.
 */

public class User implements Parcelable {
    private String id_user;
    private String nama;
    private String nim;
    private String phone;
    private String email;
    private String jurusan;
    private String role;
    private int angkatan;
    private String idUKM;

    public User() {
    }

    public User(String id_user, String nama, String nim, String phone, String email, String jurusan, String role, int angkatan, String idUKM) {
        this.id_user = id_user;
        this.nama = nama;
        this.nim = nim;
        this.phone = phone;
        this.email = email;
        this.jurusan = jurusan;
        this.role = role;
        this.angkatan = angkatan;
        this.idUKM = idUKM;
    }

    public String getid_user() {
        return id_user;
    }

    public void setid_user(String id_user) {
        this.id_user = id_user;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(int angkatan) {
        this.angkatan = angkatan;
    }

    public String getIdUKM() {
        return idUKM;
    }

    public void setIdUKM(String idUKM) {
        this.idUKM = idUKM;
    }

    public void updateUser(String nama, String nim, String jurusan, String telp, int angkatan){
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.phone = telp;
        this.angkatan = angkatan;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public User(Parcel in) {
        String[] data = new String[9]; //bikin array string sebanyak atributnya

        in.readStringArray(data);
        this.id_user = data[0];
        this.nama = data[1];
        this.nim = data[2];
        this.phone = data[3];
        this.role = data[4];
        this.angkatan = Integer.parseInt(data[5]);
        this.idUKM = data[6];
        this.jurusan = data[7];
        this.email = data[8];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.id_user, this.nama, this.nim, this.phone,
                this.role, String.valueOf(this.angkatan), this.idUKM, this.jurusan, this.email});
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
