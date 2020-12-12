package com.example.analyste.controller;

public class Progression {


    private String joueur;
    private int nbre_reponse;

    public Progression() {}

    public Progression(String joueur, int nbre_reponse) {
        this.joueur = joueur;
        this.nbre_reponse = nbre_reponse;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public int getNbre_reponse() {
        return nbre_reponse;
    }

    public void setNbre_reponse(int nbre_reponse) {
        this.nbre_reponse = nbre_reponse;
    }
}
