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
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
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
                        //Fecha-hora de venta
                        escritura=new PrintWriter(sock.getOutputStream(),true);
                        Date fecha = new Date();
                        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
                        String dateString=sdf.format(fecha);
                        escritura.println(dateString);
                        break;
                    case 3: 
                        do{
                            System.out.println("Ingrese fecha-hora de inicio (yyyy-mm-dd hh:mm:ss):");
                            escritura=new PrintWriter(sock.getOutputStream(),true);
                            linea=teclado.readLine();
                        }while(!validarFecha(linea));
                        escritura.println(linea);
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
    
    public String reciboDatos(Socket socketRecepcion) throws IOException{

        BufferedReader lectura;
        String s = "";
        try {
            lectura=new BufferedReader( new
            InputStreamReader(socketRecepcion.getInputStream()));
            s = lectura.readLine();
        } catch (IOException ex) {
            System.out.println("MENSAJE ERROR EN INICIAR COMUNICACION: "+ex.getMessage());
        }
        return s;
    }
    
    public boolean validarFecha(String fecha){
        boolean valida=false;
        if(fecha.length()==19){
            if(Integer.parseInt(fecha.substring(0, 4)) >= 2017){
                if(fecha.substring(4, 5).equals("-")){
                    if(Integer.parseInt(fecha.substring(5, 7)) >=1 && Integer.parseInt(fecha.substring(5, 7)) <=12){
                        if(fecha.substring(7, 8).equals("-")){
                            if(Integer.parseInt(fecha.substring(8, 10)) >=1 && Integer.parseInt(fecha.substring(8, 10)) <=31){
                                if(fecha.substring(10,11).equals(" ")){
                                    if(Integer.parseInt(fecha.substring(11, 13)) >=0 && Integer.parseInt(fecha.substring(11, 13)) <=23){
                                        if(fecha.substring(13,14).equals(":")){
                                             if(Integer.parseInt(fecha.substring(14, 16)) >=0 && Integer.parseInt(fecha.substring(14, 16)) <=59){
                                                 if(fecha.substring(16,17).equals(":")){
                                                     if(Integer.parseInt(fecha.substring(17, 19)) >=0 && Integer.parseInt(fecha.substring(17, 19)) <=59){
                                                         valida=true;
                                                     }
                                                 }
                                             }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return valida;
    }
}
