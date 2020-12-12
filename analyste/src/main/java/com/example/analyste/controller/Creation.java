package com.example.analyste.controller;

import java.sql.Timestamp;

public class Creation {

    private String joueur;
    private Timestamp timestamp;

    public Creation(){
        this.timestamp=new Timestamp(System.currentTimeMillis());
    }

    public Creation(String joueur){
        this.joueur=joueur;
        this.timestamp= new Timestamp(System.currentTimeMillis());
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
