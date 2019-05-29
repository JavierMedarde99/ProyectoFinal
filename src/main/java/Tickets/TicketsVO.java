/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;

import java.sql.Timestamp;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;


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
    private LocalDate fechaInicio;
    private LocalTime tiempoInicio;
     private LocalDate fechaFin;
    private LocalTime tiempoFin;

    public TicketsVO(int codPlazas, String matricula, int pin, Double precioFin, Double precioMin, LocalDate fechaInicio, LocalTime tiempoInicio) {
        this.codPlazas = codPlazas;
        this.matricula = matricula;
        this.pin = pin;
        this.precioFin = precioFin;
        this.precioMin = precioMin;
        this.fechaInicio = fechaInicio;
        this.tiempoInicio = tiempoInicio;
    }

    

    public TicketsVO() {
        
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalTime getTiempoInicio() {
        return tiempoInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalTime getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(LocalTime tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    public void setTiempoInicio(LocalTime tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    @Override
    public String toString() {
        return "TicketsVO{" + "codPlazas=" + codPlazas + ", matricula=" + matricula + ", pin=" + pin + ", precioFin=" + precioFin + ", precioMin=" + precioMin + ", fechaInicio=" + fechaInicio + ", tiempoInicio=" + tiempoInicio + ", fechaFin=" + fechaFin + ", tiempoFin=" + tiempoFin + '}';
    }

}
