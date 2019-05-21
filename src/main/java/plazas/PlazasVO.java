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
    private String matricula; //Se usa matricula del vehiculo como clave foranea
    private boolean estado;
    
    //Métodos
    //Constructor parametrizado
    public PlazasVO(String matricula, boolean estado) {
        this.matricula = matricula;
        this.estado = estado;
        codigoPlaza++;
    }

    //Constructor por defecto
    public PlazasVO() {
        codigoPlaza++;
    }

    //Getters y setters
    public static int getCodigoPlaza() {
        return codigoPlaza;
    }

    public static void setCodigoPlaza(int codigoPlaza) {
        PlazasVO.codigoPlaza = codigoPlaza;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PlazasVO{" + "matricula=" + matricula + ", estado=" + estado + '}';
    }
    
}
