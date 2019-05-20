/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abonados;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javie
 */
public class AbonadosDAO implements IAbonados{
     private Connection con = null;

    public AbonadosDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<AbonadosVO> getAll() throws SQLException {
        List<AbonadosVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from persona");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                AbonadosVO p = new AbonadosVO();
                // Recogemos los datos de la persona, guardamos en un objeto
               
                p.setNombre(res.getString("nombre"));
                p.setApellidos(res.getString("apelidos"));
                p.setDNI(res.getString("DNI"));
                p.setPinAbonados(res.getInt("pinAbonados"));
                p.setTarjetaCredito(res.getString("tarjetaCredito"));
                p.setEmail(res.getString("email"));
                p.setTipoAbonados(res.getInt("tipoAbonado"));
              p.setMatricula(res.getString("matricula"));
               
                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }

        return lista;
    }
     @Override
    public AbonadosVO findByPk(String DNI) throws SQLException {
         ResultSet res = null;
        AbonadosVO abonados = new AbonadosVO();

        String sql = "select * from abonados where DNI=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setString(4, DNI);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                
                abonados.setNombre(res.getString("nombre"));
                abonados.setApellidos(res.getString("apellidos"));
                abonados.setDNI(res.getString("DNI"));
                abonados.setPinAbonados(res.getInt("pinAbonados"));
                abonados.setPinAbonados(res.getInt("pinAbonados"));
                abonados.setTarjetaCredito(res.getString("tarjetaCredito"));
                abonados.setEmail(res.getString("email"));
                abonados.setTipoAbonados(res.getInt("tipoAbonado"));
              abonados.setMatricula(res.getString("matricula"));
                return abonados;
            }

            return null;
        }
    }
    @Override
    public int insertAbonados(AbonadosVO abonados) throws SQLException {
        int numFilas = 0;
        String sql = "insert into abonados values (?,?,?,?,?,?,?,?,?,?)";

        if (findByPk(abonados.getDNI()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                
                prest.setString(1,abonados.getNombre());
                prest.setString(2,abonados.getApellidos());
                prest.setString(3, abonados.getDNI());
                prest.setInt(4,abonados.getPinAbonados());
                prest.setString(5,abonados.getTarjetaCredito());
                prest.setString(6,abonados.getEmail());
                prest.setInt(7,abonados.getTipoAbonados());
                prest.setString(8,abonados.getMatricula());
                prest.setTimestamp(9,abonados.getFechaInicioAbono());
                prest.setTimestamp(10,abonados.getFechaFinAbono());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertAbonados(List<AbonadosVO> lista) throws SQLException {
      int numFilas = 0;

        for (AbonadosVO tmp : lista) {
            numFilas += insertAbonados(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteAbonados(AbonadosVO p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    @Override
    public int deleteAbonados() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateAbonados(int codAbonados, AbonadosVO nuevosDatos) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
     
}
