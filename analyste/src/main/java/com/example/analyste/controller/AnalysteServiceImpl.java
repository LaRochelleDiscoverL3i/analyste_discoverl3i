package com.example.analyste.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;;


import java.sql.Timestamp;
import java.util.*;


@Service
public class AnalysteServiceImpl implements AnalysteService {

    // port de connexion
    private String port= "8083";
    //map contenant l'idjoueur et le timestamp utilisé pour calculer les levels de curiosité
    private static Map<String, Timestamp > map_curio = new HashMap<>();
    //map contenant l'idjoueur et le timestamp utilisé pour calculer les levels de progression
    private static Map<String, Timestamp > map_prog = new HashMap<>();


    //cette liste permet de simuler la globalapiServer
    private static Map<String, Integer > test_api ;
    static {
        test_api = new HashMap<>();
        test_api.put("med",1);
        test_api.put("didi",3);
        test_api.put("sidi", 5);
    }


    private String level;

    //creation d'un objet creation
    private static Creation p= new Creation();

    /*fonction qui permet de creer un objet creation, au moment creer le joueur dans la bdd,


     */
    @Override
    public void createCreation(Creation creation) {

        p.setJoueur(creation.getJoueur());
        System.out.println(creation.getJoueur());
        System.out.println(p.getJoueur());
        //ajout dans la map curiosité
        map_curio.put(p.getJoueur(),p.getTimestamp());
        //ajout dans la map progression
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
        String url = "http://localhost:".concat(port).concat("/api_curio");
        sendGlobalApi(curiosite.getJoueur(), level, url);

    }

    /*
    * fonction qui permet d'envoyer les informations sur la globalapi json (joueur, level)
    */
    @Override
    public void sendGlobalApi(String joueur, String level, String url) {
        RestTemplate template = new RestTemplate();
        Map<String, String > map = new HashMap<>();
        map.put("joueur" , joueur);
        map.put("level" , level);
        System.out.println("send to global api");
        template.postForLocation(url, map);


    }

    /*
    Cette fonction permet d'analyse la curiosité
    cette derniere parcoure la map_curio si le timestamp >=5 min, elle envoie sur la globalApiServer
    pour lui demander nbre_scan relié au joueur
     */
    @Override
    public void AnalyseCuriosite() {
        RestTemplate template = new RestTemplate();
        System.out.println("analyse curiosite");
        String uri = "http://localhost:".concat(port).concat("/api/analyste/curiosite/");


        Map<String, Timestamp > map1=map_curio;

        for (Map.Entry<String, Timestamp> entry : map1.entrySet()) {
            Timestamp timestamp= (Timestamp)entry.getValue();
            Timestamp current = new Timestamp(System.currentTimeMillis());
            //si le timestamp du joueur >= 70secondes
            if ( current.getTime() - timestamp.getTime()  >= 70000 && entry!=null) {
                System.out.println(" ----" +entry.getKey().toString());
                Curiosite  curiosite = template.getForObject(uri.concat(entry.getKey().toString()), Curiosite.class);
                sendCuriosite(curiosite);
                //mise à jour du contenu du timestamp à 0
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


    /*
    Cette fonction permet d'analyser la progression
    cette derniere parcoure la map_prog si le timestamp >=10 min, elle envoie sur la globalApiServer
    pour lui demander nbre_reponse relié au joueur
     */

    @Override
    public void AnalyseProgression() {

        RestTemplate template = new RestTemplate();
        System.out.println("analyse progression");
        String uri = "http://localhost:".concat(port).concat("/api/analyste/progression/");


        Map<String, Timestamp > mapPro=map_prog;

        for (Map.Entry<String, Timestamp> entry : mapPro.entrySet()) {
            Timestamp timestamp= (Timestamp)entry.getValue();
            Timestamp current = new Timestamp(System.currentTimeMillis());
            //si le timestamp du joueur >= 90secondes
            if ( current.getTime() - timestamp.getTime()  >= 90000 && entry!=null) {
                System.out.println(" ----" +entry.getKey().toString());
                Progression  progression = template.getForObject(uri.concat(entry.getKey().toString()), Progression.class);
                sendProgression(progression);
                //mise à jour du contenu du timestamp à 0
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

    //fonction qui permet d'envoyer la progression
    public void sendProgression(Progression progression) {

        System.out.println("sendProgression");
        level =levelprogression(progression.getNbre_reponse());
        String url = "http://localhost:".concat(port).concat("api_prog");
        sendGlobalApi(progression.getJoueur(), level, url);
    }

    //fonction qui permet d'affecter le niveau de progression
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

    //fonction qui permet d'affecter le niveau de curiosité
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
