package com.example.ilhamk.adminonukm;

import android.os.Parcel;
import android.os.Parcelable;

public class PengurusInformation implements Parcelable{
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

    public static Creator<PengurusInformation> getCREATOR() {
        return CREATOR;
    }

    //ngebuat parcel
    public PengurusInformation(Parcel in) {
        String[] data = new String[5]; //bikin array string sebanyak atributnya

        in.readStringArray(data);

        this.id_ukm = data[0];
        this.nama = data[1];
        this.id_user = data[2];
        this.email = data[3];
        this.jabatan = data[4];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.id_ukm, this.nama, this.id_user, this.email, this.jabatan});
    }

    public static final Parcelable.Creator<PengurusInformation> CREATOR = new Parcelable.Creator<PengurusInformation>() {

        @Override
        public PengurusInformation createFromParcel(Parcel source) {
            return new PengurusInformation(source);
        }

        @Override
        public PengurusInformation[] newArray(int size) {
            return new PengurusInformation[size];
        }
    };
}
