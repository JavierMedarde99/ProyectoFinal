/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;

import java.sql.Timestamp;

import java.time.LocalDateTime;


/**
 *
 * @author javie
 */
public class TicketsVO {
    private int codPlazas;
    private String matricula;
    private int pin;
    private Double precioFin;
    private Double precioMin; //Depende del vehiculo
    private LocalDateTime tiempoInicio;
    private LocalDateTime tiempoFin;

    public TicketsVO(int codPlazas, String matricula, int pin, Double precioFin) {
        this.codPlazas = codPlazas;
        this.matricula = matricula;
        this.pin = pin;
        this.precioFin = precioFin;
        this.tiempoInicio=LocalDateTime.now();
    }

    TicketsVO() {
        
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

    public Double getPrecioFin() {
        return precioFin;
    }

    public void setPrecioFin(Double precioFin) {
        this.precioFin = precioFin;
    }

    public Double getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(Double precioMin) {
        this.precioMin = precioMin;
    }

    public LocalDateTime getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(LocalDateTime tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public LocalDateTime getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(LocalDateTime tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    @Override
    public String toString() {
        return "TicketsVO{" + "codPlazas=" + codPlazas + ", matricula=" + matricula + ", pin=" + pin + ", precioFin=" + precioFin + ", precioMin=" + precioMin + ", tiempoInicio=" + tiempoInicio + ", tiempoFin=" + tiempoFin + '}';
    }

}
