/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParkingDAW;

import Abonados.AbonadosVO;
import Abonados.MetodosAbonados;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author javi
 */
public class ParkingCliente {

    public static void main(String[] args) {
        int Eleccion1, Eleccion2, Eleccion3, Eleccion4;
        ArrayList<AbonadosVO> listaAbonados = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("1. Cliente");
            System.out.println("2. Admin");

            Eleccion1 = teclado.nextInt();
            switch (Eleccion1) {

                case 1:
                    System.out.println("1. Si soy abonado de nuestro Parking");
                    System.out.println("2.No soy abonado y deseo insertar o retirar mi vehiculo");
                    System.out.println("3.No soy abonado y deseo serlo");
                    Eleccion2 = teclado.nextInt();
                    switch (Eleccion2) {
                        case 1:
                            System.out.println("Introduce tu dni");
                            teclado.nextLine();
                            String dni = teclado.nextLine();

                            if (MetodosAbonados.comprobarAbonado(listaAbonados, dni)) {
                                do {
                                    System.out.println("1.Deseo insertar mi vehiculo");
                                    System.out.println("2.Deseo retirar mi vehiculo");
                                    Eleccion3 = teclado.nextInt();
                                    switch (Eleccion3) {
                                        case 1:

                                            break;
                                        case 2:

                                            break;
                                        default:
                                            Eleccion3 = 0;
                                    }
                                } while (Eleccion3 == 0);
                            }
                            break;
                        case 2:
                            do {
                                System.out.println("1.Deseo insertar mi vehiculo");
                                System.out.println("2.Deseo retirar mi vehiculo");
                                Eleccion4 = teclado.nextInt();
                                switch (Eleccion4) {
                                    case 1:

                                        break;
                                    case 2:

                                        break;
                                    default:
                                        Eleccion4 = 0;
                                }
                            } while (Eleccion4 == 0);
                            break;
                        case 3:
                            listaAbonados.add(MetodosAbonados.insertarDatos());
                            break;
                        default:
                            Eleccion2 = 0;
                    }
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
                                Eleccion2=0;
                        }
                    }while(Eleccion2==0);
            }
        } while (Eleccion1 == 0);
    }
}
