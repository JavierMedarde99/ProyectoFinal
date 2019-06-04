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
import Tickets.TicketsDAO;
import Tickets.TicketsVO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import plazas.PlazasDAO;

/**
 *
 * @author javi
 */
public class ParkingCliente {
    
    public static void main(String[] args) throws SQLException {
        int Eleccion1, Eleccion2, Eleccion3, Eleccion4, Eleccion5, repetir, tipo, codPlaza, mes;
        String dni, matricula, pin;
        AbonadosDAO a1 = new AbonadosDAO();
        VehiculoDAO v1 = new VehiculoDAO();
        PlazasDAO p1 = new PlazasDAO();
        TicketsDAO t1 = new TicketsDAO();
        //ArrayList<AbonadosVO> listaAbonados = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        String opcionfinal;
        do {
            
            do {
                System.out.println("1. Cliente");
                System.out.println("2. Administrador");
                Eleccion1 = teclado.nextInt();
                
                switch (Eleccion1) {
                    case 1:
                        do {
                            System.out.println("1. Depositar vehículo");
                            System.out.println("2. Retirar vehículo");
                            Eleccion2 = teclado.nextInt();
                            
                            switch (Eleccion2) {
                                case 1:
                                    do {
                                        System.out.println("¿Es usted abonado?");
                                        System.out.println("1. Sí");
                                        System.out.println("2. No");
                                        Eleccion3 = teclado.nextInt();
                                        
                                        switch (Eleccion3) {
                                            case 1:
                                                
                                                System.out.println("Introduce tu dni: ");
                                                teclado.nextLine();
                                                dni = teclado.nextLine();
                                                
                                                if (MetodosAbonados.comprobarAbonado(dni) == true) {
                                                    System.out.println("Introduce la matricula del vehículo: ");
                                                    matricula = teclado.nextLine();
                                                    System.out.println("Introduce el pin para retirar el vehículo: ");
                                                    pin = teclado.nextLine();
                                                    MetodosVehiculos.meterVehiculoAbonado(dni, matricula, pin);
                                                }
                                                
                                                break;
                                            
                                            case 2:
                                                Admin.estadoPlazas();
                                                System.out.println("Introduce una matrícula");
                                                teclado.nextLine();
                                                matricula = teclado.nextLine();
                                                System.out.println("Introduce un tipo de vehículo");
                                                System.out.println("1. Turismo");
                                                System.out.println("2. Motocicleta");
                                                System.out.println("3. Caravana");
                                                tipo = teclado.nextInt();
                                                TicketsVO mostrarTicket = MetodosVehiculos.meterVehiculoNoAbonado(matricula, tipo);
                                                System.out.println(mostrarTicket.toStringInicial());
                                                break;
                                            
                                            default:
                                                System.out.println("Introduce una opción válida");
                                                Eleccion3 = 0;
                                                break;
                                        }
                                    } while (Eleccion3 == 0);
                                    break;
                                case 2:
                                    do {
                                        System.out.println("¿Es usted abonado?");
                                        System.out.println("1. Sí");
                                        System.out.println("2. No");
                                        Eleccion3 = teclado.nextInt();
                                        switch (Eleccion3) {
                                            case 1:
                                                System.out.println("Introduce tu dni: ");
                                                teclado.nextLine();
                                                dni = teclado.nextLine();
                                                
                                                if (MetodosAbonados.comprobarAbonado(dni) == true) {
                                                    System.out.println("Introduce la matrícula del vehículo: ");
                                                    matricula = teclado.nextLine();
                                                    System.out.println("Introduce el pin: ");
                                                    pin = teclado.nextLine();
                                                    MetodosVehiculos.sacarVehiculoAbonado(dni, matricula, pin);
                                                }
                                                break;
                                            
                                            case 2:
                                                System.out.println("Introduce tu Matricula: ");
                                                teclado.nextLine();
                                                dni = teclado.nextLine();
                                                System.out.println("Introduce el código de la plaza: ");
                                                codPlaza = teclado.nextInt();
                                                System.out.println("Introduce pin: ");
                                                teclado.nextLine();
                                                pin = teclado.nextLine();
                                                
                                                TicketsVO retirar = MetodosVehiculos.retirarVehiculoNoAbonado(dni, codPlaza, pin);
                                                System.out.println(retirar.toString());
                                                break;
                                            
                                            default:
                                                System.out.println("Introduce una opción válida");
                                                Eleccion3 = 0;
                                                break;
                                        }
                                    } while (Eleccion3 == 0);
                                    break;
                                
                                default:
                                    System.out.println("Introduce una opción válida");
                                    Eleccion2 = 0;
                                
                            }
                        } while (Eleccion2 == 0);
                        break;
                    case 2:
                        do {
                            
                            System.out.println("1. Facturación");
                            System.out.println("2. Abonos");
                            System.out.println("3. Copia de seguridad");
                            System.out.println("4. Estado parking");
                            Eleccion2 = teclado.nextInt();
                            switch (Eleccion2) {
                                
                                case 1:
                                    do {
                                        System.out.println("Elige un método de facturación: ");
                                        System.out.println("1. Entre fechas");
                                        System.out.println("2. Abonados");
                                        Eleccion3 = teclado.nextInt();
                                        switch (Eleccion3) {
                                            case 1:
                                                Admin.pedirFechas();
                                                break;
                                            
                                            case 2:
                                                Admin.abonosAnual();
                                                break;
                                            default:
                                                Eleccion3 = 0;
                                        }
                                    } while (Eleccion3 == 0);
                                    break;
                                
                                case 2:
                                    do {
                                        System.out.println("Elige una opción");
                                        System.out.println("1. Dar de alta un abonado");
                                        System.out.println("2. Modificar un abonado");
                                        System.out.println("3. Eliminar un abonado");
                                        System.out.println("4. Caducidad de abonos");
                                        Eleccion4 = teclado.nextInt();
                                        
                                        switch (Eleccion4) {
                                            case 1:
                                                AbonadosVO fichero = MetodosAbonados.insertarDatos();
                                                MetodosVehiculos.escribirFicheroPin(fichero);
                                                break;
                                            
                                            case 2:
                                                System.out.println("Introduce tu dni: ");
                                                teclado.nextLine();
                                                dni = teclado.nextLine();
                                                Admin.actualizardatos(dni);
                                                break;
                                            
                                            case 3:
                                                System.out.println("Introduce tu dni: ");
                                                teclado.nextLine();
                                                dni = teclado.nextLine();
                                                Admin.eliminarAbonado(dni);
                                                break;
                                            
                                            case 4:
                                                do {
                                                    System.out.println("1. Caducidad en un mes");
                                                    System.out.println("2. Caducidad en 10 días");
                                                    Eleccion5 = teclado.nextInt();
                                                    
                                                    switch (Eleccion5) {
                                                        case 1:
                                                            System.out.println("Introduce un número de mes: ");
                                                            mes = teclado.nextInt();
                                                            ArrayList<AbonadosVO> listaMes = Admin.mesQueCaduca(mes);
                                                            System.out.println("Abonados que caducan este mes: ");
                                                            listaMes.forEach(System.out::println);
                                                            break;
                                                        
                                                        case 2:
                                                            ArrayList<AbonadosVO> lista10Dias = Admin.caduca10Dias();
                                                            System.out.println("Abonados que caducan en 10 días: ");
                                                            lista10Dias.forEach(System.out::println);
                                                            break;
                                                        
                                                        default:
                                                            Eleccion5 = 0;
                                                            break;
                                                    }
                                                } while (Eleccion5 == 0);
                                                break;
                                            
                                            default:
                                                Eleccion4 = 0;
                                        }
                                    } while (Eleccion4 == 0);
                                    break;
                                case 3:
                            {
                                int EleccionCS;
                                do{
                                    System.out.println("Elige una opción");
                                    System.out.println("1. Hacer la copia de seguridad ahora");
                                    System.out.println("2. Restablecer la copia de seguridad");
                                     EleccionCS = teclado.nextInt();
                                    switch (EleccionCS) {
                                        case 1:
                                            MetodosVehiculos.CopiaSeguridadAbonados(a1.getAll());
                                            MetodosVehiculos.CopiaSeguridadPlazas(p1.getAll());
                                            MetodosVehiculos.CopiaSeguridadTickets(t1.getAll());
                                            MetodosVehiculos.CopiaSeguridadVehiculos(v1.getAll());
                                            break;
                                        case 2:
                                            break;
                                            
                                        default:
                                            EleccionCS=0;
                                    }
                                    
                                }while(EleccionCS==0);
                            }
                                case 4:
                                    Admin.estadoPlazas();
                                    break;
                                default:
                                    System.out.println("Introduce una opción válida");
                                    Eleccion2 = 0;
                            }
                        } while (Eleccion2 == 0);
                        break;
                    
                    default:
                        System.out.println("Introduce una opción válida");
                        Eleccion1 = 0;
                        break;
                    
                }
            } while (Eleccion1 == 0);
            System.out.println("¿Quiere hacer otra operacion?");
            opcionfinal = teclado.next();
            
        } while ("si".equalsIgnoreCase(opcionfinal));
    }
}
