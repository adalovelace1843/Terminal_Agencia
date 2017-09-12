/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminalagencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author f188315
 */
public class Cliente {
    
    public void envioDatos(Socket sock){
        PrintWriter escritura;
        BufferedReader teclado;
        String linea;
        teclado=new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i =0; i<6; i++){
                switch(i){
                    case 0: 
                        System.out.println("Ingrese Matricula:");
                        escritura=new PrintWriter(sock.getOutputStream(),true);
                        linea=teclado.readLine();
                        escritura.println(linea);
                        break;
                    case 1: 
                        String ag_vta = "Pocitos";
                        escritura=new PrintWriter(sock.getOutputStream(),true);
                        linea=ag_vta;
                        escritura.println(linea);
                        break;
                    case 2: 
                        escritura=new PrintWriter(sock.getOutputStream(),true);
                        Date fecha = new Date();
                        escritura.println(fecha);
                        break;
                    case 3: 
                        escritura=new PrintWriter(sock.getOutputStream(),true);
                        Date fecha2 = new Date();
                        escritura.println(fecha2);
                        break;
                    case 4: 
                        System.out.println("Ingrese Minutos:");
                        escritura=new PrintWriter(sock.getOutputStream(),true);
                        linea=teclado.readLine();
                        escritura.println(linea);
                        break;
                    case 5: 
                        String term = "01";
                        escritura=new PrintWriter(sock.getOutputStream(),true);
                        linea=term;
                        escritura.println(linea);
                        break;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*try {
            
            System.out.println("Ingrese comando:");
            escritura=new PrintWriter(sock.getOutputStream(),true);
            linea=teclado.readLine();
            escritura.println(linea);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
