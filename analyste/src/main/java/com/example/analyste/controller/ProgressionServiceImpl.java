package com.example.analyste.controller;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ProgressionServiceImpl implements ProgressionService {
    private static Map<String, Timestamp > joueur_reponse = new HashMap<String, Timestamp>();

    private static Progression p= new Progression();

    @Override
    public void createProgression(Progression progression) {
        System.out.println("test");
        p.setJoueur(progression.getJoueur());
        p.setTimestamp(progression.getTimestamp());
        joueur_reponse.put(p.getJoueur(),p.getTimestamp());
    }

    @Override
    public ArrayList<String> getInformations() {
        return null;
    }

    @Override
    public void sendGlobalApi() {

    }

    @Override
    public List<String> calcultime(Map<String, Timestamp> map ) {
        Iterator it = map.entrySet().iterator();
        List<String> list= new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            Timestamp timestamp= (Timestamp)pair.getValue();
            Timestamp current = new Timestamp(System.currentTimeMillis());
            if ( current.getTime() - timestamp.getTime() == 1000 ) {
                list.add(pair.getKey().toString());
            }
            it.remove();
        }


        return list;
    }

    @Override
    public HashMap<String, Timestamp> getJoueur_timestamp(String joueur) {
    return null;
    }


}
