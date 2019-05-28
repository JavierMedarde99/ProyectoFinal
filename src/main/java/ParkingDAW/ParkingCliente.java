/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParkingDAW;

import Abonados.AbonadosDAO;
import Abonados.AbonadosVO;
import Abonados.MetodosAbonados;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import vehiculos.VehiculoDAO;
import vehiculos.VehiculoVO;

/**
 *
 * @author javi
 */
public class ParkingCliente {

    public static void main(String[] args) throws SQLException {
        int Eleccion1, Eleccion2, Eleccion3, Eleccion4, repetir;
        AbonadosDAO a1=new AbonadosDAO();
        VehiculoDAO v1=new VehiculoDAO();
        ArrayList<AbonadosVO> listaAbonados = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
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

                                            break;
                                        case 2:
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

                                            break;
                                        case 2:
                                        default:
                                            System.out.println("Introduce una opción válida");
                                            Eleccion3=0;
                                            break;
                                    } 
                                }while(Eleccion3==0);

                            case 3:
                                break;

                            case 4:
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

                                break;
                            case 2:

                                break;
                            case 3:

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
            repetir=1;
            if(Eleccion1!=0){
                repetir=teclado.nextInt();
                do{
                    System.out.println("¿Quieres realizar otra acción?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                }while(repetir!=1 || repetir!=0);
            }
        } while (Eleccion1 == 0 || repetir==1);
    }
}
