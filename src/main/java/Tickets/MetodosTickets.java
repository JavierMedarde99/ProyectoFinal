/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import vehiculos.VehiculoVO;

/**
 *
 * @author jriosaguilar
 */
public class MetodosTickets {
    
    public static final Double TARIFA_TURISMO=0.12;
    public static final Double TARIFA_MOTOCICLETA=0.08;
    public static final Double TARIFA_CARAVANA=0.45;
    
    public static long retirarTicket(LocalTime tiempoInicio, LocalDate fechaInicio){
        LocalDateTime horaEntrada=LocalDateTime.of(fechaInicio, tiempoInicio);
        return ChronoUnit.MINUTES.between(horaEntrada, LocalDateTime.now());
    }
    
    public static Double calcularPrecioMinuto(VehiculoVO vehiculo){
        switch (vehiculo.getTipoVehiculo()) {
            case 1:
                return TARIFA_TURISMO;
            case 2:
                return TARIFA_MOTOCICLETA;
            case 3:
                return TARIFA_CARAVANA;
            default:
                System.out.println("El tipo de vehiculo es erroneo");
        }
        return 0.0;  
    }
    
}
