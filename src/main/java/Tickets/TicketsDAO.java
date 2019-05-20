/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;

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
public class TicketsDAO implements ITickets{
private Connection con = null;

    public TicketsDAO() {
        con = Conexion.getInstance();
    }
    @Override
    public List<TicketsVO> getAll() throws SQLException {
        List<TicketsVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from tikets");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                TicketsVO p = new TicketsVO();
                // Recogemos los datos de la persona, guardamos en un objeto
               p.setCodPlazas(res.getInt("codPlazas"));
                p.setMatricula(res.getString("matricula"));
                p.setPin(res.getInt("pin"));
                p.setPrecioFin(res.getDouble("precioFin"));
                p.setPrecioMin(res.getDouble("precioPorMin"));
                p.setTiempoFin(res.getTimestamp("tiempoSalida"));
                p.setTiempoInicio(res.getTimestamp("tiempoEntrada"));
               
                //Añadimos el objeto a la lista
                lista.add(p);
            }
    }
         return lista;
    }

    @Override
    public int insertPersona(TicketsVO ticket) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertPersona(List<TicketsVO> lista) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deletePersona(TicketsVO ticket) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deletePersona() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updatePersona(int codPlazas, String matricula, TicketsVO nuevosDatos) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
