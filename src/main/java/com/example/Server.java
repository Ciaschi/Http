package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server {

    static private ServerSocket serverSocket;
    static private Socket client;
    static private BufferedReader input;
    static private DataOutputStream output;


     public static void accept(){

        try {

            while(true){
                client = serverSocket.accept();
            }

        } catch (IOException e) {
            
            System.out.println("Errore durante la creazione delle stream I/O");
            e.printStackTrace();
        }

     }
     
     public class provoAFareUnaSortaDiMultiThread extends Thread{
        private Socket s;
        public provoAFareUnaSortaDiMultiThread(Socket s){
            this.s = s;
            try {
                input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                output = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
        
        @Override
        public void run(){

            String req = Server.read().split(" ")[1];

            if(req.equals("/")){
                req = "/index.html";
            }

            File file = new File("src/main/resources" + req);

            try {
                if(file.exists() && file.isFile()){
                Server.reply("HTTP/1.1 200 OK\r\n".getBytes());
                Server.reply("Content-type: text/html\r\n\r\n".getBytes());
                Server.reply(Files.readAllBytes(file.toPath()));
            }else{
                Server.reply("HTTP/1.1 404 Not Found\r\n".getBytes());
                Server.reply("Content-type: text/html\r\n\r\n".getBytes());
                Server.reply(Files.readAllBytes(Paths.get("src/main/resources/error.html")));
            }
            } catch (Exception e) {
            }

        }

     }

     public static void openServer() {

          try {

               serverSocket = new ServerSocket(8000);

          } catch (IOException e) {

               System.out.println("Errore durante l'avvio del ServerSocket");
               e.printStackTrace();
          }
     }

     public static String read(){
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
     } 
    
     public static void reply(byte [] p){
        try {
            output.write(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
}
