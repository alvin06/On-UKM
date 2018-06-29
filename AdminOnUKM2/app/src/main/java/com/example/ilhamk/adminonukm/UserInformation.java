package com.example.ilhamk.adminonukm;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInformation implements Parcelable{
    private String id_user;
    private String nama;
    private String nim;
    private String jurusan;
    private String phone;
    private Integer angkatan;
    private String role;
    private String email;
    private String idUKM;
    private String ktmUrl;

    public UserInformation(String id_user, String nama, String nim, String jurusan, String phone, Integer angkatan, String role, String email, String idUKM, String ktmUrl) {
        this.id_user = id_user;
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.phone = phone;
        this.angkatan = angkatan;
        this.role = role;
        this.email = email;
        this.idUKM = idUKM;
        this.ktmUrl = ktmUrl;
    }

    public UserInformation() {
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
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

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(Integer angkatan) {
        this.angkatan = angkatan;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdUKM() {
        return idUKM;
    }

    public void setIdUKM(String idUKM) {
        this.idUKM = idUKM;
    }

    public String getKtmUrl() {
        return ktmUrl;
    }

    public void setKtmUrl(String ktmUrl) {
        this.ktmUrl = ktmUrl;
    }

    public static Creator<UserInformation> getCREATOR() {
        return CREATOR;
    }

    public void updateUser(String nama, String nim, String jurusan, int angkatan, String phone, String role, String idUKM){
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.angkatan = angkatan;
        this.phone = phone;
        this.role = role;
        this.idUKM = idUKM;
    }

    //ngebuat parcel
    public UserInformation(Parcel in) {
        String[] data = new String[10]; //bikin array string sebanyak atributnya

        in.readStringArray(data);

        this.id_user = data[0];
        this.nama = data[1];
        this.nim = data[2];
        this.jurusan = data[3];
        this.phone = data[4];
        this.angkatan = Integer.parseInt(data[5]);
        this.role= data[6];
        this.email= data[7];
        this.idUKM = data[8];
        this.ktmUrl = data[9];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.id_user, this.nama, this.nim, this.jurusan,
                this.phone, String.valueOf(angkatan), this.role,  this.email, this.idUKM, this.ktmUrl});
    }

    public static final Parcelable.Creator<UserInformation> CREATOR = new Parcelable.Creator<UserInformation>() {

        @Override
        public UserInformation createFromParcel(Parcel source) {
            return new UserInformation(source);
        }

        @Override
        public UserInformation[] newArray(int size) {
            return new UserInformation[size];
        }
    };
}
