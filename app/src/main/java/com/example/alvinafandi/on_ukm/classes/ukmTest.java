package com.example.alvinafandi.on_ukm.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rizky on 6/21/2018.
 */

public class ukmTest implements Parcelable{
    private String idUKM;
    private String jadwalLatihan;
    private String kategori;
    private String logoUKM;
    private String namaUKM;
    private String pembina;
    private int totalAnggota;
//hqbxlcqebuye
    public ukmTest() {
    }

    public ukmTest(String idUkm, String jadwalLatihan, String kategori, String logoUkm, String namaUkm, String pembina, int totalAnggota) {
        this.idUKM = idUkm;
        this.jadwalLatihan = jadwalLatihan;
        this.kategori = kategori;
        this.logoUKM = logoUkm;
        this.namaUKM = namaUkm;
        this.pembina = pembina;
        this.totalAnggota = totalAnggota;
    }

    public String getIdUkm() {
        return idUKM;
    }

    public void setIdUkm(String idUkm) {
        this.idUKM = idUkm;
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

    public String getLogoUkm() {
        return logoUKM;
    }

    public void setLogoUkm(String logoUkm) {
        this.logoUKM = logoUkm;
    }

    public String getNamaUkm() {
        return namaUKM;
    }

    public void setNamaUkm(String namaUkm) {
        this.namaUKM = namaUkm;
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

    public static Parcelable.Creator<ukmTest> getCREATOR() {
        return CREATOR;
    }

    //ngebuat parcel
    public ukmTest(Parcel in) {
        String[] data = new String[7]; //bikin array string sebanyak atributnya

        in.readStringArray(data);
        this.idUKM = data[0]; //masukin tiap atribut ke array
        this.jadwalLatihan = data[1];
        this.kategori = data[2];
        this.logoUKM = data[3];
        this.namaUKM = data[4];
        this.pembina = data[5];
        this.totalAnggota = Integer.parseInt(data[6]);
    }

    @Override
    public int describeContents() {
        return 0;
    } //kaga tau buat apaan wkwk

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.idUKM, this.jadwalLatihan, this.kategori, this.logoUKM,
                this.namaUKM, this.pembina, String.valueOf(this.totalAnggota)});
    }

    public static final Parcelable.Creator<ukmTest> CREATOR = new Parcelable.Creator<ukmTest>() {

        @Override
        public ukmTest createFromParcel(Parcel source) {
            return new ukmTest(source);
        }

        @Override
        public ukmTest[] newArray(int size) {
            return new ukmTest[size];
        }
    };
}
