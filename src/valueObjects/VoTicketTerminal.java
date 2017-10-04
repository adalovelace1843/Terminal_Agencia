/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObjects;

import java.io.Serializable;

/**
 *
 * @author e299227
 */
public class VoTicketTerminal implements Serializable{
    private String matricula;
    private String fecha_hora_venta;
    private String fecha_hora_inicio;
    private int min;
    private String terminal;

    public VoTicketTerminal() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getFecha_hora_venta() {
        return fecha_hora_venta;
    }

    public void setFecha_hora_venta(String fecha_hora_venta) {
        this.fecha_hora_venta = fecha_hora_venta;
    }

    public String getFecha_hora_inicio() {
        return fecha_hora_inicio;
    }

    public void setFecha_hora_inicio(String fecha_hora_inicio) {
        this.fecha_hora_inicio = fecha_hora_inicio;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
    
    
}
