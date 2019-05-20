/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plazas;

import Abonados.AbonadosVO;
import vehiculos.VehiculoVO;

/**
 *
 * @author jriosaguilar
 */
public class PlazasVO {
    
    //Atributos
    private static int codigoPlaza=1; //Clave primaria
    private final static int NUMEROPLAZAS=45;
    private VehiculoVO vehiculo; //Se usa matricula del vehiculo como clave foranea
    
    //Métodos
    //Constructor parametrizado
    public PlazasVO(VehiculoVO vehiculo) {
        this.vehiculo = vehiculo;
    }

    //Constructor por defecto
    public PlazasVO() {
    }

    //Getters y setters
    public static int getCodigoPlaza() {
        return codigoPlaza;
    }

    public static void setCodigoPlaza(int codigoPlaza) {
        PlazasVO.codigoPlaza = codigoPlaza;
    }

    public VehiculoVO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoVO vehiculo) {
        this.vehiculo = vehiculo;
    }

    //toString
    @Override
    public String toString() {
        return "PlazasVO{" + "vehiculo=" + vehiculo + '}';
    }
    
}
