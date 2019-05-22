/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParkingDAW;

import Abonados.AbonadosVO;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author javi
 */
public class ParkingCliente {
    public static void main(String[] args) {
        int Elecion1,Elecion2,Elecion3;
        ArrayList<AbonadosVO> listaAbonados = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        do{
        
        System.out.println("1.Si es abonado de nuestro Parking");
        System.out.println("2.No soy abonado y deseo insertar mi vehiculo");
        System.out.println("3.No soy abonado y deseo serlo");
         Elecion1 = teclado.nextInt();
        switch (Elecion1) {
            case 1:
                do{
                System.out.println("1.Deseo insertar mi vehiculo");
                System.out.println("2.Deseo retirar mi vehiculo");
                Elecion2 = teclado.nextInt();
                switch (Elecion2) {
                    case 1:
                        
                        break;
                     case 2:
                        
                        break;
                    default:
                        Elecion2=0;
                }
                }while(Elecion2==0);
                break;
                case 2:
                 do{
                System.out.println("1.Deseo insertar mi vehiculo");
                System.out.println("2.Deseo retirar mi vehiculo");
                Elecion3 = teclado.nextInt();
                switch (Elecion3) {
                    case 1:
                        
                        break;
                     case 2:
                        
                        break;
                    default:
                        Elecion3=0;
                }
                }while(Elecion3==0);
                break;
                case 3:
                    listaAbonados.add(MetodosCliente.datos());
                break;
            default:
               Elecion1=0;
        }
        }while(Elecion1==0);
    }
}
