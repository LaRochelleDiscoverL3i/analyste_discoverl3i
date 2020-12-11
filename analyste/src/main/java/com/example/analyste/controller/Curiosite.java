package com.example.analyste.controller;

import java.sql.Timestamp;

public class Curiosite {

    String joueur;
    Timestamp timestamp;

    public Curiosite(){};
    public Curiosite(String joueur, Timestamp timestamp){
        this.joueur=joueur;
        this.timestamp=timestamp;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


}
