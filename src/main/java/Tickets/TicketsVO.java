/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;

/**
 *
 * @author javie
 */
public class TicketsVO {
    private int codPlazas;
    private String matricula;
    private int pin;
    private int precio;

    public TicketsVO(int codPlazas, String matricula, int pin, int precio) {
        this.codPlazas = codPlazas;
        this.matricula = matricula;
        this.pin = pin;
        this.precio = precio;
    }

    public int getCodPlazas() {
        return codPlazas;
    }

    public void setCodPlazas(int codPlazas) {
        this.codPlazas = codPlazas;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "TicketsVO{" + "codPlazas=" + codPlazas + ", matricula=" + matricula + ", pin=" + pin + ", precio=" + precio + '}';
    }
    
    
    
    
}
