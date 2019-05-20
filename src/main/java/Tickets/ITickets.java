/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author javi
 */
public interface ITickets {
        List<TicketsVO> getAll() throws SQLException;
    int insertPersona (TicketsVO ticket) throws SQLException;
    int insertPersona (List<TicketsVO> lista) throws SQLException;
     int deletePersona (TicketsVO ticket) throws SQLException;
    int deletePersona() throws SQLException;
    int updatePersona (int codPlazas, String matricula, TicketsVO nuevosDatos) throws SQLException;
}
