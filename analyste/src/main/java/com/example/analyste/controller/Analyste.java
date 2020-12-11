package com.example.analyste.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Analyste {


    @Autowired
    ProgressionService progressionService;
    CuriositeService curiositeService;

    @RequestMapping(value = "/analyse", method = RequestMethod.GET)
    @ResponseBody
    List<String> getInfos() {

        return progressionService.getInformations();
    }




    @RequestMapping(value = "/progression", method = RequestMethod.POST)
    void addProgression(@RequestBody Progression progression){


        progressionService.createProgression(progression);

    }

    @RequestMapping(value = "/curiosite", method = RequestMethod.POST)
    void addCuriosite(@RequestBody Curiosite curiosite){


        curiositeService.createCuriosite(curiosite);

    }

}
