/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abonados;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import plazas.PlazasVO;

/**
 *
 * @author jriosaguilar
 */
public class MetodosAbonados {
    
    //Método para comprobar si el abonado está dentro de la lista de abonados
    public static boolean comprobarAbonado(String dni) throws SQLException{
        AbonadosDAO a1=new AbonadosDAO();
        AbonadosVO a2=new AbonadosVO();
        if(a1.findByPk(dni)!=null){
            return true;
        }
        return false;
    }
    
    public static AbonadosVO insertarDatos() {
        AbonadosDAO daoAbonados = new AbonadosDAO();
        
        Scanner teclado = new Scanner(System.in);
        Random rnd = new Random();
        
        int Pin = rnd.nextInt(111111-999999 + 1)+ 111110;
        
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
        AbonadosVO abonado=new AbonadosVO(DNI,Nombre,Apellidos,Pin,Tarjeta,Email,TipoAbonado,Matricula,LocalDate.now());
        try {
            daoAbonados.insertAbonados(abonado);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return abonado;
    }
    
   
    
    public int numTotalPlazas() {
        
        return(PlazasVO.NUMEROPLAZAS_CARAVANA +PlazasVO.NUMEROPLAZAS_MOTOCICLETA
        + PlazasVO.NUMEROPLAZAS_TURISMO);
            
    }
    
}
