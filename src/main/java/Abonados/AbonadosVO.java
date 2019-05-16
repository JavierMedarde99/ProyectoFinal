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
}
