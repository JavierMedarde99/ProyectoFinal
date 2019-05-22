/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParkingDAW;

import Abonados.AbonadosVO;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author javi
 */
public class MetodosCliente {
    public static AbonadosVO datos(){
        Scanner teclado = new Scanner(System.in);
        Random rnd = new Random();
        int Pin = rnd.nextInt(111111-999999);
        System.out.println("DNI:");
        String DNI=teclado.next();
        System.out.println("Nombre:");
        String Nombre=teclado.next();
        System.out.println("Apellidos:");
        String Apellidos=teclado.next();
        System.out.println("Tarjeta de credito:");
        String Tarjeta=teclado.next();
        System.out.println("email:");
        String Email=teclado.next();
        System.out.println("Que tipo de abonado quiere");
        System.out.println("1.anual");
        System.out.println("2.trimestral");
        System.out.println("3.mensual");
        System.out.println("4.semanal");
        int TipoAbonado=teclado.nextInt();
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
                throw new AssertionError();
        }
        System.out.println("matricula de su coche:");
        String Matricula=teclado.next();
        return new AbonadosVO(DNI,Nombre,Apellidos,Pin,Tarjeta,Email,TipoAbonado,Matricula,LocalDate.now());
    }
}
