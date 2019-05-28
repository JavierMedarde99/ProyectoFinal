/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plazas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jriosaguilar
 */
public interface IPlazas {
    
    ArrayList<PlazasVO> getAll() throws SQLException;
    
    // int insertPlazas(PlazasVO plaza) throws SQLException;
    
    PlazasVO findByPk(int codPlazas) throws SQLException;
    
    // int insertPlazas(List<PlazasVO> lista) throws SQLException;
    
    // int deletePlazas(PlazasVO plaza) throws SQLException;
    
    // int deletePlazas() throws SQLException;
    
    int updatePlazas(int codPlazas, PlazasVO nuevosDatos) throws SQLException;
    
}
