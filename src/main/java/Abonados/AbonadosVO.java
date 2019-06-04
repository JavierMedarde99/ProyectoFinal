/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abonados;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import vehiculos.VehiculoVO;

/**
 *
 * @author javi
 */
public class AbonadosVO {

    private String DNI; //Clave primaria
    private String nombre;
    private String apellidos;
    private String pinAbonados;
    private String tarjetaCredito;
    private String email;
    private int tipoAbonados; //1-Mensual 2-Trimestral 3-Semestral 4-Anual
    private String matricula;//clave foreanea
    private LocalDate fechaInicioAbono;
    private LocalDate fechaFinAbono;
    
public AbonadosVO() {
    }

    public AbonadosVO(String DNI, String nombre, String apellidos, String pinAbonados, String tarjetaCredito, String email, int tipoAbonados, String matricula, LocalDate fechaInicioAbono) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pinAbonados = pinAbonados;
        this.tarjetaCredito = tarjetaCredito;
        this.email = email;
        this.tipoAbonados = tipoAbonados;
        this.matricula = matricula;
        this.fechaInicioAbono = fechaInicioAbono;
        switch (tipoAbonados) {
            case 1:
                this.fechaFinAbono=fechaInicioAbono.plusMonths(1);
                break;
            case 2:
                this.fechaFinAbono=fechaInicioAbono.plusMonths(3);
                break;
            case 3:
                this.fechaFinAbono=fechaInicioAbono.plusMonths(4);
                break;
            case 4:
                this.fechaFinAbono=fechaInicioAbono.plusYears(1);
                break;
        }
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

    public String getPinAbonados() {
        return pinAbonados;
    }

    public void setPinAbonados(String pinAbonados) {
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

    public LocalDate getFechaInicioAbono() {
        return fechaInicioAbono;
    }

    public void setFechaInicioAbono(LocalDate fechaInicioAbono) {
        this.fechaInicioAbono = fechaInicioAbono;
    }

    public LocalDate getFechaFinAbono() {
        return fechaFinAbono;
    }

    public void setFechaFinAbono(LocalDate fechaFinAbono) {
        this.fechaFinAbono = fechaFinAbono;
    }

    @Override
    public String toString() {
        return  DNI + "|" + nombre + "|" + apellidos + "|" + pinAbonados + "|" + tarjetaCredito + "|" + email + "|" + tipoAbonados + "|" + matricula + "|" + fechaInicioAbono + "|" + fechaFinAbono;
    }
    
    
    public String toStringNormal() {
        return "AbonadosVO{" + ", DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", pinAbonados=" + pinAbonados + ", tarjetaCredito=" + tarjetaCredito + ", email=" + email + ", tipoAbonados=" + tipoAbonados + ", matricula=" + matricula + ", fechaInicioAbono=" + fechaInicioAbono + ", fechaFinAbono=" + fechaFinAbono + '}';
    }
}
