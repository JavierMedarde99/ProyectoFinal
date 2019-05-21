/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
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
                p.setTiempoInicio(res.getTime("tiempoEntrada").toLocalTime());
                p.setFechaInicio(res.getDate("fechaEntrada").toLocalDate());
                 p.setTiempoFin(res.getTime("tiempoSalida").toLocalTime());
                p.setFechaFin(res.getDate("fechaSalida").toLocalDate());
                //p.setTiempoInicio(res.getTimestamp("tiempoEntrada").toLocalDateTime());
               
                //Añadimos el objeto a la lista
                lista.add(p);
            }
    }
         return lista;
    }
 @Override
    public TicketsVO findByPk(String matricula,int codPlazas) throws SQLException {
          ResultSet res = null;
        TicketsVO tickets = new TicketsVO();

        String sql = "select * from tikets where matricula=? and codPlazas=?";

        try (PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setString(2, matricula);
             prest.setInt(1, codPlazas);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.first()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                
                tickets.setCodPlazas(res.getInt("codPlazas"));
                tickets.setMatricula(res.getString("matricula"));
                tickets.setPin(res.getInt("pin"));
                tickets.setPrecioFin(res.getDouble("precioFin"));
                tickets.setPrecioMin(res.getDouble("precioPorMin"));
                tickets.setTiempoInicio(res.getTime("tiempoEntrada").toLocalTime());
                tickets.setFechaInicio(res.getDate("fechaEntrada").toLocalDate());
                tickets.setTiempoFin(res.getTime("tiempoSalida").toLocalTime());
                tickets.setFechaFin(res.getDate("fechaSalida").toLocalDate());
                return tickets;
            }

            return null;
        }
    }
    @Override
    public int insertTickets(TicketsVO ticket) throws SQLException {
        int numFilas = 0;
        String sql = "insert into tikets values (?,?,?,?,?,?,?)";

        if (findByPk(ticket.getMatricula(),ticket.getCodPlazas()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                
                prest.setString(2,ticket.getMatricula());
                prest.setInt(1,ticket.getCodPlazas());
                prest.setInt(3, ticket.getPin());
                prest.setDouble(4,ticket.getPrecioFin());
                prest.setDouble(5,ticket.getPrecioMin());
                prest.setTime(6,Time.valueOf(ticket.getTiempoInicio()));
                prest.setDate(7,Date.valueOf(ticket.getFechaInicio()));
                prest.setTime(8,Time.valueOf(ticket.getTiempoFin()));
                prest.setDate(9,Date.valueOf(ticket.getFechaFin()));

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int insertTickets(List<TicketsVO> lista) throws SQLException {
        int numFilas = 0;

        for (TicketsVO tmp : lista) {
            numFilas += insertTickets(tmp);
        }

        return numFilas;
    }

    @Override
    public int deleteTickets(TicketsVO ticket) throws SQLException {
        int numFilas = 0;

        String sql = "delete from tikets where matricula = ? and codAbonados=?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
             prest.setInt(1, ticket.getCodPlazas());
            prest.setString(2, ticket.getMatricula());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;
    }

    @Override
    public int deleteTickets() throws SQLException {
         String sql = "delete from tikets";

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
    public int updateTickets(int codPlazas, String matricula, TicketsVO nuevosDatos) throws SQLException {
         int numFilas = 0;
        String sql = "update tikets set pin=?,precioFin=?,"
                + "precioPorMin=?,tiempoEntrada=?,tiempoSalida=?"
                + " where matricula=? and codplazas=?";

        if (findByPk(matricula,codPlazas) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(2,nuevosDatos.getMatricula());
                prest.setInt(1,nuevosDatos.getCodPlazas());
                prest.setInt(3, nuevosDatos.getPin());
                prest.setDouble(4,nuevosDatos.getPrecioFin());
                prest.setDouble(5,nuevosDatos.getPrecioMin());
                prest.setTime(6,Time.valueOf(nuevosDatos.getTiempoInicio()));
                prest.setDate(7,Date.valueOf(nuevosDatos.getFechaInicio()));
                prest.setTime(8,Time.valueOf(nuevosDatos.getTiempoFin()));
                prest.setDate(9,Date.valueOf(nuevosDatos.getFechaFin()));
                
                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

   
    
}
