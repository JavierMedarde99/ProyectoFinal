/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;

import Abonados.AbonadosVO;

/**
 *
 * @author jriosaguilar
 */
public class VehiculoVO {
    
    //Atributos
    private String matricula;
    private Integer tipoVehiculo;
    private AbonadosVO dueño;
    
    //Métodos
    //Constructor parametrizado
    public VehiculoVO(String matricula, Integer tipoVehiculo, AbonadosVO dueño) {
        this.matricula = matricula;
        this.tipoVehiculo = tipoVehiculo;
        this.dueño = dueño;
    }

    //Constructor por defecto
    public VehiculoVO() {
    }
    
    //Getters y setters

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Integer tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public AbonadosVO getDueño() {
        return dueño;
    }

    public void setDueño(AbonadosVO dueño) {
        this.dueño = dueño;
    }
    
    //toString
    @Override
    public String toString() {
        return "VehiculoVO{" + "matricula=" + matricula + ", tipoVehiculo=" + tipoVehiculo + ", due\u00f1o=" + dueño + '}';
    }
    
}
