/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;

import java.util.Scanner;
import plazas.PlazasVO;

/**
 *
 * @author javi
 */
public class MetodosVehiculos {
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
                    if(PlazasVO.NUMEROPLAZAS_TURISMO==0){
                        TipoVehiculos=0;
                        System.out.println("No hay plazas libres de turismos");
                        break;
                    }else{
                    TipoVehiculos=1;
                    break;
                    }

                case 2:
                     if(PlazasVO.NUMEROPLAZAS_MOTOCICLETA==0){
                        TipoVehiculos=0;
                        System.out.println("No hay plazas libres de motocicletas");
                        break;
                    }else{
                    TipoVehiculos=2;
                    break;
                     }
                case 3:
                     if(PlazasVO.NUMEROPLAZAS_CARAVANA==0){
                        TipoVehiculos=0;
                        System.out.println("No hay plazas libres de caravanas");
                        break;
                    }else{
                    TipoVehiculos=3;
                    break;
                     }
                default:
                    TipoVehiculos=0;
            }
        }while(TipoVehiculos==0);
        return new VehiculoVO(matricula,TipoVehiculos);
    }
}
