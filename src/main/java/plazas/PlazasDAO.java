/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plazas;

import Conexion.Conexion;
import plazas.PlazasVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jriosaguilar
 */
public class PlazasDAO implements IPlazas{
    
    private Connection con = null;

    public PlazasDAO() {
        con = Conexion.getInstance();
    }
    
    @Override
    public List<PlazasVO> getAll() throws SQLException {
        List<PlazasVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from plazas");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                PlazasVO p = new PlazasVO();
                
                // Recogemos los datos de la plaza, guardamos en un objeto
                p.setEstado(res.getInt("columna"));
                p.setMatricula(res.getString("matricula"));
               
                //Añadimos el objeto a la lista
                lista.add(p);
            }
    }
         return lista;
    }
    
    @Override
    public PlazasVO findByPk(int codPlazas) throws SQLException {
        ResultSet res = null;
        PlazasVO plazas = new PlazasVO();

        String sql = "select * from plazas where codPlazas=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, codPlazas);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la plaza, guardamos en un objeto
                
                plazas.setEstado(res.getInt("columna"));
                plazas.setMatricula(res.getString("matricula"));
                return plazas;
            }

            return null;
        }
    }
    
   /* @Override
    public int insertPlazas(PlazasVO plazas) throws SQLException {
        int numFilas = 0;
        String sql = "insert into plazas values (?,?,?,?)";

        if (findByPk(plazas.getCodigoPlaza()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1,plazas.getCodigoPlaza());
                prest.setInt(2, 45);
                prest.setString(3,plazas.getMatricula());
                prest.setBoolean(4,plazas.getEstado());              

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertPlazas(List<PlazasVO> lista) throws SQLException {
        int numFilas = 0;

        for (PlazasVO tmp : lista) {
            numFilas += insertPlazas(tmp);
        }

        return numFilas;
    }

    @Override
    public int deletePlazas(PlazasVO plazas) throws SQLException {
        int numFilas = 0;

        String sql = "delete from plazas where codPlazas = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, plazas.getCodigoPlaza());
            
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int deletePlazas() throws SQLException {
         String sql = "delete from plazas";

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
*/
    @Override
    public int updatePlazas(int codPlazas, PlazasVO nuevosDatos) throws SQLException {
         int numFilas = 0;
        String sql = "update plazas set numPlazas = ?, matricula=?, estado=?,"
                + " where codplazas=?";

        if (findByPk(codPlazas) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia                
                prest.setInt(4,nuevosDatos.getCodigoPlaza());
                prest.setInt(1,45);
                prest.setString(2, nuevosDatos.getMatricula());
                prest.setInt(3,nuevosDatos.getEstado());
                
                numFilas = prest.executeUpdate();
            }
            return numFilas;
            
        }
    }
    
}
