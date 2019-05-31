/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Scanner;
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
    
    public static void crearTicket(String matricula, int plaza, int tipo) throws SQLException{
        Scanner teclado=new Scanner(System.in);
        Random rnd = new Random();
        
        String pin="";
        int numero;
        for(int x=0;x<6;x++){
            numero=rnd.nextInt(10);
            pin+=numero;
        }
        
        Double precioMin=1.0;
        if(tipo==1){
            precioMin=TARIFA_TURISMO;
        }else if(tipo==2){
            precioMin=TARIFA_MOTOCICLETA;
        }else if(tipo==3){
            precioMin=TARIFA_CARAVANA;
        }
        
        LocalDate fechaInicio=LocalDate.now();
        LocalTime tiempoInicio=LocalTime.now();
        
        TicketsVO t=new TicketsVO(plaza, matricula, pin, 0.0, precioMin, fechaInicio, tiempoInicio);
        
        TicketsDAO t1=new TicketsDAO();
        t1.insertTickets(t);
    }
    
}
