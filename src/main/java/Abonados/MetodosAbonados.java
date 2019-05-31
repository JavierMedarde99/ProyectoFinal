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
import plazas.PlazasDAO;
import plazas.PlazasVO;
import vehiculos.MetodosVehiculos;
import vehiculos.VehiculoDAO;
import vehiculos.VehiculoVO;

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
    
    public static void insertarDatos() {
        AbonadosDAO daoAbonados = new AbonadosDAO();
        MetodosVehiculos.datosVehiculos();
        
        Scanner teclado = new Scanner(System.in);
        Random rnd = new Random();
        
        String Pin="";
        int numero;
        for(int x=0;x<6;x++){
            numero=rnd.nextInt(10);
            Pin+=numero;
        }
        
        String DNI="";
        do{
        System.out.println("DNI:");
            DNI=teclado.next();
        }while(DNI.length()!=9); 
        
        System.out.println("Nombre:");
        String Nombre=teclado.next();
        
        System.out.println("Apellidos:");
        String Apellidos=teclado.next();
        
        String Tarjeta;
        do{
            System.out.println("Tarjeta de credito:");
            Tarjeta=teclado.next();
        }while(Tarjeta.length()!=16);
        
        System.out.println("email:");
        String Email=teclado.next();
        
        System.out.println("Que tipo de abonado quiere");
        System.out.println("1. Mensual");
        System.out.println("2. Trimestral");
        System.out.println("3. Semestral");
        System.out.println("4. Anual");
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
            asignarPlazaParking(abonado);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosAbonados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private static void asignarPlazaParking(AbonadosVO abonado) throws SQLException{
        PlazasDAO p1=new PlazasDAO();
        ArrayList<PlazasVO> listaPlazas=new ArrayList<>();
        ArrayList<PlazasVO> listaPlazasLibres=new ArrayList<>(); 
        ArrayList<PlazasVO> listaTurismo=new ArrayList<>();
        ArrayList<PlazasVO> listaMotocicleta=new ArrayList<>();
        ArrayList<PlazasVO> listaCaravana=new ArrayList<>();
        
        listaPlazas=p1.getAll();
        
        listaPlazas.forEach(System.out::println);
        //Coge todas las plazas libres del parking
        for (PlazasVO tmp : listaPlazas) {
            if (tmp.getEstado() == 2) {
                listaPlazasLibres.add(tmp);
            }
        }


        //Recoge las plazas libres de turismo
        for (PlazasVO tmp : listaPlazasLibres) {
            if (tmp.getCodigoPlaza() > 100 && tmp.getCodigoPlaza() <= 115) {
                listaTurismo.add(tmp);
            }
        }

        //Recoge las plazas libres de motocicleta
        for (PlazasVO tmp : listaPlazasLibres) {
            if (tmp.getCodigoPlaza() > 200 && tmp.getCodigoPlaza() <= 215) {
                listaMotocicleta.add(tmp);
            }
        }

        //Recoge las plazas libres de caravanas
        for (PlazasVO tmp : listaPlazasLibres) {
            if (tmp.getCodigoPlaza() > 300 && tmp.getCodigoPlaza() <= 315) {
                listaCaravana.add(tmp);
            }
        }
        
        VehiculoDAO v1=new VehiculoDAO();
        VehiculoVO v2=new VehiculoVO();
        
        v2=v1.findByPk(abonado.getMatricula());
        PlazasVO plazaNueva;
        
        switch (v2.getTipoVehiculo()) {
            case 1:
                plazaNueva=listaTurismo.get(0);
                plazaNueva.setMatricula(v2.getMatricula());
                plazaNueva.setEstado(4);
                p1.updatePlazas(listaTurismo.get(0).getCodigoPlaza(), plazaNueva);
                break;
            
            case 2:
                plazaNueva=listaMotocicleta.get(0);
                plazaNueva.setMatricula(v2.getMatricula());
                plazaNueva.setEstado(4);
                p1.updatePlazas(listaMotocicleta.get(0).getCodigoPlaza(), plazaNueva);
                break;
                
            case 3:
                plazaNueva=listaCaravana.get(0);
                plazaNueva.setMatricula(v2.getMatricula());
                plazaNueva.setEstado(4);
                p1.updatePlazas(listaCaravana.get(0).getCodigoPlaza(), plazaNueva);
                break;
                
        }
    }
    
    public int numTotalPlazas() {
        
        return(PlazasVO.NUMEROPLAZAS_CARAVANA +PlazasVO.NUMEROPLAZAS_MOTOCICLETA
        + PlazasVO.NUMEROPLAZAS_TURISMO);
            
    }
    
}
