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
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import plazas.PlazasDAO;
import plazas.PlazasVO;
import vehiculos.VehiculoDAO;
import vehiculos.VehiculoVO;

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
                                                
        System.out.println("Introduce el mes de la primera fecha: ");
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
                                                
        System.out.println("Introduce el mes de la segunda fecha: ");
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
    
    private static void modificarAbonado(String dni) throws SQLException {
        AbonadosDAO daoAbonados = new AbonadosDAO();
        AbonadosVO a1=new AbonadosVO();
        VehiculoDAO v1=new VehiculoDAO();
        VehiculoVO v2=new VehiculoVO();
        
        Scanner teclado = new Scanner(System.in);
        Random rnd = new Random();
        
        a1=daoAbonados.findByPk(dni);
        
        v2=v1.findByPk(a1.getMatricula());
        
        String Pin="";
        int numero;
        for(int x=0;x<6;x++){
            numero=rnd.nextInt(10);
            Pin+=numero;
        }
        
        System.out.println("Nombre:");
        String Nombre=teclado.next();
        
        System.out.println("Apellidos:");
        String Apellidos=teclado.next();
        
        System.out.println("Tarjeta de credito:");
        String Tarjeta=teclado.next();
        
        System.out.println("email:");
        String Email=teclado.next();
        
        System.out.println("Que tipo de abonado quiere");
        System.out.println("1. Mensual");
        System.out.println("2. Trimestral");
        System.out.println("3. Semestral");
        System.out.println("4. Anual");
        int TipoAbonado;
        do{
            TipoAbonado=teclado.nextInt();
            switch (TipoAbonado) {
                case 1:
                    TipoAbonado=1;
                    break;

                case 2:
                    TipoAbonado=2;
                    break;

                case 3:
                    TipoAbonado=3;
                    break;

                case 4:
                    TipoAbonado=4;
                    break;
                default:
                    TipoAbonado=0;
            }
        }while(TipoAbonado==0);
        
        System.out.println("Introduce la matrícula de su coche: ");
        teclado.nextLine();
        String matricula=teclado.nextLine();
        
        VehiculoVO nuevoVehiculo=new VehiculoVO(matricula, v2.getTipoVehiculo());
        
        v1.updateVehiculo(v2.getMatricula(), nuevoVehiculo);
        
        AbonadosVO abonado=new AbonadosVO(dni,Nombre,Apellidos,Pin,Tarjeta,Email,TipoAbonado,matricula,a1.getFechaInicioAbono());
        try {
            daoAbonados.updateAbonados(dni,abonado);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void actualizardatos(String dni) throws SQLException{
        Scanner teclado = new Scanner(System.in);
        AbonadosDAO a1=new AbonadosDAO();
        AbonadosVO a2=new AbonadosVO();
        a2=a1.findByPk(dni);
        int tipoAbonados;
        
        if(a1.findByPk(dni)!=null){
            System.out.println("¿Desea actualizar todos los datos personales o renovar el abono?");
            System.out.println("1- Actualizar todos los datos");
            System.out.println("2- Renovar el abono");
            int opcion=teclado.nextInt();
            switch (opcion) {
                case 1:
                    modificarAbonado(dni);
                    break;
                case 2:
                    do{
                        System.out.println("Tipo de abono que desea actualizar");
                        tipoAbonados = teclado.nextInt();
                        switch (tipoAbonados) {
                            case 1:
                                a2.setFechaFinAbono(a2.getFechaFinAbono().plusMonths(1));
                                break;

                            case 2:
                                a2.setFechaFinAbono(a2.getFechaFinAbono().plusMonths(3));
                                break;

                            case 3:
                                a2.setFechaFinAbono(a2.getFechaFinAbono().plusMonths(6));
                                break;

                            case 4:
                                a2.setFechaFinAbono(a2.getFechaFinAbono().plusYears(1));
                                break;

                            default:
                                System.out.println("Introduce un tipo de abonado correcto");
                                tipoAbonados=0;
                                break;
                        }
                    }while(tipoAbonados==0);

                    try {
                        a1.updateAbonados(a2.getDNI(), a2);
                    } catch (SQLException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
        }
    }
    
    public static void eliminarAbonado(String dni) throws SQLException{
        AbonadosDAO a1=new AbonadosDAO();
        AbonadosVO a2= new AbonadosVO();
        VehiculoDAO v1=new VehiculoDAO();
        VehiculoVO v2=new VehiculoVO();
        PlazasDAO p1=new PlazasDAO();
        PlazasVO p2=new PlazasVO();
        
        try {
            if(a1.findByPk(dni)!=null){
                
                a2=a1.findByPk(dni);
                v2=v1.findByPk(a2.getMatricula());
                p2=p1.findByFk(a2.getMatricula());
                
                PlazasVO nuevaPlaza=new PlazasVO();
                nuevaPlaza.setEstado(2);
                nuevaPlaza.setMatricula(null);
                
                a2.setDNI("------");
                a2.setNombre("------");
                a2.setApellidos("------");
                a2.setEmail("------");
                a2.setPinAbonados("------");
                a2.setTarjetaCredito("------");
                a2.setMatricula("------");
                
                a1.updateAbonados(dni, a2);
                v1.deleteVehiculo(v2);
                p1.updatePlazas(p2.getCodigoPlaza(), nuevaPlaza);
            }
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
 