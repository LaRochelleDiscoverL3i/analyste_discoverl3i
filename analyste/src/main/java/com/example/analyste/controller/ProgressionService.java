package com.example.analyste.controller;
import java.sql.Timestamp;
import java.util.*;


public interface ProgressionService {
    public abstract void createProgression(Progression progression);
    public abstract ArrayList<String> getInformations();
    public HashMap<String, Timestamp> getJoueur_timestamp(String joueur) ;
    public abstract void sendGlobalApi();

    public  abstract List<String> calcultime(Map<String, Timestamp> map );


}
