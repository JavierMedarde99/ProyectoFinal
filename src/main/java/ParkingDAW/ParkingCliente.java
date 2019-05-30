/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParkingDAW;

import Abonados.AbonadosDAO;
import Abonados.AbonadosVO;
import Abonados.MetodosAbonados;
import Tickets.MetodosTickets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import vehiculos.MetodosVehiculos;
import vehiculos.VehiculoDAO;
import vehiculos.VehiculoVO;
import Admin.Admin;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author javi
 */
public class ParkingCliente {

    public static void main(String[] args) throws SQLException {
        System.out.println("1----------------");
        int Eleccion1, Eleccion2, Eleccion3, Eleccion4, repetir, tipo, codPlaza, pin, mes;
        System.out.println("2----------------");
        String dni, matricula;
        System.out.println("3-----------------");
        AbonadosDAO a1=new AbonadosDAO();
        System.out.println("4-----------------");
        VehiculoDAO v1=new VehiculoDAO();
        System.out.println("5-----------------");
        ArrayList<AbonadosVO> listaAbonados = new ArrayList<>();
        System.out.println("6-----------------");
        Scanner teclado = new Scanner(System.in);
        System.out.println("7-----------------");
        
        do {
            System.out.println("1. Cliente");
            System.out.println("2. Administrador");
            Eleccion1 = teclado.nextInt();
            
            switch (Eleccion1) {
                case 1:
                    do{
                        System.out.println("1. Depositar vehículo");
                        System.out.println("2. Retirar vehículo");
                        System.out.println("3. Añadir abonado");
                        System.out.println("4. Retirar abonado");
                        Eleccion2 = teclado.nextInt();
                        
                        switch (Eleccion2) {
                            case 1:
                                do{
                                    System.out.println("¿Es usted abonado?");
                                    System.out.println("1. Sí");
                                    System.out.println("2. No");
                                    Eleccion3=teclado.nextInt();
                                    
                                    switch (Eleccion3) {
                                        case 1:
                                            System.out.println("Introduce tu dni: ");
                                            teclado.nextLine();
                                            dni=teclado.nextLine();
                                            
                                            if(MetodosAbonados.comprobarAbonado(dni)==true){
                                                System.out.println("Introduce la matricula del vehículo: ");
                                                matricula=teclado.nextLine();
                                                System.out.println("Introduce el pin para retirar el vehículo: ");
                                                pin=teclado.nextInt();
                                                MetodosVehiculos.depositarVehiculoAbonado(dni, matricula, pin);
                                            };
                                            break;
                                            
                                        case 2:
                                            System.out.println("Introduce una matrícula");
                                            teclado.nextLine();
                                            matricula=teclado.nextLine();
                                            System.out.println("Introduce un tipo de vehículo");
                                            System.out.println("1. Turismo");
                                            System.out.println("2. Motocicleta");
                                            System.out.println("3. Caravana");
                                            tipo=teclado.nextInt();
                                            MetodosVehiculos.depositarVehiculo(matricula, tipo);
                                            break;
                                            
                                        default:
                                            System.out.println("Introduce una opción válida");
                                            Eleccion3=0;
                                            break;
                                    } 
                                }while(Eleccion3==0);

                            case 2:
                                do{
                                    System.out.println("¿Es usted abonado?");
                                    System.out.println("1. Sí");
                                    System.out.println("2. No");
                                    Eleccion3=teclado.nextInt();
                                    switch (Eleccion3) {
                                        case 1:
                                            System.out.println("Introduce tu dni: ");
                                            teclado.nextLine();
                                            dni=teclado.nextLine();
                                            
                                            if(MetodosAbonados.comprobarAbonado(dni)==true){
                                                System.out.println("Introduce la matrícula del vehículo: ");
                                                matricula=teclado.nextLine();
                                                System.out.println("Introduce el pin: ");
                                                pin=teclado.nextInt();
                                                MetodosVehiculos.retirarVehiculoAbonado(dni, matricula, pin);
                                            }
                                            break;
                                            
                                        case 2:
                                            System.out.println("Introduce tu dni: ");
                                            teclado.nextLine();
                                            dni=teclado.nextLine();
                                            System.out.println("Introduce el código de la plaza: ");
                                            codPlaza=teclado.nextInt();
                                            System.out.println("Introduce pin: ");
                                            pin=teclado.nextInt();
                                            
                                            MetodosVehiculos.retirarVehiculo(dni, codPlaza, pin);
                                            break;
                                            
                                        default:
                                            System.out.println("Introduce una opción válida");
                                            Eleccion3=0;
                                            break;
                                    } 
                                }while(Eleccion3==0);

                            case 3:
                                System.out.println("Introduce un DNI: ");
                                teclado.nextLine();
                                dni=teclado.nextLine();
                                System.out.println("Introduce una matricula: ");
                                matricula=teclado.nextLine();
                                System.out.println("Introduce el pin: ");
                                pin=teclado.nextInt();
                                MetodosVehiculos.depositarVehiculoAbonado(dni, matricula, pin);
                                break;

                            case 4:
                                System.out.println("Introduce un DNI: ");
                                teclado.nextLine();
                                dni=teclado.nextLine();
                                System.out.println("Introduce una matricula: ");
                                matricula=teclado.nextLine();
                                System.out.println("Introduce el pin: ");
                                pin=teclado.nextInt();
                                MetodosVehiculos.retirarVehiculoAbonado(dni, matricula, pin);
                                break;

                            default:
                                System.out.println("Introduce una opción válida");
                                Eleccion2=0;
                        }
                    }while(Eleccion2==0);
                case 2:
                    do{
                        System.out.println("1. Ver estado del parking");
                        System.out.println("2. Facturación");
                        System.out.println("3. Abonos");
                        System.out.println("4. Copia de seguridad");
                        Eleccion2=teclado.nextInt();
                        switch (Eleccion2) {
                            case 1:
                                Admin.estadoPlazas();
                                break;
                                
                            case 2:
                                do{
                                    System.out.println("Elige un método de facturación: ");
                                    System.out.println("1. Entre fechas");
                                    System.out.println("2. Abonados");
                                    Eleccion3=teclado.nextInt();
                                    switch (Eleccion3) {
                                            case 1:
                                                Admin.PrecioEntreDosFechas(LocalDate.of(2015, Month.DECEMBER, 5), LocalDate.of(2019, Month.MARCH, 8), LocalTime.of(00, 0), LocalTime.of(23, 59));
                                                break;

                                            case 2:
                                                Admin.abonosAnual();
                                                break;
                                            default:
                                                Eleccion3=0;
                                    }
                                }while(Eleccion3==0);
                                break;
                                
                            case 3:
                                do{
                                    System.out.println("Elige una opción");
                                    System.out.println("1. Dar de alta un abonado");
                                    System.out.println("2. Modificar un abonado");
                                    System.out.println("3. Eliminar un abonado");
                                    System.out.println("4.Caducidad de abonos");
                                    Eleccion4=teclado.nextInt();

                                    switch (Eleccion4) {
                                        case 1:
                                            System.out.println("Introduce un número de mes: ");
                                            mes=teclado.nextInt();
                                            ArrayList<AbonadosVO> listaMes=Admin.mesQueCaduca(mes);
                                            System.out.println("Abonados que caducan este mes: ");
                                            listaMes.forEach(System.out::println);
                                            break;

                                        case 2:
                                            ArrayList<AbonadosVO> lista10Dias=Admin.caduca10Dias();
                                            System.out.println("Abonados que caducan en 10 días: ");
                                            lista10Dias.forEach(System.out::println);
                                            break;

                                        default:
                                            Eleccion4=0;
                                    }
                                }while(Eleccion4==0);
                                break;
                            case 4:
                                
                                break;
                            default:
                                System.out.println("Introduce una opción válida");
                                Eleccion2=0;
                        }
                    }while(Eleccion2==0);
                default:
                    System.out.println("Introduce una opción válida");
                    Eleccion1=0;
            }
        } while (Eleccion1 == 0);
    }
}
