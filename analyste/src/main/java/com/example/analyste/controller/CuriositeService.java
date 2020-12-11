package com.example.analyste.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CuriositeService {

    public abstract void createCuriosite(Curiosite curiosite);
    public abstract ArrayList<String> getInformations();
    public HashMap<String, Timestamp> getJoueur_timestamp(String joueur) ;
    public abstract void sendGlobalApi();

    public  abstract List<String> calcultime(Map<String, Timestamp> map );
}
