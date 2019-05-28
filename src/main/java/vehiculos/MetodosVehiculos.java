/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import plazas.PlazasDAO;
import plazas.PlazasVO;

/**
 *
 * @author javi
 */
public class MetodosVehiculos { 
    
    public static VehiculoVO datosVehiculos(){
        VehiculoDAO daoVehiculo = new VehiculoDAO();
       
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
        VehiculoVO vehiculo=new VehiculoVO(matricula,TipoVehiculos);
        try {
            daoVehiculo.insertVehiculo(vehiculo);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehiculo;
    }
    
    public static void EliminarVehiculo(String matricula){
        try {
            VehiculoDAO daoVehiculo = new VehiculoDAO();
            VehiculoVO vehiculo =daoVehiculo.findByPk(matricula);
            daoVehiculo.deleteVehiculo(vehiculo);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void depositarVehiculo(String matricula, int tipo) throws SQLException{
        PlazasDAO p=new PlazasDAO();
        ArrayList<PlazasVO> listaPlazas=new ArrayList<>();
        ArrayList<PlazasVO> listaPlazasLibres=new ArrayList<>();
        ArrayList<PlazasVO> listaTurismo=new ArrayList<>();
        ArrayList<PlazasVO> listaMotocicleta=new ArrayList<>();
        ArrayList<PlazasVO> listaCaravana=new ArrayList<>();
        listaPlazas=p.getAll();
        
        for (PlazasVO tmp : listaPlazas) {
            if(tmp.getEstado()==2){
                listaPlazasLibres.add(tmp);
            }
        }
        
        System.out.println("Plazas libres: ");
        listaPlazasLibres.forEach(System.out::println);
        
        switch (tipo) {
            case 1:
                for (PlazasVO tmp : listaPlazasLibres) {
                    if(tmp.getCodigoPlaza()>100 && tmp.getCodigoPlaza()<=115){
                        listaTurismo.add(tmp);
                    }
                }
                
                if(!listaTurismo.isEmpty()){
                    System.out.println("Plaza: "+listaTurismo.get(0));
                    p.updatePlazas(listaTurismo.get(0).getCodigoPlaza(), new PlazasVO(matricula,1));
                }
                break;
                
            case 2:
                for (PlazasVO tmp : listaPlazasLibres) {
                    if(tmp.getCodigoPlaza()>200 && tmp.getCodigoPlaza()<=215){
                        listaMotocicleta.add(tmp);
                    }
                }
                
                if(!listaTurismo.isEmpty()){
                    System.out.println("Plaza: "+listaMotocicleta.get(0));
                    p.updatePlazas(listaMotocicleta.get(0).getCodigoPlaza(), new PlazasVO(matricula,1));
                }
                break;
                
            case 3:
                for (PlazasVO tmp : listaPlazasLibres) {
                    if(tmp.getCodigoPlaza()>300 && tmp.getCodigoPlaza()<=315){
                        listaCaravana.add(tmp);
                    }
                }
                
                if(!listaTurismo.isEmpty()){
                    System.out.println("Plaza: "+listaCaravana.get(0));
                    p.updatePlazas(listaCaravana.get(0).getCodigoPlaza(), new PlazasVO(matricula,1));
                }
                break;
            default:
                System.out.println("Tipo de vehículo incorrecto");
        }
    }
}
