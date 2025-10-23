Activite1 TP3 :
Ce code implémente un serveur TCP multithread qui peut gérer plusieurs clients simultanément. Chaque client est traité dans son propre thread indépendant.
La creation de thread server et après .start() lance la methode run qui s'exécute dans un nouveau thread   =>Crée ServerSocket sur port 6005  => Entre en boucle infinie while(true).
Mécanisme : `accept()` est bloquant : le serveur attend qu'un client se connecte , Dès qu'un client arrive, `accept()` retourne un `Socket` dédié 
 Le serveur incrémente le compteur et crée un thread enfant (ClientProcess) 
 Le serveur retourne immédiatement à `accept()` pour attendre le prochain client
Analyse Thread par Thread :
Thread 1 : Serveurthread:
Tâche :  - Écouter sur le port 6005  
         - Accepter les nouvelles connexions 
         - Créer un thread par client  
          - Ne traite JAMAIS les données client directement                          
Thread 2, 3, 4... : ClientProcess : 
Tâche : 
         - Communiquer avec son client assigné  
         - Lire les messages entrants  
         - Envoyer des réponses  
      - Fermer la connexion quand terminé  
Indépendance : Chaque thread est complètement isolé des autres.
