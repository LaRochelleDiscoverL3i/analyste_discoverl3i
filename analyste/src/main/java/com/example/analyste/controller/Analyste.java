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
    CuriositeService curiositeService;

    @RequestMapping(value = "/progression_test", method = RequestMethod.GET)
    @ResponseBody
    Map<String, Timestamp> getInfos() {

        return curiositeService.getInformations();
    }

    @RequestMapping(value = "/api/{joueur}", method = RequestMethod.GET)
    @ResponseBody
    Curiosite getcuriositeapi(@PathVariable String joueur) {

        return curiositeService.find(joueur);
    }

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    void sendtoapiCurio(@RequestBody Map<String, String > map){

        System.out.println("joueur: ");
        System.out.println(map);
        System.out.println("super");


    }



    @Scheduled(fixedRate = 3000)
    public void greeting() {

        curiositeService.AnalyseCuriosite();
    }

    @RequestMapping(value = "/progression", method = RequestMethod.POST)
    void addCreation(@RequestBody Creation creation){


        curiositeService.createCreation(creation);

    }

    @RequestMapping(value = "/curiosite", method = RequestMethod.POST)
    void addCuriosite(@RequestBody Curiosite curiosite){


        curiositeService.sendCuriosite(curiosite);

    }

}
