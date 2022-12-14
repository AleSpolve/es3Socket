package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Socket s = new Socket("localhost", 3000);
        boolean c=true;
        // per parlare
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        
        // per ascoltare
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // per la tastiera
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

        pr.println("Eccomi");
        System.out.println(br.readLine()); // ricevo: come ti chiami
        pr.println(tastiera.readLine()); // leggo da tastiera il nome e lo invio

        System.out.println(br.readLine());

        while(c){
            System.out.println(br.readLine());//ricevo Cosa vuoi sapere?
            String comandi=tastiera.readLine();//invio il comando
            pr.println(comandi);

            if(comandi.equals("fine")){
                break;
            }
            System.out.println(br.readLine());
        }
                         
        s.close();
    }
}
