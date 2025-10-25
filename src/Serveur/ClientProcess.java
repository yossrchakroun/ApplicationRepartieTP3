package Serveur;

import java.net.Socket;
import Shared.Operation;
import java.io.*;

public class ClientProcess extends Thread{
	 private Socket socketClient;
	 private int clientCount=0;
	
	public ClientProcess(Socket socketClient ,int clientCount) {
		this.socketClient=socketClient;
		this.clientCount=clientCount;
	}
	
    @Override
	public void run() { 
		
        System.out.println("Thread démarré pour le client n°" + clientCount);
		try {
            
            // Création des flux
            ObjectOutputStream Objout = new ObjectOutputStream(socketClient.getOutputStream());
            ObjectInputStream Objin = new ObjectInputStream(socketClient.getInputStream());
            
            
          //Lecture de l'ojet recu par le clent
            //readoject return object et j'ai appliquer Operation donc forcage de type
            Operation opp= (Operation) Objin.readObject();
            
            System.out.println("Objet recuperé :"+opp.getOperande1()+" "+opp.getOperateur()+" "+opp.getOperande2());

            
                int res=0;

                switch (opp.getOperateur()) {
                    case '+':
                        res = opp.getOperande1() + opp.getOperande2();
                        break;
                    case '-':
                        res = opp.getOperande1() - opp.getOperande2();
                        break;
                    case '*':
                        res = opp.getOperande1() * opp.getOperande2();
                        break;
                    case '/':
                        res = opp.getOperande1() / opp.getOperande2();
                        break;
                    default:
                        System.out.println("Erreur : opérateur non valide !");
                        break;
                }

                System.out.println("Résultat envoyé au client  "+ clientCount + " : " + res);
                Objout.writeInt(res);
                Objout.flush();
                // NOUVEAU : Incrémenter le compteur global après traitement réussi
                Serveurthread.incrementOperationCount(clientCount);
                System.out.println("\n ");

                
                
                //fermeture 
                Objin.close();
                Objout.close();
                socketClient.close(); 

                
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur avec le client n°" + clientCount + " : " + e.getMessage());
        }			
	} 
}
