package com.example.analyste.controller;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;




public interface ProgressionService {
    public abstract void createProgression(Progression progression);
    public abstract ArrayList<String> getInformations();
    public HashMap<String, Timestamp> getJoueur_timestamp(String joueur) ;
    public abstract void sendGlobalApi();


}
