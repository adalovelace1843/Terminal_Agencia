/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import exceptions.ExComunicacion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 *
 * @author f188315
 */
public class Comunicacion {
    Socket sock;

    public Comunicacion() {
        
    }
    
    public void crearComunicacion(String ip, int puerto) throws ExComunicacion{
        try {
            this.sock = new Socket(ip,puerto);
        } catch (IOException ex) {
            throw new ExComunicacion("Error al crear comunicacion (TA)["+ex.getMessage()+"]");
        }
    }
    
    public void envioDatos(Object objeto) throws ExComunicacion{
        try {
            ObjectOutputStream escritura = new ObjectOutputStream(sock.getOutputStream());
            escritura.writeObject(objeto);
            
        } catch (IOException ex) {
            throw new ExComunicacion("Error al enviar datos (TA)["+ex.getMessage()+"]");
        }    
    }
    
    public Object reciboDatos() throws ExComunicacion {
        Object s;
        try {
            ObjectInputStream lectura = new ObjectInputStream(sock.getInputStream());
            s =  lectura.readObject();
        } catch (IOException ex) {
            throw new ExComunicacion("Error al recibir datos desde Servidor Agencia (I/O) (TA)["+ex.getMessage()+"]");
        } catch (ClassNotFoundException ex) {
            throw new ExComunicacion("Error al recibir datos desde Servidor Agencia (TA)["+ex.getMessage()+"]");
        }
        return s;
    }
    
    public void Finalizar() throws ExComunicacion{
        try {
            envioDatos("0");
            this.sock.close();
        } catch (IOException ex) {
            throw new ExComunicacion("Error finalizar la conexion (TA)["+ex.getMessage()+"]");
        }
    }
}
