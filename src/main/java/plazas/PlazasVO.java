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
    private int codigoPlaza; //Clave primaria
    public final static int NUMEROPLAZAS=45;
    private String matricula; //Se usa matricula del vehiculo como clave foranea
    private boolean estado;
    private static int contador=1;
    
    //Métodos
    //Constructor parametrizado
    public PlazasVO(String matricula, boolean estado) {
        this.matricula = matricula;
        this.estado = estado;
        this.codigoPlaza=contador;
        contador++;
    }

    //Constructor por defecto
    public PlazasVO() {
        this.codigoPlaza=contador;
        contador++;
    }

    //Getters y setters
    public int getCodigoPlaza() {
        return codigoPlaza;
    }

    public void setCodigoPlaza(int codigoPlaza) {
        this.codigoPlaza = codigoPlaza;
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
        return "PlazasVO{" + "codigoPlaza=" + codigoPlaza + ", matricula=" + matricula + ", estado=" + estado + '}';
    }
    
}
