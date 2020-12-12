package com.example.analyste.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;;


import java.sql.Timestamp;
import java.util.*;

@Service
public class CuriositeServiceImpl implements CuriositeService {
    private static Map<String, Timestamp > map = new HashMap<>();
    private String level;

    private static Creation p= new Creation();

    @Override
    public void createCreation(Creation creation) {
        System.out.println("test");
        p.setJoueur(creation.getJoueur());
        System.out.println(creation.getJoueur());
        System.out.println(p.getJoueur());
        map.put(p.getJoueur(),p.getTimestamp());

    }
    @Override
    public void createCuriosite(Curiosite curiosite) {
        level =levelcuriosite(curiosite.getNbre_scan());
        sendGlobalApi(curiosite.getJoueur(), level);

    }

    @Override
    public void sendGlobalApi(String joueur, String level) {
        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8080/api";
        Map<String, String > map = new HashMap<>();
        map.put("joueur" , joueur);
        map.put("level" , level);

        template.postForLocation(url, map);


    }

    @Override
    public List<String> calcultime() {
        Iterator it = this.map.entrySet().iterator();
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
    public Map<String, Timestamp> getInformations() {
        return map;
    }

    public String levelcuriosite(int nbre_scan) {
        if (nbre_scan <= 1 ){
            return "faible";
        }
        else if (nbre_scan <=3) {
            return "normal";
        }
        else {
            return "eleve";
        }

    }

}
