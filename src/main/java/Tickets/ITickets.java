/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javi
 */
public interface ITickets {
        ArrayList<TicketsVO> getAll() throws SQLException;
    int insertTickets (TicketsVO ticket) throws SQLException;
     TicketsVO findByPk(String matricula,int codPlazas) throws SQLException;
    int insertTickets (List<TicketsVO> lista) throws SQLException;
     int deleteTickets (TicketsVO ticket) throws SQLException;
    int deleteTickets() throws SQLException;
    int updateTickets (int codPlazas, String matricula, TicketsVO nuevosDatos) throws SQLException;
}
