package com.example.ilhamk.adminonukm;

public class UKMInformation {
    private String idUKM;
    private String jadwalLatihan;
    private String namaUKM;
    private int totalAnggota;
    private String pembina;
    private String kategori;

    public UKMInformation() {
    }

    public UKMInformation(String idUKM, String jadwalLatihan, String namaUKM, int totalAnggota, String pembina, String kategori) {
        this.idUKM = idUKM;
        this.jadwalLatihan = jadwalLatihan;
        this.namaUKM = namaUKM;
        this.totalAnggota = totalAnggota;
        this.pembina = pembina;
        this.kategori = kategori;
    }

    public String getIdUKM() {
        return idUKM;
    }

    public String getJadwalLatihan() {
        return jadwalLatihan;
    }

    public String getNamaUKM() {
        return namaUKM;
    }

    public Integer getTotalAnggota() {
        return totalAnggota;
    }

    public String getPembina() {
        return pembina;
    }

    public String getKategori() {
        return kategori;
    }

    public void setIdUKM(String idUKM) {
        this.idUKM = idUKM;
    }

    public void setJadwalLatihan(String jadwalLatihan) {
        this.jadwalLatihan = jadwalLatihan;
    }

    public void setNamaUKM(String namaUKM) {
        this.namaUKM = namaUKM;
    }

    public void setTotalAnggota(int totalAnggota) {
        this.totalAnggota = totalAnggota;
    }

    public void setPembina(String pembina) {
        this.pembina = pembina;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
