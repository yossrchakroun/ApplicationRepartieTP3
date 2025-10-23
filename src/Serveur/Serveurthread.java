package Serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import Clent.ClientProcess;

public class Serveurthread extends Thread  {

    private static int clientCount = 0;
    public static void main(String[] args) {
    	
            System.out.println("Je suis un serveur en attente de connexion d’un client.");

            
            //ceration de serveur Multithread
            Serveurthread SMT = new Serveurthread();
            SMT.start();
 	
    }
    
    	public void run() {
            try {
         		ServerSocket socketServeur = new ServerSocket(6005);
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
}            

	           

