/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;

import Conexion.Conexion;
import Tickets.TicketsVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jriosaguilar
 */
public class VehiculoDAO implements IVehiculo{
    
    private Connection con = null;

    public VehiculoDAO() {
        con = Conexion.getInstance();
    }
    @Override
    public ArrayList<VehiculoVO> getAll() throws SQLException {
        ArrayList<VehiculoVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from vehiculos");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                VehiculoVO p = new VehiculoVO();
                
                // Recogemos los datos del vehiculo, guardamos en un objeto
                p.setMatricula(res.getString("matricula"));
                p.setTipoVehiculo(res.getInt("tipoVehiculo"));
               
                //Añadimos el objeto a la lista
                lista.add(p);
            }
    }
         return lista;
    }
    
    @Override
    public VehiculoVO findByPk(String matricula) throws SQLException {
          ResultSet res = null;
        VehiculoVO vehiculos = new VehiculoVO();

        String sql = "select * from vehiculos where matricula=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setString(1, matricula);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                
                vehiculos.setMatricula(res.getString("matricula"));
                vehiculos.setTipoVehiculo(res.getInt("tipoVehiculo"));
                
                return vehiculos;
            }

            return null;
        }
    }
    
    @Override
    public int insertVehiculo(VehiculoVO vehiculo) throws SQLException {
        int numFilas = 0;
        String sql = "insert into vehiculos values (?,?)";

        if (findByPk(vehiculo.getMatricula()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia          
                prest.setString(1,vehiculo.getMatricula());
                prest.setInt(2,vehiculo.getTipoVehiculo());                

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertVehiculo(List<VehiculoVO> lista) throws SQLException {
        int numFilas = 0;

        for (VehiculoVO tmp : lista) {
            numFilas += insertVehiculo(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteVehiculo(VehiculoVO vehiculo) throws SQLException {
        int numFilas = 0;

        String sql = "delete from vehiculos where matricula = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setString(1, vehiculo.getMatricula());
            
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int deleteVehiculo() throws SQLException {
         String sql = "delete from vehiculos";

        int nfilas = 0;

        // Preparamos el borrado de datos  mediante un Statement
        // No hay parámetros en la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecución de la sentencia
            nfilas = st.executeUpdate(sql);
        }

        // El borrado se realizó con éxito, devolvemos filas afectadas
        return nfilas;
    }

    @Override
    public int updateVehiculo(String matricula, VehiculoVO nuevosDatos) throws SQLException {
         int numFilas = 0;
        String sql = "update vehiculos set matricula=?, tipoVehiculo=?"
                + " where matricula=?";

        if (findByPk(matricula) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1,nuevosDatos.getMatricula());
                prest.setInt(2,nuevosDatos.getTipoVehiculo());
                prest.setString(3,matricula);
                
                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }
    
}
