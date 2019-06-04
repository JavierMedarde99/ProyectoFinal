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
    public final static int NUMEROPLAZAS_TURISMO=15;
    public final static int NUMEROPLAZAS_MOTOCICLETA=15;
    public final static int NUMEROPLAZAS_CARAVANA=15;
    private String matricula; //Se usa matricula del vehiculo como clave foranea
    private int estado; //1-Ocupada   2-Libre   3-Ocupada abonado   4-Libre abonado
    
    //Métodos
    //Constructor parametrizado
    public PlazasVO(String matricula, int estado) {
        this.matricula = matricula;
        this.estado = estado;
    } 

    //Constructor por defecto
    public PlazasVO() {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return  codigoPlaza + ":" + matricula + ":" + estado + "\n";
    }
    
    
    public String toStringNormal() {
       return "PlazasVO{" + "codigoPlaza=" + codigoPlaza + ", matricula=" + matricula + ", estado=" + estado + '}';
    }
    
}
