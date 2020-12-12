package com.example.analyste.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface CuriositeService {

    public abstract void createCuriosite(Curiosite curiosite);
    public abstract void createCreation(Creation creation);
    public abstract List<String> calcultime( );
    public abstract void sendGlobalApi(String joueur, String level);
    public abstract Map<String, Timestamp> getInformations();

}
