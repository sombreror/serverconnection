package it.bianco;

import java.io.*;
import java.net.*;

import java.util.Scanner;
public class ClientEcho {

    public static void main(String[] args){

        try{

            //apre il socket sull'indrizzo 127.0.0.1 (localhost) e prende le informazioni dalla porta 5000 (la porta del server)
            //il client invece (il proprio OS) apre una porta casuale, anche chiamata effimera, per poi chiuderla quando è terminata la comunicazione con il server
            Socket socket = new Socket("localhost", 5000);

            System.out.println("Connesso al server");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            

            //prende l'input dell'utente
            Scanner scanner = new Scanner(System.in);

            while (true) {

                //chiede all'utente di inserire un messaggio
                System.out.print("Inserisci una frase: ");
                String messaggio = scanner.nextLine();

                //invia il messaggio al server
                output.println(messaggio);

                // se l'utente scrive "exit" => termina
                if (messaggio.equalsIgnoreCase("exit")) {
                    break;
                }

                // riceve e legge la risposta data
                String risposta = input.readLine();

                System.out.println("Risposta dal Server: " + risposta);
            }

            //termina il programma 
            scanner.close();
            input.close();
            output.close();
            socket.close();

            System.out.println("Client terminato.");

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}