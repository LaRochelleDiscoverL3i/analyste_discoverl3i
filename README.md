# Analyste




Les routes :
- localhost:8083/analyste/creation     /* Http POST /



Chaque fois que un joueur est cree  GlobaApiServer fait un POST sur url (localhost:8083/analyste/creation ) de l'analyste avec le json suivant:
    {
        - joueur
    }

    ex:
    {
        - "joueur": "toto"
    }
    

Pour la partie curiosite :

Toutes les n minutes l'agent analyste fait un get sur l'url suivant de globalApiserver : localhost:8081/api/analyste/curiosite/{joueur}
 et il  doit reccuperer le json suivant:

``` json 
{
     joueur,
     nbre_scan
 }
```
     ex:  localhost:8081/api/analyste/curiosite/toto
    {
         "joueur":"toto",
         "nbre_scan": 2
     }

des que l'analyste fait une analyse de curiosite pour un joueur il fait un post sur globalApiserver sur l'url (localhost:8081/api/analyste/curioisite/{joueur}) 
 avec le json suivant :
``` json
    {
        joueur
        level
    }
```
    ex: localhost:8081/api/analyste/curioisite/toto

    {
        "joueur":"toto",
        "level":"bas"
    }

Pour la partie Progression : 
Toutes les n minutes (double temps que la curiosité 10 min ) fait un get pour sur l'url de globalApiServer localhost:8081/analyste/progression/{joueur}
 ```json
     {
         joueur
         nbre_reponse
     }
 ```

     ex:  localhost:8081/analyste/progression/toto
    {
         "joueur":"toto",
         "nbre_reponse": 2
     }
     
 Dés que l'analyste fait l'analyse sur la progression du joueur 
 ce dernier fait un post sur globalApiserver sur l'url (localhost:8081/api/analyste/progression/{joueur}) 
  avec le json suivant :
 ```json
 {
     joueur
     level
 }
```
 
     ex: localhost:8081/api/analyste/progression/toto
 
     {
         "joueur":"toto",
         "level":"bas"
     }