package com.example.alvinafandi.on_ukm.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by rizky on 4/21/2018.
 */

public class Ukm implements Parcelable{
    private String idUkm;
    private String namaUkm;
    private int totalAnggota;
    private String idKetua;
    private String jadwalKegiatan;

    public Ukm() {
    }

    public Ukm(String idUkm, String namaUkm, int totalAnggota, String idKetua, String jadwalKegiatan) {
        this.idUkm = idUkm;
        this.namaUkm = namaUkm;
        this.totalAnggota = totalAnggota;
        this.idKetua = idKetua;
        this.jadwalKegiatan = jadwalKegiatan;
    }

    public String getIdUkm() {
        return idUkm;
    }

    public void setIdUkm(String idUkm) {
        this.idUkm = idUkm;
    }

    public String getNamaUkm() {
        return namaUkm;
    }

    public void setNamaUkm(String namaUkm) {
        this.namaUkm = namaUkm;
    }

    public int getTotalAnggota() {
        return totalAnggota;
    }

    public void setTotalAnggota(int totalAnggota) {
        this.totalAnggota = totalAnggota;
    }

    public String getIdKetua() {
        return idKetua;
    }

    public void setIdKetua(String idKetua) {
        this.idKetua = idKetua;
    }

    public String getJadwalKegiatan() {
        return jadwalKegiatan;
    }

    public void setJadwalKegiatan(String jadwalKegiatan) {
        this.jadwalKegiatan = jadwalKegiatan;
    }


    //ngebuat parcel
    public Ukm(Parcel in) {
        String[] data = new String[5]; //bikin array string sebanyak atributnya

        in.readStringArray(data);
        this.idUkm = data[0]; //masukin tiap atribut ke array
        this.namaUkm = data[1];
        this.totalAnggota = Integer.parseInt(data[2]);
        this.idKetua = data[3];
        this.jadwalKegiatan = data[4];
    }

    @Override
    public int describeContents() {
        return 0;
    } //kaga tau buat apaan wkwk

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.idUkm, this.namaUkm, String.valueOf(this.totalAnggota),
                this.idKetua, this.jadwalKegiatan});
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
