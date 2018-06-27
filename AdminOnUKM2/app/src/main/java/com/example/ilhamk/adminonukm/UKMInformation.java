package com.example.ilhamk.adminonukm;

public class UKMInformation {
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
}
