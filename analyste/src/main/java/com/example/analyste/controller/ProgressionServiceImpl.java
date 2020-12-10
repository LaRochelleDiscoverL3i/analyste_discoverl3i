package com.example.analyste.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ProgressionServiceImpl implements ProgressionService {
    private static Map<String, Integer> joueur_reponse = new HashMap<String, Integer>();

    private static Progression p= new Progression();

    @Override
    public void createProgression(Progression progression) {
        System.out.println("test");
        p.setJoueur(progression.getJoueur());
        p.setTimestamp(progression.getTimestamp());
    }

    @Override
    public ArrayList<String> getInformations() {
        return null;
    }

    @Override
    public void sendGlobalApi() {

    }

    @Override
    public HashMap<String, Timestamp> getJoueur_timestamp(String joueur) {
    return null;
    }


}
