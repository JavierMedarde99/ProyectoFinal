/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abonados;

import Conexion.Conexion;
import java.sql.Connection;
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
                p.setCodAbonados(res.getInt("codAbonados"));
                p.setNombre(res.getString("nombre"));
                p.setApellidos(res.getString("apelidos"));
                p.setDNI(res.getString("DNI"));
                p.setPinAbonados(res.getInt("pinAbonados"));
                p.setTarjetaCredito(res.getString("tarjetaCredito"));
                p.setEmail(res.getString("email"));
                p.setTipoAbonados(res.getInt("tipoAbonado"));
                p.setMatricula(res.getString("Matricula"));
               
                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }

        return lista;
    }

    @Override
    public int insertPersona(AbonadosVO persona) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertPersona(List<AbonadosVO> lista) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deletePersona(AbonadosVO p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deletePersona() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updatePersona(int codAbonados, AbonadosVO nuevosDatos) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
