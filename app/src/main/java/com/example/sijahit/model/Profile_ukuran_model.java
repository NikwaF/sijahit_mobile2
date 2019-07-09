package com.example.sijahit.model;

import java.util.ArrayList;

public class Profile_ukuran_model {

    private String id_profile_ukuran;
    private String nama_profile, panjang_dada,lingkar_dada,lebar_dada,panjang_lengan,lingkar_lengan,lingkar_pinggul,panjang_bahu,panjang_punggung;
    private String lingkar_pinggang,panjang_celana,lingkar_celana,lingkar_paha,jenis_kelamin;

    public Profile_ukuran_model(String id_profile_ukuran, String nama_profile, String panjang_dada, String lingkar_dada, String lebar_dada, String panjang_lengan, String lingkar_lengan, String lingkar_pinggul, String panjang_bahu, String panjang_punggung, String lingkar_pinggang, String panjang_celana, String lingkar_celana,String lingkar_paha,String jenis_kelamin) {
        this.id_profile_ukuran = id_profile_ukuran;
        this.nama_profile = nama_profile;
        this.panjang_dada = panjang_dada;
        this.lingkar_dada = lingkar_dada;
        this.lebar_dada = lebar_dada;
        this.panjang_lengan = panjang_lengan;
        this.lingkar_lengan = lingkar_lengan;
        this.lingkar_pinggul = lingkar_pinggul;
        this.panjang_bahu = panjang_bahu;
        this.panjang_punggung = panjang_punggung;
        this.lingkar_pinggang = lingkar_pinggang;
        this.panjang_celana = panjang_celana;
        this.lingkar_celana = lingkar_celana;
        this.lingkar_paha = lingkar_paha;
        this.jenis_kelamin= jenis_kelamin;
    }

    public String getId_profile_ukuran() {
        return id_profile_ukuran;
    }

    public String getNama_profile() {
        return nama_profile;
    }

    public String getPanjang_dada() {
        return panjang_dada;
    }

    public String getLingkar_dada() {
        return lingkar_dada;
    }

    public String getLebar_dada() {
        return lebar_dada;
    }

    public String getPanjang_lengan() {
        return panjang_lengan;
    }

    public String getLingkar_lengan() {
        return lingkar_lengan;
    }

    public String getLingkar_pinggul() {
        return lingkar_pinggul;
    }

    public String getPanjang_bahu() {
        return panjang_bahu;
    }

    public String getPanjang_punggung() {
        return panjang_punggung;
    }

    public String getLingkar_pinggang() {
        return lingkar_pinggang;
    }

    public String getPanjang_celana() {
        return panjang_celana;
    }

    public String getLingkar_celana() {
        return lingkar_celana;
    }

    public String getLingkar_paha() {
        return lingkar_paha;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }
}
