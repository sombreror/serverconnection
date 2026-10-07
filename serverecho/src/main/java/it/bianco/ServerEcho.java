package it.bianco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEcho {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server in ascolto sulla porta 5000...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connesso.");

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true
            );

            String messaggio;

            while (true) {

                // Legge il messaggio del client
                messaggio = input.readLine();

                // Controlla se il client ha chiuso la connessione
                if (messaggio == null) {
                    break;
                }

                // Se riceve "exit", termina
                if (messaggio.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Messaggio ricevuto: " + messaggio);

                // Converte il messaggio in maiuscolo
                String risposta = messaggio.toUpperCase();

                // Invia la risposta al client
                output.println(risposta);
            }

            input.close();
            output.close();
            socket.close();
            serverSocket.close();

            System.out.println("Server terminato.");

        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}