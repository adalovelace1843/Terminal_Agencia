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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.PasswordField;
import valueObjects.VoTicketTerminal;

/**
 *
 * @author f188315
 */




public class Cliente {

    public boolean login(Socket sock, Socket socketRecepcion) throws IOException, ClassNotFoundException{
        boolean resultado=false;
        ObjectOutputStream escritura = new ObjectOutputStream(sock.getOutputStream());
        BufferedReader teclado;
        String usuario,linea;
        teclado=new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Usuario: ");
        usuario=teclado.readLine();
        
        System.out.println("Contraseña: ");
        
        Console c=System.console();
        char[] password = c.readPassword();
        String pass = new String(password);

        linea=usuario+";"+pass;
        escritura.writeObject(linea);
        
        String respuesta=reciboDatos(socketRecepcion);
        if(respuesta.equals("OK")){
            resultado=true;
        }
        return resultado;
    }
    
  
 
    
    public void envioDatos(Socket sock, String opcion) throws IOException{
        ObjectOutputStream escritura = new ObjectOutputStream(sock.getOutputStream());
        /* ENVIO '1' PARA INDICAR AL SERVIDOR QUE QUIERO INICIAR ENVIO DE DATOS DE TICKET */
        if(opcion.equals("1")){
            VoTicketTerminal vo;
            vo = creoVoTerminal();
            escritura.writeObject(opcion);
            ObjectOutputStream escritura2 = new ObjectOutputStream(sock.getOutputStream());
            escritura2.writeObject(vo);
        }else{ //salgo
            escritura.writeObject(opcion);
        }
    }
        
    
    public String reciboDatos(Socket socketRecepcion) throws IOException, ClassNotFoundException{

        String s = "";
        try {
            ObjectInputStream lectura = new ObjectInputStream(socketRecepcion.getInputStream());
            s = (String) lectura.readObject();
        } catch (IOException ex) {
            System.out.println("Error interno terminal agencia: "+ex.getMessage());
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
    
    public VoTicketTerminal creoVoTerminal () throws IOException{
        BufferedReader teclado;
        String linea;
        teclado=new BufferedReader(new InputStreamReader(System.in));
        VoTicketTerminal vo = new VoTicketTerminal();
               
        for (int i =0; i<6; i++){
            switch(i){
                case 0: 
                    System.out.println("Ingrese Matricula:");
                    linea=teclado.readLine();
                    vo.setMatricula(linea);
                    break;
                case 1: 
                    String ag_vta = "Pocitos";
                    vo.setAgencia(ag_vta);
                    break;
                case 2:
                    Date fecha = new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
                    String dateString=sdf.format(fecha);
                    vo.setFecha_hora_venta(dateString);
                    break;
                case 3:
                    boolean error;
                    do{
                        System.out.println("Ingrese fecha-hora de inicio (yyyy-mm-dd hh:mm:ss):");
                        linea=teclado.readLine();
                        if(!validarFecha(linea)){
                            System.out.println("El formato no es valido");
                            error=true;
                        }else{
                            error=false;
                        }    
                    }while(error);
                    vo.setFecha_hora_inicio(linea);
                    break;
                case 4: 
                    System.out.println("Ingrese Minutos:");
                    linea=teclado.readLine();
                    vo.setMin(Integer.parseInt(linea));
                    break;
                case 5: 
                    String term = "01";
                    vo.setTerminal(term);
                    break;
            }
        }
    
        return vo;
    }
}
