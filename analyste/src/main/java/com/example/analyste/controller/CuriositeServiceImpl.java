package com.example.analyste.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;;


import java.sql.Timestamp;
import java.util.*;

@Service
public class CuriositeServiceImpl implements CuriositeService {
    private static Map<String, Timestamp > map = new HashMap<>();

    private static Map<String, Integer > test_api ;
    static {
        test_api = new HashMap<>();
        test_api.put("med",2);
        test_api.put("didi",3);
        //test_api.put("sidi", 3);
    }


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

    public void remove(Creation creation){
        map.remove(creation.getJoueur());
    }

    @Override
    public Curiosite find(String joueur){

        System.out.println("find");

        Curiosite curio = new Curiosite();
        for (Map.Entry<String, Integer> entry : test_api.entrySet()) {
            if(entry.getKey().toString().equals(joueur)){
                curio.setJoueur(entry.getKey().toString());
                curio.setNbre_scan(entry.getValue());
                System.out.println("find j");

            }
            System.out.println(entry.getKey().toString() + ":" + entry.getValue());
        }
        return curio;
    }
    @Override
    public void sendCuriosite(Curiosite curiosite) {
        System.out.println("sendCuriosite");
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
        System.out.println("send to global api");
        template.postForLocation(url, map);


    }

    @Override
    public void AnalyseCuriosite() {
        RestTemplate template = new RestTemplate();
        System.out.println("analyse curiosite");
        String uri = "http://localhost:8080/api/";

        Creation p1= new Creation();
        Map<String, Timestamp > map1=map;

        for (Map.Entry<String, Timestamp> entry : map1.entrySet()) {
            Timestamp timestamp= (Timestamp)entry.getValue();
            Timestamp current = new Timestamp(System.currentTimeMillis());
            if ( current.getTime() - timestamp.getTime()  >= 10000 && entry!=null) {
                System.out.println(" ----" +entry.getKey().toString());
                Curiosite  curiosite = template.getForObject(uri.concat(entry.getKey().toString()), Curiosite.class);
                System.out.println("curiosite joeur");
                sendCuriosite(curiosite);
                timestamp= new Timestamp(System.currentTimeMillis());
                //map.put(pair.getKey().toString(),timestamp);

                p1.setJoueur(entry.getKey().toString());
                map.put(p1.joueur, timestamp);




            }

        }


        System.out.println(" -------------------");





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
