/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abonados;

/**
 *
 * @author javi
 */
public class AbonadosVO {
     private int codAbonados=0;
    private String DNI;
    private String nombre;
    private String apellidos;
    private int pinAbonados;
    
public AbonadosVO() {
    }

    public AbonadosVO( String nombre, String apellidos,String DNI, int pinAbonados) {
        this.codAbonados=codAbonados;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pinAbonados = pinAbonados;
        codAbonados++;
    }
    
     public  int getCodAbonado() {
        return codAbonados;
    }

    public  void setCodAbonado(int codAbonado) {
        this.codAbonados = codAbonado;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getPinAbonados() {
        return pinAbonados;
    }

    public void setPinAbonados(int pinAbonados) {
        this.pinAbonados = pinAbonados;
    }

    @Override
    public String toString() {
        return "AbonadosVO{" + "DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", pinAbonados=" + pinAbonados + '}';
    }
}
