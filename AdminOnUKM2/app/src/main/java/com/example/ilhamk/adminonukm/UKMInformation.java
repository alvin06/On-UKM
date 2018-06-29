package com.example.ilhamk.adminonukm;

import android.os.Parcel;
import android.os.Parcelable;

public class UKMInformation implements Parcelable {
    private String idUKM;
    private String jadwalLatihan;
    private String namaUKM;
    private int totalAnggota;
    private String pembina;
    private String kategori;
    private String logoUKM;
    private String posterUKM;
    private String caption;
    private Boolean oprec;

    public UKMInformation() {
    }

    public UKMInformation(String idUKM, String jadwalLatihan, String namaUKM, int totalAnggota, String pembina,
                          String kategori, String logoUKM, String posterUKM, String caption, Boolean oprec) {
        this.idUKM = idUKM;
        this.jadwalLatihan = jadwalLatihan;
        this.namaUKM = namaUKM;
        this.totalAnggota = totalAnggota;
        this.pembina = pembina;
        this.kategori = kategori;
        this.logoUKM = logoUKM;
        this.posterUKM = posterUKM;
        this.caption = caption;
        this.oprec = oprec;
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

    public String getNamaUKM() {
        return namaUKM;
    }

    public void setNamaUKM(String namaUKM) {
        this.namaUKM = namaUKM;
    }

    public int getTotalAnggota() {
        return totalAnggota;
    }

    public void setTotalAnggota(int totalAnggota) {
        this.totalAnggota = totalAnggota;
    }

    public String getPembina() {
        return pembina;
    }

    public void setPembina(String pembina) {
        this.pembina = pembina;
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

    public String getPosterUKM() {
        return posterUKM;
    }

    public void setPosterUKM(String posterUKM) {
        this.posterUKM = posterUKM;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Boolean getOprec() {
        return oprec;
    }

    public void setOprec(Boolean oprec) {
        this.oprec = oprec;
    }

    public void updateData(String namaUKM, String jadwalLatihan, String pembina, String kategori){
        this.namaUKM = namaUKM;
        this.jadwalLatihan = jadwalLatihan;
        this.pembina = pembina;
        this.kategori = kategori;
    }

    public static Creator<UKMInformation> getCREATOR() {
        return CREATOR;
    }

    //ngebuat parcel
    public UKMInformation(Parcel in) {
        String[] data = new String[10]; //bikin array string sebanyak atributnya

        in.readStringArray(data);

        this.idUKM = data[0];
        this.jadwalLatihan = data[1];
        this.namaUKM = data[2];
        this.totalAnggota = Integer.parseInt(data[3]);
        this.pembina = data[4];
        this.kategori = data[5];
        this.logoUKM = data[6];
        this.posterUKM = data[7];
        this.caption = data[8];
        this.oprec = Boolean.parseBoolean(data[9]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.idUKM, this.jadwalLatihan, this.namaUKM, String.valueOf(this.totalAnggota),
                this.pembina, this.kategori, this.logoUKM,  this.posterUKM, this.caption, String.valueOf(this.oprec)});
    }

    public static final Parcelable.Creator<UKMInformation> CREATOR = new Parcelable.Creator<UKMInformation>() {

        @Override
        public UKMInformation createFromParcel(Parcel source) {
            return new UKMInformation(source);
        }

        @Override
        public UKMInformation[] newArray(int size) {
            return new UKMInformation[size];
        }
    };
}
