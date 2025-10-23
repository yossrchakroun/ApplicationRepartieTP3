package Clent;

import java.net.Socket;
import java.io.*;

public class ClientProcess extends Thread{
	 private Socket socketClient;
	 private int clientCount=0;
	
	public ClientProcess(Socket socketClient ,int clientCount) {
		this.socketClient=socketClient;
		this.clientCount=clientCount;
	}
	
	public void run() { 
		
		System.out.println("Le client est connecté ! ");
		try {
            System.out.println("Le client n°" + clientCount + " est connecté !");
            
            // Création des flux
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socketClient.getOutputStream()), true);
            
            // Envoyer un message de bienvenue au client
            out.println("Bienvenue ! Vous êtes le client n°" + clientCount);
            
            // Lire les messages du client
            String messageClient;
            while ((messageClient = in.readLine()) != null) {
                System.out.println("Client n°" + clientCount + " a envoyé : " + messageClient);
                
            }
            
        }catch (IOException e) {
            System.out.println("Erreur avec le client n°" + clientCount + " : " + e.getMessage());
        }			
	} 
}
