package Clent;

import java.io.*;

import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté...");
            Socket socketClient = new Socket("localhost", 6005);

            // Création des flux
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            

 
                // lecture du résultat
                String res = in.readLine();
                System.out.println("Résultat reçu du serveur : " + res);


            // Fermeture
            in.close();
            out.close();
            socketClient.close();
            System.out.println("Connexion fermée côté client.");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}