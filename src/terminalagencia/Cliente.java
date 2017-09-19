/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminalagencia;

import comunicacion.Comunicacion;
import exceptions.ExCliente;
import exceptions.ExComunicacion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import valueObjects.VoTicketTerminal;

/**
 *
 * @author f188315
 */




public class Cliente {
    private Comunicacion com;

    public Cliente() throws ExComunicacion {
        com = new Comunicacion();
        com.crearComunicacion("127.0.0.1", 1500);
    }
    
    public boolean login() throws ExComunicacion,ExCliente{
        boolean resultado=false;
        try {
            BufferedReader teclado;
            String usuario,linea;
            
            /*INGRESO USUARIO Y CONTRASEÑA*/
            teclado=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Usuario: ");
            usuario=teclado.readLine();
            System.out.println("Contraseña: ");
            Console c=System.console();
            char[] password = c.readPassword();
            String pass = new String(password);
            linea=usuario+";"+pass;
            /*FIN*/
            
            /*ENVIO DATOS*/
            com.envioDatos(linea);
            
            /*ESPERO RESPUESTA DE SERVIDOR AGENCIA*/
            String respuesta=(String) com.reciboDatos();
            
            if(respuesta.equals("OK")){
                resultado=true;
            }   
        } catch (IOException ex) {
            throw new ExCliente("Error en login (TA)["+ex.getMessage()+"]");
        }
        return resultado;
    }
    
    private boolean validarFecha(String fecha){
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
    
    public String altaTicket () throws ExCliente, ExComunicacion {
        BufferedReader teclado;
        String linea,resp;
        teclado=new BufferedReader(new InputStreamReader(System.in));
        VoTicketTerminal vo = new VoTicketTerminal();
        
        try {       
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
            /*ENVIO LOS DATOS AL SERVIDOR*/
            com.envioDatos("1");
            com.envioDatos(vo);
            
            /*RECIBO RESPUESTA DEL SERVIDOR*/
            resp = (String) com.reciboDatos();
        } catch (IOException ex) {
            throw new ExCliente("Error al ingresar informaciòn (I/O)");
        }
    
        return resp;
    }
    
    public void finalizar() throws ExComunicacion{
        com.Finalizar();
    }
}
