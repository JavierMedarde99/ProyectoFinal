/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;

import Abonados.AbonadosDAO;
import Abonados.AbonadosVO;
import Tickets.MetodosTickets;
import Tickets.TicketsDAO;
import Tickets.TicketsVO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import plazas.PlazasDAO;
import plazas.PlazasVO;

/**
 *
 * @author javi
 */
public class MetodosVehiculos { 
    
    public static VehiculoVO datosVehiculos(){
        VehiculoDAO daoVehiculo = new VehiculoDAO();
       
        int TipoVehiculos;
        String matricula;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Inserte la matricula");
        matricula = teclado.nextLine();
        System.out.println("Introduce el tipo de vehiculo");
        System.out.println("1.si es turismo");
        System.out.println("2.si es motocicleta");
        System.out.println("3.si es Caravanas");
        
        do{
            TipoVehiculos=teclado.nextInt();
            switch (TipoVehiculos) {
                case 1:
                    TipoVehiculos=1;
                    break;
                case 2:                     
                    TipoVehiculos=2;
                    break;                     
                case 3:                 
                    TipoVehiculos=3;
                    break;
                default:
                    TipoVehiculos=0;
            }
        }while(TipoVehiculos==0);
        VehiculoVO vehiculo=new VehiculoVO(matricula,TipoVehiculos);
        try {
            daoVehiculo.insertVehiculo(vehiculo);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehiculo;
    }
    
    public static void EliminarVehiculo(String matricula){
        try {
            VehiculoDAO daoVehiculo = new VehiculoDAO();
            VehiculoVO vehiculo =daoVehiculo.findByPk(matricula);
            daoVehiculo.deleteVehiculo(vehiculo);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void depositarVehiculo(String matricula, int tipo) throws SQLException{
        PlazasDAO p=new PlazasDAO();
        ArrayList<PlazasVO> listaPlazas=new ArrayList<>();
        ArrayList<PlazasVO> listaPlazasLibres=new ArrayList<>();
        ArrayList<PlazasVO> listaTurismo=new ArrayList<>();
        ArrayList<PlazasVO> listaMotocicleta=new ArrayList<>();
        ArrayList<PlazasVO> listaCaravana=new ArrayList<>();
        listaPlazas=p.getAll();
        
        for (PlazasVO tmp : listaPlazas) {
            if(tmp.getEstado()==2){
                listaPlazasLibres.add(tmp);
            }
        }
        
        System.out.println("Plazas libres: ");
        listaPlazasLibres.forEach(System.out::println);
        
        switch (tipo) {
            case 1:
                for (PlazasVO tmp : listaPlazasLibres) {
                    if(tmp.getCodigoPlaza()>100 && tmp.getCodigoPlaza()<=115){
                        listaTurismo.add(tmp);
                    }
                }
                
                if(!listaTurismo.isEmpty()){
                    System.out.println("Plaza: "+listaTurismo.get(0));
                    p.updatePlazas(listaTurismo.get(0).getCodigoPlaza(), new PlazasVO(matricula,1));
                    MetodosTickets.crearTicket(matricula, listaTurismo.get(0).getCodigoPlaza(), tipo);
                }
                break;
                
            case 2:
                for (PlazasVO tmp : listaPlazasLibres) {
                    if(tmp.getCodigoPlaza()>200 && tmp.getCodigoPlaza()<=215){
                        listaMotocicleta.add(tmp);
                    }
                }
                
                if(!listaMotocicleta.isEmpty()){
                    System.out.println("Plaza: "+listaMotocicleta.get(0));
                    p.updatePlazas(listaMotocicleta.get(0).getCodigoPlaza(), new PlazasVO(matricula,1));
                    MetodosTickets.crearTicket(matricula, listaMotocicleta.get(0).getCodigoPlaza(), tipo);
                    
                }
                break;
                
            case 3:
                for (PlazasVO tmp : listaPlazasLibres) {
                    if(tmp.getCodigoPlaza()>300 && tmp.getCodigoPlaza()<=315){
                        listaCaravana.add(tmp);
                    }
                }
                
                if(!listaCaravana.isEmpty()){
                    System.out.println("Plaza: "+listaCaravana.get(0));
                    p.updatePlazas(listaCaravana.get(0).getCodigoPlaza(), new PlazasVO(matricula,1));
                    MetodosTickets.crearTicket(matricula, listaCaravana.get(0).getCodigoPlaza(), tipo);
                }
                break;
            default:
                System.out.println("Tipo de vehículo incorrecto");
        }
    }
    
    public static void depositarVehiculoAbonado(String dni, String matricula, int pin) throws SQLException{
        AbonadosDAO a1=new AbonadosDAO();
        AbonadosVO a2=new AbonadosVO();
        PlazasDAO p1=new PlazasDAO();
        PlazasVO p2=new PlazasVO();
        
        a2=a1.findByPk(dni);
        
        if(matricula.equalsIgnoreCase(a2.getMatricula()) && pin==a2.getPinAbonados()){
            if(p1.findByFk(matricula)!=null){
                p2=p1.findByFk(matricula);
                p1.updatePlazas(p2.getCodigoPlaza(), new PlazasVO(matricula, 3));
                escribirFicheroPin(a2);
            }
        }
    }
    
    public static void retirarVehiculo(String matricula, int codPlaza, int pin) throws SQLException{
        PlazasDAO p1=new PlazasDAO();
        PlazasVO p2=new PlazasVO();
        TicketsDAO t1=new TicketsDAO();
        TicketsVO t2=new TicketsVO();
        VehiculoDAO v1=new VehiculoDAO();
        VehiculoVO v2=new VehiculoVO();
        
        if(p2.getCodigoPlaza()==codPlaza && p2.getMatricula().equalsIgnoreCase(matricula) && p1.findByPk(codPlaza)!=null){
            p2=p1.findByPk(codPlaza);
            if(t1.findByPk(matricula, codPlaza)!=null){
                t2=t1.findByPk(matricula, codPlaza);
                if(t2.getPin()==pin){
                    v2=v1.findByPk(matricula);
                    Double precioMin=MetodosTickets.calcularPrecioMinuto(v2);
                    long tiempo=MetodosTickets.retirarTicket(t2.getTiempoInicio(), t2.getFechaInicio());
                    t1.updateTickets(codPlaza, matricula, new TicketsVO(codPlaza, matricula, pin, precioMin*tiempo, precioMin, t2.getFechaInicio(), t2.getTiempoInicio(), LocalDate.now(), LocalTime.now()));
                    p1.updatePlazas(codPlaza, new PlazasVO("", 2));
                }   
            }
        }
    }
    
    public static void retirarVehiculoAbonado(String dni, String matricula, int pin) throws SQLException{
        PlazasDAO p1=new PlazasDAO();
        PlazasVO p2=new PlazasVO();
        AbonadosDAO a1=new AbonadosDAO();
        AbonadosVO a2=new AbonadosVO();
        
        a2=a1.findByPk(dni);
        
        if(p1.findByFk(matricula)!=null && a2.getMatricula().equalsIgnoreCase(matricula) && pin==a2.getPinAbonados()){
            p1.updatePlazas(p2.getCodigoPlaza(), new PlazasVO(p2.getMatricula(), 4));
        }
        
    }
    
    public static void escribirFicheroPin(AbonadosVO abonado){
        //Creamos el directorio
            Path directory = Paths.get("./pinAbonados");
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                System.out.println("Problema creando el directorio pinAbonados.");
                System.out.println(e.toString());
            }
            
        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
	String fichero="pinAbonados/"+abonado.getDNI()+".txt";
        
        // Estructura try-with-resources. Instancia el objeto con el fichero a escribir
        // y se encarga de cerrar el recurso "flujo" una vez finalizadas las operaciones
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(fichero))){
            flujo.write(abonado.getPinAbonados());
            flujo.flush();
	} catch (IOException e) {
            System.out.println(e.getMessage());
	}
    }
}
