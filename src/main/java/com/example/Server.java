package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static private ServerSocket serverSocket;
    static private Socket client;
    static private BufferedReader input;
    static private DataOutputStream output;


     public void accept(){

        try {

                client = serverSocket.accept();
                input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                output = new DataOutputStream(client.getOutputStream());
            

        } catch (IOException e) {
            
            System.out.println("Errore durante la creazione delle stream I/O");
            e.printStackTrace();
        }

     }

     public  void openServer() {

          try {

               serverSocket = new ServerSocket(8080);

          } catch (IOException e) {

               System.out.println("Errore durante l'avvio del ServerSocket");
               e.printStackTrace();
          }
     }

     public  String read(){
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
     } 
    
     public  void reply(byte [] p){
        try {
            output.write(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
     }

     public void close() {
        try {
            input.close();
            output.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
     }
}
