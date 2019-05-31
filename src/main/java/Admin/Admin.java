/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Abonados.AbonadosDAO;
import Abonados.AbonadosVO;
import Tickets.TicketsDAO;
import Tickets.TicketsVO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import plazas.PlazasDAO;
import plazas.PlazasVO;

/**
 *
 * @author javi
 */
public class Admin {

    public static void PrecioEntreDosFechas(LocalDate fecha1 ,LocalDate fecha2,LocalTime tiempo1,LocalTime tiempo2) throws SQLException{
        TicketsDAO p1=new TicketsDAO();
        ArrayList<TicketsVO> listaTickets=new ArrayList<>();
        Double precio=0.0;
        int contador=0;
        listaTickets=p1.getAll();
        for (TicketsVO tmp : listaTickets) {
            if(tmp.getFechaInicio().isAfter(fecha1) && tmp.getFechaFin().isAfter(fecha2) && tmp.getTiempoInicio().isBefore(tiempo1) && tmp.getTiempoFin().isAfter(tiempo2)){
                contador++;
                precio+=tmp.getPrecioFin();
            }
        }
        System.out.println("Se han realizado "+contador+" cobros y se ha recogido "+precio+" euros");
        
    }
    
    public static void abonosAnual() throws SQLException{
        AbonadosDAO p1=new AbonadosDAO();
        ArrayList<AbonadosVO> listaAbonados=new ArrayList<>();
        listaAbonados=p1.getAll();
        Double precioTotal=0.0;
        
        System.out.println("Abonados este año");
        System.out.println("---------------------------------------------");
        for (AbonadosVO tmp : listaAbonados) {
            if(tmp.getFechaInicioAbono().getYear()==LocalDate.now().getYear()){
                System.out.println(tmp);
                int tipoAbono=tmp.getTipoAbonados();
                switch (tipoAbono) {
                    case 1:
                        precioTotal+=25;
                        break;
                        
                    case 2:
                        precioTotal+=70;
                        break;
                        
                    case 3:
                        precioTotal+=130;
                        break;
                        
                    case 4:
                        precioTotal+=200;
                        break;
                    default:
                        System.out.println("Tipo de abono incorrecto");
                }
            }
        }
        System.out.println("Total recogido de abonos: "+precioTotal);
    }
    
    public static void pedirFechas() throws SQLException{
        int dia1, dia2, mes1, mes2, anio1, anio2, hora1, hora2, min1, min2;
        Scanner teclado=new Scanner(System.in);
        
        System.out.println("Introduce el día de la primera fecha: ");
        dia1=teclado.nextInt();
                                                
        System.out.println("Introduce el més de la primera fecha: ");
        mes1=teclado.nextInt();
                                                
        System.out.println("Introduce el año de la primera fecha: ");
        anio1=teclado.nextInt();
                                                
        System.out.println("Introduce la hora de la primera fecha: ");
        hora1=teclado.nextInt();
                                                
        System.out.println("Introduce el minuto de la primera fecha: ");
        min1=teclado.nextInt();
        
        LocalDate fecha1=LocalDate.of(anio1, mes1, dia1);
        LocalTime tiempo1=LocalTime.of(hora1, min1);
        
        System.out.println("Introduce el día de la segunda fecha: ");
        dia2=teclado.nextInt();
                                                
        System.out.println("Introduce el més de la segunda fecha: ");
        mes2=teclado.nextInt();
                                                
        System.out.println("Introduce el año de la segunda fecha: ");
        anio2=teclado.nextInt();
                                                
        System.out.println("Introduce la hora de la segunda fecha: ");
        hora2=teclado.nextInt();
                                                
        System.out.println("Introduce el minuto de la segunda fecha: ");
        min2=teclado.nextInt();
        
        LocalDate fecha2=LocalDate.of(anio2, mes2, dia2);
        LocalTime tiempo2=LocalTime.of(hora2, min2);
        
        PrecioEntreDosFechas(fecha1, fecha2, tiempo1, tiempo2);
    }
    
    public static void estadoPlazas() throws SQLException{
        PlazasDAO p1=new PlazasDAO();
        ArrayList<PlazasVO> listaPlazas=new ArrayList<>();
        
        listaPlazas=p1.getAll();
        for (PlazasVO tmp : listaPlazas) {
            System.out.print(tmp.getCodigoPlaza()+" - ");
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
    
    public static void actualizardatos(AbonadosVO abonado){
        Scanner teclado = new Scanner(System.in);
        AbonadosDAO A1=new AbonadosDAO();
        int tipoAbonados;
        do{
        System.out.println("Tipo de abono que desa actualizar");
        tipoAbonados = teclado.nextInt();
        switch (tipoAbonados) {
            case 1:
               abonado.getFechaFinAbono().plusMonths(1);
                break;
            case 2:
               abonado.getFechaFinAbono().plusMonths(3);
                break;
            case 3:
                 abonado.getFechaFinAbono().plusMonths(4);
                break;
            case 4:
                 abonado.getFechaFinAbono().plusYears(1);
                break;
        }
        }while(tipoAbonados<5 && tipoAbonados>0);
        
        try {
            A1.updateAbonados(abonado.getDNI(), abonado);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void eliminarAbonado(AbonadosVO abonado){
        AbonadosDAO A1=new AbonadosDAO();
        AbonadosVO eliminacion = new AbonadosVO(abonado.getDNI(),"","",abonado.getTipoAbonados(),abonado.getTarjetaCredito(),"",5,abonado.getMatricula(),abonado.getFechaInicioAbono());
        try {
            A1.updateAbonados(abonado.getDNI(), eliminacion);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<AbonadosVO> mesQueCaduca(int mes) throws SQLException{
        AbonadosDAO a1=new AbonadosDAO();
        ArrayList<AbonadosVO> listaAbonados=new ArrayList<>();
        ArrayList<AbonadosVO> caducaEsteMes = new ArrayList<>();
        
        listaAbonados=a1.getAll();
        for (AbonadosVO abonadostodo : listaAbonados) {
            if(mes==abonadostodo.getFechaFinAbono().getMonth().getValue() ){
                caducaEsteMes.add(abonadostodo);
            }
        } 
        return caducaEsteMes;
    }
    
    public static ArrayList<AbonadosVO> caduca10Dias() throws SQLException{
        AbonadosDAO a1=new AbonadosDAO();
        ArrayList<AbonadosVO> listaAbonados=new ArrayList<>();
        ArrayList<AbonadosVO> caduca10Dias = new ArrayList<>();
        
        listaAbonados=a1.getAll();
        for (AbonadosVO tmp : listaAbonados) {
            if(tmp.getFechaFinAbono().getDayOfYear()>LocalDate.now().getDayOfYear() && tmp.getFechaFinAbono().getDayOfYear()>LocalDate.now().plusDays(10).getDayOfYear()){
                caduca10Dias.add(tmp);
            }
        } 
        return caduca10Dias;
    }
}
 