/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;

import java.sql.SQLException;
import java.util.List;
import plazas.PlazasVO;

/**
 *
 * @author jriosaguilar
 */
public interface IVehiculo {
    
    List<VehiculoVO> getAll() throws SQLException;
    
    int insertVehiculo(VehiculoVO vehiculo) throws SQLException;
    
    VehiculoVO findByPk(String matricula) throws SQLException;
    
    int insertVehiculo(List<VehiculoVO> lista) throws SQLException;
    
    int deleteVehiculo(VehiculoVO vehiculo) throws SQLException;
    
    int deleteVehiculo() throws SQLException;
    
    int updateVehiculo(String vehiculo, VehiculoVO nuevosDatos) throws SQLException;
    
}
