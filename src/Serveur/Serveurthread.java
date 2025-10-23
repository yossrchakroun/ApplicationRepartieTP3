package Serveur;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Clent.ClientProcess;

public class Serveurthread extends Thread  {

    private static int clientCount = 0;
    public static void main(String[] args) {
    
            //ceration de serveur Multithread
            Serveurthread SMT = new Serveurthread();
            SMT.start();
 	
    }
    
    	public void run() {
            try {
         		//ServerSocket socketServeur = new ServerSocket(6005);
            	// IP du serveur locale ou d'une autre machine
                InetAddress ipServeur = InetAddress.getByName("192.168.56.1");
                
                //Il permet de lier un socket à une adresse et un port spécifiques.
                InetSocketAddress SocketADR = new InetSocketAddress(ipServeur,6005);
                
    			//1ere etape : creation de socket du serveur pour recevoir la req du client
    			ServerSocket socketServeur = new ServerSocket ();//precision du port
    			
    			//lier (attacher) un socket à une adresse réseau (IP + port).
    			socketServeur.bind(SocketADR);
    			System.out.println("serveur demarré sur "+ ipServeur.getHostAddress()+" : "+6005);
                
    			
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
}            

	           

