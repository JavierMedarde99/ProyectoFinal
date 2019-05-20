/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abonados;

import java.sql.Timestamp;
import java.time.LocalDate;
import vehiculos.VehiculoVO;

/**
 *
 * @author javi
 */
public class AbonadosVO {

    private String DNI; //Clave primaria
    private String nombre;
    private String apellidos;
    private int pinAbonados;
    private String tarjetaCredito;
    private String email;
    private int tipoAbonados;
    private String matricula;//clave foreanea
    private Timestamp fechaInicioAbono;
    private Timestamp fechaFinAbono;
    
public AbonadosVO() {
    }

    public AbonadosVO(String DNI, String nombre, String apellidos, int pinAbonados, String tarjetaCredito, String email, int tipoAbonados, String matricula, Timestamp fechaInicioAbono) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pinAbonados = pinAbonados;
        this.tarjetaCredito = tarjetaCredito;
        this.email = email;
        this.tipoAbonados = tipoAbonados;
        this.matricula = matricula;
        this.fechaInicioAbono = fechaInicioAbono;
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

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTipoAbonados() {
        return tipoAbonados;
    }

    public void setTipoAbonados(int tipoAbonados) {
        this.tipoAbonados = tipoAbonados;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Timestamp getFechaInicioAbono() {
        return fechaInicioAbono;
    }

    public void setFechaInicioAbono(Timestamp fechaInicioAbono) {
        this.fechaInicioAbono = fechaInicioAbono;
    }

    public Timestamp getFechaFinAbono() {
        return fechaFinAbono;
    }

    public void setFechaFinAbono(Timestamp fechaFinAbono) {
        this.fechaFinAbono = fechaFinAbono;
    }

    @Override
    public String toString() {
        return "AbonadosVO{" + ", DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", pinAbonados=" + pinAbonados + ", tarjetaCredito=" + tarjetaCredito + ", email=" + email + ", tipoAbonados=" + tipoAbonados + ", matricula=" + matricula + ", fechaInicioAbono=" + fechaInicioAbono + ", fechaFinAbono=" + fechaFinAbono + '}';
    }
    
}
