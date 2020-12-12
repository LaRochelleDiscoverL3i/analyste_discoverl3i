package com.example.analyste.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface CuriositeService {

    public abstract void sendCuriosite(Curiosite curiosite);
    public abstract Curiosite find(String joueur);
    public abstract void createCreation(Creation creation);
    public abstract void AnalyseCuriosite( );
    public abstract void sendGlobalApi(String joueur, String level);
    public abstract Map<String, Timestamp> getInformations();

}
