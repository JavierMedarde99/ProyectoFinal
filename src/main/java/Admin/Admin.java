/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Abonados.AbonadosVO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author javi
 */
public class Admin {
    public static void main(String[] args) {
         
           
    }
    public static int PrecioEntreDosFechas(LocalDate fecha1 ,LocalDate fecha2,AbonadosVO abonado){
        if(abonado.getFechaInicioAbono().equals(ChronoUnit.YEARS.between(fecha1, fecha2)) && abonado.getFechaFinAbono().equals(ChronoUnit.YEARS.between(fecha1, fecha2))){
            
        }
    }
}
 