package com.example.alvinafandi.on_ukm.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by rizky on 4/21/2018.
 */

public class Ukm implements Parcelable{
    String idUKM;
    String jadwalLatihan;
    String kategori;
    String namaUKM;
    String pembina;
    int totalAnggota;

    public Ukm() {
    }

    public Ukm(String idUKM, String jadwalLatihan, String kategori, String namaUKM, String pembina, int totalAnggota) {
        this.idUKM = idUKM;
        this.jadwalLatihan = jadwalLatihan;
        this.kategori = kategori;
        this.namaUKM = namaUKM;
        this.pembina = pembina;
        this.totalAnggota = totalAnggota;
    }

    public String getIdUKM() {
        return idUKM;
    }

    public void setIdUKM(String idUKM) {
        this.idUKM = idUKM;
    }

    public String getJadwalLatihan() {
        return jadwalLatihan;
    }

    public void setJadwalLatihan(String jadwalLatihan) {
        this.jadwalLatihan = jadwalLatihan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNamaUKM() {
        return namaUKM;
    }

    public void setNamaUKM(String namaUKM) {
        this.namaUKM = namaUKM;
    }

    public String getPembina() {
        return pembina;
    }

    public void setPembina(String pembina) {
        this.pembina = pembina;
    }

    public int getTotalAnggota() {
        return totalAnggota;
    }

    public void setTotalAnggota(int totalAnggota) {
        this.totalAnggota = totalAnggota;
    }

    public static Creator<Ukm> getCREATOR() {
        return CREATOR;
    }

    //ngebuat parcel
    public Ukm(Parcel in) {
        String[] data = new String[6]; //bikin array string sebanyak atributnya

        in.readStringArray(data);
        this.idUKM = data[0]; //masukin tiap atribut ke array
        this.jadwalLatihan = data[1];
        this.kategori = data[2];
        this.namaUKM = data[3];
        this.pembina = data[4];
        this.totalAnggota = Integer.parseInt(data[5]);
    }

    @Override
    public int describeContents() {
        return 0;
    } //kaga tau buat apaan wkwk

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.idUKM, this.jadwalLatihan, this.kategori,
                this.namaUKM, this.pembina, String.valueOf(this.totalAnggota)});
    }

    public static final Parcelable.Creator<Ukm> CREATOR = new Parcelable.Creator<Ukm>() {

        @Override
        public Ukm createFromParcel(Parcel source) {
            return new Ukm(source);
        }

        @Override
        public Ukm[] newArray(int size) {
            return new Ukm[size];
        }
    };
}
