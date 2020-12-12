package com.example.analyste.controller;

import java.sql.Timestamp;
import java.util.Map;

public interface AnalysteService {

    public abstract void sendCuriosite(Curiosite curiosite);
    public abstract Curiosite find_curiosite(String joueur);
    public abstract void createCreation(Creation creation);
    public abstract void AnalyseCuriosite();
    public abstract void sendGlobalApi(String joueur, String level, String url);
    public abstract Map<String, Timestamp> getMap_curio();


    public abstract void sendProgression(Progression progression);
    public abstract void AnalyseProgression ();
    public abstract Progression find_progression(String joueur);
    public abstract Map<String, Timestamp> getMap_prog();



}
