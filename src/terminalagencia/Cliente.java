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
            System.out.println("Ingrese comando:");
            escritura=new PrintWriter(sock.getOutputStream(),true);
            linea=teclado.readLine();
            escritura.println(linea);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
