/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminalagencia;

import java.io.IOException;
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
            c.envioDatos(sock);
            System.out.println("Datos recibidos del servidor:"+c.reciboDatos(sock));
            sock.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(TerminalAgencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
    }
    
}
