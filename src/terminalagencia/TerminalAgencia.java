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
 * @author e299227
 */
public class TerminalAgencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            
            Socket sock = new Socket("127.0.0.1",1500);
            Cliente c = new Cliente();
            boolean validacion=false;
            while(!validacion){
                try{
                    if(c.login(sock, sock)){
                        validacion=true;
                    }else{
                        System.out.println("Usuario/contraseña incorrectos");
                    }
                }catch (IOException ex) {
                    System.out.println("Hubo un error de comunicación, inténtelo nuevamente.");
                }
            }
            String linea;
            do{
                System.out.println("\n----------------MENU----------------");
                System.out.println("1- Alta ticket");
                System.out.println("0- Salir");
                System.out.println("\nIngrese opción: ");
                BufferedReader teclado;
                teclado=new BufferedReader(new InputStreamReader(System.in));
                linea=teclado.readLine();
                switch(linea){
                    case "1": 
                        c.envioDatos(sock);
                        System.out.println("Datos recibidos del servidor:"+c.reciboDatos(sock));
                        break;
                    case "0":
                        PrintWriter escritura;
                        escritura=new PrintWriter(sock.getOutputStream(),true);
                        escritura.println("0");
                        System.out.println("Saliendo ...");
                        sock.close();
                        break;
                    default : System.out.println("opción incorrecta");break;
                }
                
            }while(!linea.equals("0"));
    
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(TerminalAgencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
    }
    
}
