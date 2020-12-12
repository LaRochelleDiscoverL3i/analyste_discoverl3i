package com.example.analyste.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;;


import java.sql.Timestamp;
import java.util.*;


@Service
public class AnalysteServiceImpl implements AnalysteService {


    private static Map<String, Timestamp > map_curio = new HashMap<>();
    private static Map<String, Timestamp > map_prog = new HashMap<>();

    private static Map<String, Integer > test_api ;
    static {
        test_api = new HashMap<>();
        test_api.put("med",1);
        test_api.put("didi",3);
        test_api.put("sidi", 5);
    }


    private String level;

    private static Creation p= new Creation();

    @Override
    public void createCreation(Creation creation) {


        p.setJoueur(creation.getJoueur());
        System.out.println(creation.getJoueur());
        System.out.println(p.getJoueur());
        map_curio.put(p.getJoueur(),p.getTimestamp());
        map_prog.put(p.getJoueur(),p.getTimestamp());


    }


    @Override
    public Curiosite find_curiosite(String joueur){


        Curiosite curio = new Curiosite();
        for (Map.Entry<String, Integer> entry : test_api.entrySet()) {
            if(entry.getKey().toString().equals(joueur)){
                curio.setJoueur(entry.getKey().toString());
                curio.setNbre_scan(entry.getValue());

            }
        }
        return curio;
    }
    @Override
    public void sendCuriosite(Curiosite curiosite) {
        System.out.println("sendCuriosite");
        level =levelcuriosite(curiosite.getNbre_scan());
        String url = "http://localhost:8080/api_curio";
        sendGlobalApi(curiosite.getJoueur(), level, url);

    }

    @Override
    public void sendGlobalApi(String joueur, String level, String url) {
        RestTemplate template = new RestTemplate();
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
        String uri = "http://localhost:8080/api/curiosite/";


        Map<String, Timestamp > map1=map_curio;

        for (Map.Entry<String, Timestamp> entry : map1.entrySet()) {
            Timestamp timestamp= (Timestamp)entry.getValue();
            Timestamp current = new Timestamp(System.currentTimeMillis());
            if ( current.getTime() - timestamp.getTime()  >= 70000 && entry!=null) {
                System.out.println(" ----" +entry.getKey().toString());
                Curiosite  curiosite = template.getForObject(uri.concat(entry.getKey().toString()), Curiosite.class);
                sendCuriosite(curiosite);
                timestamp= new Timestamp(System.currentTimeMillis());
                //map.put(pair.getKey().toString(),timestamp);
                Creation p1= new Creation();

                p1.setJoueur(entry.getKey().toString());
                map_curio.put(p1.getJoueur(), timestamp);




            }

        }


        System.out.println(" -------------------");





    }

    @Override
    public Map<String, Timestamp> getMap_curio() {
        return map_curio;
    }
    @Override
    public Map<String, Timestamp> getMap_prog() {
        return map_prog;
    }



    @Override
    public void AnalyseProgression() {

        RestTemplate template = new RestTemplate();
        System.out.println("analyse progression");
        String uri = "http://localhost:8080/api/progression/";


        Map<String, Timestamp > mapPro=map_prog;

        for (Map.Entry<String, Timestamp> entry : mapPro.entrySet()) {
            Timestamp timestamp= (Timestamp)entry.getValue();
            Timestamp current = new Timestamp(System.currentTimeMillis());
            if ( current.getTime() - timestamp.getTime()  >= 90000 && entry!=null) {
                System.out.println(" ----" +entry.getKey().toString());
                Progression  progression = template.getForObject(uri.concat(entry.getKey().toString()), Progression.class);
                sendProgression(progression);
                timestamp= new Timestamp(System.currentTimeMillis());
                //map.put(pair.getKey().toString(),timestamp);
                Creation p1= new Creation();

                p1.setJoueur(entry.getKey().toString());
                map_prog.put(p1.getJoueur(), timestamp);




            }

        }

        System.out.println(" -------------------");

    }

    @Override
    public Progression find_progression(String joueur) {
        Progression progression = new Progression();
        for (Map.Entry<String, Integer> entry : test_api.entrySet()) {
            if(entry.getKey().toString().equals(joueur)){
                progression.setJoueur(entry.getKey().toString());
                progression.setNbre_reponse(entry.getValue());

            }
        }
        return progression;    }

    public void sendProgression(Progression progression) {

        System.out.println("sendProgression");
        level =levelprogression(progression.getNbre_reponse());
        String url = "http://localhost:8080/api_prog";
        sendGlobalApi(progression.getJoueur(), level, url);
    }

    private String levelprogression(int nbre_reponse) {
        if(nbre_reponse <= 1){
            return "bas";
        }
        else if (nbre_reponse<=2){
            return "normal";
        }
            else{
                return "fort";

        }
    }

    private String levelcuriosite(int nbre_scan) {
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
