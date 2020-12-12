package com.example.analyste.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@EnableScheduling
@RestController
public class Analyste {


    @Autowired
    AnalysteService analysteService;

    @RequestMapping(value = "/curiosite_test", method = RequestMethod.GET)
    @ResponseBody
    Map<String, Timestamp> getInfos_curio() {

        return analysteService.getMap_curio();
    }

    @RequestMapping(value = "/progression_test", method = RequestMethod.GET)
    @ResponseBody
    Map<String, Timestamp> getInfos_prog() {

        return analysteService.getMap_prog();
    }


    @RequestMapping(value = "/api/{type}/{joueur}", method = RequestMethod.GET)
    @ResponseBody
    Object getAnalysteapi(@PathVariable String joueur, @PathVariable String type) {
    if (type.equals("curiosite")){
        return analysteService.find_curiosite(joueur);}
        else {
    return analysteService.find_progression(joueur);
        }

    }

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    void sendtoapiCurio(@RequestBody Map<String, String > map){

        System.out.println("joueur: ");
        System.out.println(map);
        System.out.println("super");


    }



    @Scheduled(fixedRate = 3000)
    public void curiosite() {

        analysteService.AnalyseCuriosite();
    }

    @Scheduled(fixedRate = 8000)
    public void progression() {

        analysteService.AnalyseProgression();
    }

    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    void addCreation(@RequestBody Creation creation){
        analysteService.createCreation(creation);

    }

    @RequestMapping(value = "/curiosite", method = RequestMethod.POST)
    void addCuriosite(@RequestBody Curiosite curiosite){


        analysteService.sendCuriosite(curiosite);

    }

    @RequestMapping(value = "/progression", method = RequestMethod.POST)
    void addCuriosite(@RequestBody Progression progression){


        analysteService.sendProgression(progression);

    }

}
