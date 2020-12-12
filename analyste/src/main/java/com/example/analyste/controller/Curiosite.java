package com.example.analyste.controller;

import java.sql.Timestamp;

public class Curiosite {

    private String joueur;
    private int nbre_scan;

    public Curiosite(){};
    public Curiosite(String joueur, int nbre_scan){
        this.joueur=joueur;
        this.nbre_scan=nbre_scan;
    }

    public int getNbre_scan() {
        return nbre_scan;
    }

    public void setNbre_scan(int nbre_scan) {
        this.nbre_scan = nbre_scan;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }




}
