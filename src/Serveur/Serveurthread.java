package Serveur;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveurthread extends Thread  {

    private static int clientCount = 0;
    // NOUVEAU : Compteur global des opérations traitées
    private static int operationCount = 0;
    
    //NOUVEAU : Objet de verrouillage pour synchronisation
    private static final Object lock = new Object();
    
    
    public static void main(String[] args) {
    
            //ceration de serveur Multithread
            Serveurthread SMT = new Serveurthread();
            SMT.start();
    }
        
    	public void run() {
            try {
         		ServerSocket socketServeur = new ServerSocket(6005);
    			System.out.println("Je suis un serveur en attente la connexion d'un client ");

            	 while(true) {
                	 Socket socketClient = socketServeur.accept();
                	 clientCount++;
    		         System.out.println("Un client est connecté : "+socketClient.getRemoteSocketAddress());	         
    		         System.out.println("de nemero d'ordre : "+clientCount);
    		         
    		         // Créer un thread pour gérer ce client
    		         ClientProcess clientProcess = new ClientProcess(socketClient,clientCount);
    		         clientProcess.start(); 
            	 }

            }catch (IOException e) {
                System.out.println("Erreur avec le client n°" + clientCount);
            }      
	    }   
    	
    	// NOUVEAU : Méthode synchronisée pour incrémenter le compteur
        public static void incrementOperationCount(int clientCount) {
            synchronized (lock) {
                operationCount++;
                System.out.println();
                System.out.println(" COMPTEUR GLOBAL INCRÉMENTÉ ");
                System.out.println("  Client n°" + clientCount + " a terminé son opération ");
                System.out.println(" Nombre total d'opérations : " + operationCount );
                System.out.println();
            }
        }
        
        //  NOUVEAU : Méthode pour obtenir le compteur (lecture sécurisée)
        public static int getOperationCount() {
            synchronized (lock) {
                return operationCount;
            }
        }
}            

	           

