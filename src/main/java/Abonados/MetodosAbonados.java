/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abonados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import plazas.PlazasVO;
import vehiculos.VehiculoVO;

/**
 *
 * @author jriosaguilar
 */
public class MetodosAbonados {
    
    //Método para comprobar si el abonado está dentro de la lista de abonados
    public static boolean comprobarAbonado(ArrayList<AbonadosVO> abonados, String dni){
        for (AbonadosVO tmp : abonados) {
            if(tmp.getDNI().equalsIgnoreCase(dni)){
                return true;
            }       
        }
        return false;
    }
    
    public static AbonadosVO insertarDatos(){
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
        
        System.out.println("matricula de su coche:");
        String Matricula=teclado.next();
        
        return new AbonadosVO(DNI,Nombre,Apellidos,Pin,Tarjeta,Email,TipoAbonado,Matricula,LocalDate.now());
    }
    
    public static VehiculoVO datosVehiculos(){
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
        return new VehiculoVO(matricula,TipoVehiculos);
    }
    
    public int numTotalPlazas() {
        
        return(PlazasVO.NUMEROPLAZAS_CARAVANA +PlazasVO.NUMEROPLAZAS_MOTOCICLETA
        + PlazasVO.NUMEROPLAZAS_TURISMO);
            
    }
    
}
