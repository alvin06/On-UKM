package com.example.alvinafandi.on_ukm.classes;

/**
 * Created by rizky on 4/21/2018.
 */

public class Pendaftaran {
    private String idUser;
    private String idUkm;

    public Pendaftaran() {
    }

    public Pendaftaran(String idUser, String idUkm) {
        this.idUser = idUser;
        this.idUkm = idUkm;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUkm() {
        return idUkm;
    }

    public void setIdUkm(String idUkm) {
        this.idUkm = idUkm;
    }
}
