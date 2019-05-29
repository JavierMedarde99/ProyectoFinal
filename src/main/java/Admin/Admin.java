/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Abonados.AbonadosVO;
import Tickets.TicketsVO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author javi
 */
public class Admin {
    public static void main(String[] args) {
         
           
    }
    public static ArrayList<Integer> PrecioEntreDosFechas(LocalDate fecha1 ,LocalDate fecha2,LocalTime tiempo1,LocalTime tiempo2, TicketsVO ticket){
        ArrayList<Integer> precio = new ArrayList<>();
        if(ticket.getFechaInicio().equals(ChronoUnit.YEARS.between(fecha1, fecha2)) && ticket.getFechaFin().equals(ChronoUnit.YEARS.between(fecha1, fecha2))
                && ticket.getTiempoInicio().equals(ChronoUnit.HOURS.between(tiempo1, fecha2)) && ticket.getTiempoFin().equals(ChronoUnit.HOURS.between(tiempo1, fecha2))){
           
        }
    }
}
 