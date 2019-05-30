/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Abonados.AbonadosVO;
import Tickets.TicketsVO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import plazas.PlazasDAO;
import plazas.PlazasVO;

/**
 *
 * @author javi
 */
public class Admin {

    public static ArrayList<Integer> PrecioEntreDosFechas(LocalDate fecha1 ,LocalDate fecha2,LocalTime tiempo1,LocalTime tiempo2, TicketsVO ticket){
        ArrayList<Integer> precio = new ArrayList<>();
        if(ticket.getFechaInicio().equals(ChronoUnit.YEARS.between(fecha1, fecha2)) && ticket.getFechaFin().equals(ChronoUnit.YEARS.between(fecha1, fecha2))
                && ticket.getTiempoInicio().equals(ChronoUnit.HOURS.between(tiempo1, fecha2)) && ticket.getTiempoFin().equals(ChronoUnit.HOURS.between(tiempo1, fecha2))){
        }
        return precio;
    }
    
    public static void estadoPlazas() throws SQLException{
        PlazasDAO p1=new PlazasDAO();
        ArrayList<PlazasVO> listaPlazas=new ArrayList<>();
        
        listaPlazas=p1.getAll();
        for (PlazasVO tmp : listaPlazas) {
            System.out.println(tmp.getCodigoPlaza());
            int estado=tmp.getEstado();
            switch (estado) {
                case 1:
                    System.out.println("Ocupada");
                    break;
                    
                case 2:
                    System.out.println("Libre");
                    break;
                    
                case 3:
                    System.out.println("Ocupada por abonado");
                    break;
                
                case 4:
                    System.out.println("Libre, pero reservada para abonado");
                    break;
                default:
                    System.out.println("Codigo incorrecto");
            }
        }
    }
}
 