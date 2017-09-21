/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminalagencia;

import exceptions.ExCliente;
import exceptions.ExComunicacion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author e299227
 */
public class TerminalAgencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Defino un cliente nuevo, que me va a definir un socket para la comunicaciòn con el servidor Agencia
        Cliente c;
        try {
            c = new Cliente();
            /* REALIZO LA VALIDACION HACIA EL SERVIDOR DE AGENCIA */
            boolean validacion=false;
            System.out.println("Bienvenidos a sistema tickets TicketsLink");
            while(!validacion){  
                if(c.login()){
                    validacion=true;
                }else{
                    System.out.println("Usuario/contraseña incorrectos!");
                }
            }
            /*FIN VALIDACION*/

            /*INICIO MENU*/
            String linea;
            do{
                System.out.println("\n----------------MENU----------------");
                System.out.println("1- Alta ticket");
                System.out.println("2- Anular ticket");
                System.out.println("0- Salir");
                System.out.println("\nIngrese opción: ");
                BufferedReader teclado;
                teclado=new BufferedReader(new InputStreamReader(System.in));
                linea=teclado.readLine();
                switch(linea){
                    case "1": 
                        System.out.println("\n\nDatos recibidos del servidor:"+c.altaTicket()+"\n");
                        break;
                    case "2":
                        System.out.println("\n\nDatos recibidos del servidor:"+c.anularTicket()+"\n");
                        break;
                    case "0":
                        c.finalizar();
                        System.out.println("Saliendo ...");
                        break;
                    default : System.out.println("opción incorrecta!");break;
                }

            }while(!linea.equals("0"));
        } catch (ExComunicacion ex) {
            System.out.println(ex.getMessage());
        } catch (ExCliente ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
                System.out.println("Error al ingresar informaciòn (I/O)");
        }
       
    }
}
