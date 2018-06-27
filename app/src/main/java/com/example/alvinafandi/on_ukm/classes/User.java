package com.example.alvinafandi.on_ukm.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rizky on 4/21/2018.
 */

public class User implements Parcelable {
    private String idUser;
    private String nama;
    private String nim;
    private String nomorHp;
    private String role;
    private int angkatan;

    public User() {
    }

    public User(String idUser, String nama, String nim, String nomorHp, String role, int angkatan) {
        this.idUser = idUser;
        this.nama = nama;
        this.nim = nim;
        this.nomorHp = nomorHp;
        this.role = role;
        this.angkatan = angkatan;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
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

    public User(Parcel in) {
        String[] data = new String[6]; //bikin array string sebanyak atributnya

        in.readStringArray(data);
        this.idUser = data[0];
        this.nama = data[1];
        this.nim = data[2];
        this.nomorHp = data[3];
        this.role = data[4];
        this.angkatan = Integer.parseInt(data[5]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.idUser, this.nama, this.nim, this.nomorHp,
                this.role, String.valueOf(this.angkatan)});
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
