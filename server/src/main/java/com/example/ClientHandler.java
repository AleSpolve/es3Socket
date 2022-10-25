package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;


public class ClientHandler extends Thread {

    private Socket s;
    private static int x=1;
    private boolean a=true;
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();
    private String nomeServer="SERVERONE";

    public ClientHandler(Socket s){
        this.s=s;
    } 

    public void run(){

        try {
            
        System.out.println("Client connesso");

        // per parlare
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
          
        // per ascoltare
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        System.out.println(br.readLine()); // ricevo: Eccomi
        pr.println("Ciao, come ti chiami?"); // invio messaggio
        String nome = br.readLine(); // ricevo il nome
        System.out.println("nome ricevuto");
        System.out.println(nome.toUpperCase());//converto il nome in maiuscolo
        
        pr.println("Benvenuto " + nome + " sei l'utente numero: " + x);
        
        while(a){
            pr.println("Cosa vuoi sapere?"); 
            String comandi = br.readLine();
            
            if(comandi.equals("data")){
               
                pr.println(date);
            }else if(comandi.equals("ora")){

                pr.println(time);

            }else if(comandi.equals("nome")){

                pr.println(nomeServer);
            }else if(comandi.equals("id")){

                pr.println(x);            
            }else if(comandi.equals("fine")){

                s.close();
            }
            x++;
        }
        pr.println("Grazie e ciao"); 

        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      
    }
}

