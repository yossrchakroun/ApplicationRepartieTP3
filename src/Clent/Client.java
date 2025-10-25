package Clent;

import java.io.*;
import Shared.Operation;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté...");
            Socket socketClient = new Socket("192.168.56.1", 6005);
            System.out.println("Je suis un client connecté");


         // Création des flux
            ObjectOutputStream Objout = new ObjectOutputStream(socketClient.getOutputStream());
            ObjectInputStream Objin = new ObjectInputStream(socketClient.getInputStream());
            Scanner sc = new Scanner(System.in);

            while (true) {
                int a, b;
                char op;

                //validation de premier operande
                while (true) {
                    System.out.print("Entrez la premiére valeur : ");
                    if (sc.hasNextInt()) {
                        a = sc.nextInt();
                        break;
                    }
                    else {
                        System.out.println("Erreur : entrez un nombre entier !");
                        sc.next(); // vider la mauvaise entrée
                    }
                }
               
                
                // validation opérateur
                while (true) {
                    System.out.print("Entrez l’opérateur (+, -, *, /) : ");
                    op = sc.next().charAt(0);
                    if (op == '+' || op == '-' || op == '*' || op == '/') break;
                    System.out.println("Erreur : opérateur non valide !");
                }

                // --- Saisie de la deuxième valeur ---
                while (true) {
                    System.out.print("Entrez la deuxième valeur : ");
                    if (sc.hasNextInt()) {
                        b = sc.nextInt();
                        if (op == '/' && b == 0) {
                            System.out.println("Erreur : division par zéro interdite !");
                            continue; // redemande juste la deuxième valeur
                        }
                        break;
                    } else {
                        System.out.println("Erreur : entrez un nombre entier !");
                        sc.next(); // vider la mauvaise entrée
                    }
                }
                

                // création et envoi de l'objet
                Operation opp = new Operation(a, b, op);
                Objout.writeObject(opp);
                Objout.flush();

                // lecture du résultat
                int res = Objin.readInt();
                System.out.println("Résultat reçu du serveur : " + res);
                
             // Si tout est valide, on sort de la boucle
                break;  
            }


            // Fermeture
            Objin.close();
            Objout.close();
            socketClient.close(); 
            sc.close();
            System.out.println("Connexion fermée côté client.");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}