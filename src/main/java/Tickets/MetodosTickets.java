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
    
    public static long retirarTicket(LocalTime tiempoInicio, LocalDate fechaInicio){
        LocalDateTime horaEntrada=LocalDateTime.of(fechaInicio, tiempoInicio);
        return ChronoUnit.MINUTES.between(horaEntrada, LocalDateTime.now());
    }
    
    public static Double calcularPrecioMinuto(VehiculoVO vehiculo){
        switch (vehiculo.getTipoVehiculo()) {
            case 1:
                return 0.12;
            case 2:
                return 0.008;
            case 3:
                return 0.45;
            default:
                System.out.println("El tipo de vehiculo es erroneo");
        }
        return 0.0;  
    }
    
}
