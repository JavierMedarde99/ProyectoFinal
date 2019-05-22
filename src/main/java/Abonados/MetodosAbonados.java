/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abonados;

import java.util.ArrayList;

/**
 *
 * @author jriosaguilar
 */
public class MetodosAbonados {
    
    public static boolean comprobarAbonado(ArrayList<AbonadosVO> abonados, String dni){
        for (AbonadosVO tmp : abonados) {
            if(tmp.getDNI().equalsIgnoreCase(dni)){
                return true;
            }       
        }
        return false;
    }
    
}
