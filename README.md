# Analyste




Les routes :
- localhost:8083/create_joeur    /* Http POST /



Chaque fois que un joueur est cree  GlobaApiServer fait un POST sur url (localhost:8083/create_joeur) de l'analyste avec le json suivant:
    {
        - joueur
    }

    ex:
    {
        - "joueur": "toto"
    }
    

Pour la partie curiosite :

Tous les n minutes analayste fait un get sur l'url suivant de globalApiserver : localhost:8081/curiosite/{joueur}
 et il  doit reccuperer le json suivant:

 {
     joueur,
     nbre_scan
 }

 ex:  localhost:8081/curiosite/toto
{
     "joueur":"toto",
     "nbre_scan": 2
 }

des que l'analyste fait une analyse de curiosite pour un joueur il fait un post sur globalApiserver sur l'url (localhost:8081/api/curioisite/{joueur})  avec le json suivant :
    {
        joueur
        level
    }

    ex: localhost:8081/api/curioisite/toto

    {
        "joueur":"toto",
        "level":"bas"
    }

