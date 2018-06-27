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
    private String caption;
    private String posterUKM;
    private Boolean oprec;
    private int totalAnggota;

    public ukmTest() {
    }

    public ukmTest(String idUKM, String jadwalLatihan, String kategori, String logoUKM, String namaUKM, String pembina, String caption, String posterUKM, Boolean oprec, int totalAnggota) {
        this.idUKM = idUKM;
        this.jadwalLatihan = jadwalLatihan;
        this.kategori = kategori;
        this.logoUKM = logoUKM;
        this.namaUKM = namaUKM;
        this.pembina = pembina;
        this.caption = caption;
        this.posterUKM = posterUKM;
        this.oprec = oprec;
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

    public String getLogoUKM() {
        return logoUKM;
    }

    public void setLogoUKM(String logoUKM) {
        this.logoUKM = logoUKM;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPosterUKM() {
        return posterUKM;
    }

    public void setPosterUKM(String posterUKM) {
        this.posterUKM = posterUKM;
    }

    public Boolean getOprec() {
        return oprec;
    }

    public void setOprec(Boolean oprec) {
        this.oprec = oprec;
    }

    public int getTotalAnggota() {
        return totalAnggota;
    }

    public void setTotalAnggota(int totalAnggota) {
        this.totalAnggota = totalAnggota;
    }

    public static Creator<ukmTest> getCREATOR() {
        return CREATOR;
    }

    //ngebuat parcel
    public ukmTest(Parcel in) {
        String[] data = new String[10]; //bikin array string sebanyak atributnya

        in.readStringArray(data);
        this.idUKM = data[0]; //masukin tiap atribut ke array
        this.jadwalLatihan = data[1];
        this.kategori = data[2];
        this.logoUKM = data[3];
        this.namaUKM = data[4];
        this.pembina = data[5];
        this.caption = data[6];
        this.posterUKM = data[7];
        this.oprec = Boolean.parseBoolean(data[8]);
        this.totalAnggota = Integer.parseInt(data[9]);
    }

    @Override
    public int describeContents() {
        return 0;
    } //kaga tau buat apaan wkwk

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.idUKM, this.jadwalLatihan, this.kategori, this.logoUKM,
                this.namaUKM, this.pembina, this.caption, this.posterUKM, String.valueOf(this.oprec),String.valueOf(this.totalAnggota)});
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
