/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculos;

import Abonados.AbonadosDAO;
import Abonados.AbonadosVO;
import Tickets.MetodosTickets;
import Tickets.TicketsDAO;
import Tickets.TicketsVO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import plazas.PlazasDAO;
import plazas.PlazasVO;

/**
 *
 * @author javi
 */
public class MetodosVehiculos {

    public static VehiculoVO datosVehiculos() {
        VehiculoDAO daoVehiculo = new VehiculoDAO();

        int TipoVehiculos;
        String matricula;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Inserte la matricula");
        matricula = teclado.nextLine();
        System.out.println("Introduce el tipo de vehiculo");
        System.out.println("1.si es turismo");
        System.out.println("2.si es motocicleta");
        System.out.println("3.si es Caravanas");

        do {
            TipoVehiculos = teclado.nextInt();
            switch (TipoVehiculos) {
                case 1:
                    TipoVehiculos = 1;
                    break;
                case 2:
                    TipoVehiculos = 2;
                    break;
                case 3:
                    TipoVehiculos = 3;
                    break;
                default:
                    TipoVehiculos = 0;
            }
        } while (TipoVehiculos == 0);
        VehiculoVO vehiculo = new VehiculoVO(matricula, TipoVehiculos);
        try {
            daoVehiculo.insertVehiculo(vehiculo);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehiculo;
    }

    public static boolean comprobarMatricula(String matricula) throws SQLException {
        PlazasDAO p1 = new PlazasDAO();
        ArrayList<PlazasVO> listaMatriculas = new ArrayList<>();
        listaMatriculas = p1.getAll();

        for (PlazasVO tmp : listaMatriculas) {
            if (tmp.getMatricula().equalsIgnoreCase(matricula)) {
                return true;
            }
        }
        return false;
    }

    public static void escribirFicheroPin(AbonadosVO abonado) {
        //Creamos el directorio
        Path directory = Paths.get("./pinAbonados");
        try {
            Files.createDirectory(directory);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio pinAbonados.");
            System.out.println(e.toString());
        }

        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        String fichero = "pinAbonados/" + abonado.getDNI() + ".txt";

        // Estructura try-with-resources. Instancia el objeto con el fichero a escribir
        // y se encarga de cerrar el recurso "flujo" una vez finalizadas las operaciones
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(fichero))) {
            flujo.write(abonado.getPinAbonados());
            flujo.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static TicketsVO meterVehiculoNoAbonado(String matricula, int tipo) {
        int cont = 0;
        PlazasVO plazas = new PlazasVO(matricula, 1);
        TicketsDAO daoticket = new TicketsDAO();
        PlazasDAO daoplaza = new PlazasDAO();
        VehiculoDAO daovehiculo = new VehiculoDAO();
        ArrayList<PlazasVO> plazasArray = new ArrayList<>();

        try {
            plazasArray = daoplaza.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < plazasArray.size(); i++) {

            switch (tipo) {
                case 1:
                    for (int j = 101; j < 116; j++) {

                        if (plazasArray.get(i).getCodigoPlaza() == j && plazasArray.get(i).getEstado() == 2 && cont == 0) {

                            try {

                                daovehiculo.insertVehiculo(new VehiculoVO(matricula, tipo));
                                daoplaza.updatePlazas(j, plazas);
                                cont++;
                            } catch (SQLException ex) {
                                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            try {

                                daoticket.insertTickets(MetodosTickets.crearTicket(matricula, j, tipo));
                                TicketsVO ticket = daoticket.findByPk(matricula, j);
                                return ticket;
                            } catch (SQLException ex) {
                                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    break;
                case 2:

                    for (int j = 201; j < 216; j++) {
                        if (plazasArray.get(i).getCodigoPlaza() == j && plazasArray.get(i).getEstado() == 2 && cont == 0) {
                            /* try {
                                    MetodosTickets.crearTicket(matricula, j, tipo);
                                } catch (SQLException ex) {
                                    Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
                                }
                             */
                            try {
                                daovehiculo.insertVehiculo(new VehiculoVO(matricula, tipo));
                                daoplaza.updatePlazas(j, plazas);
                                cont++;

                            } catch (SQLException ex) {
                                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            try {

                                daoticket.insertTickets(MetodosTickets.crearTicket(matricula, j, tipo));
                                TicketsVO ticket = daoticket.findByPk(matricula, j);
                                return ticket;
                            } catch (SQLException ex) {
                                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    break;
                case 3:
                    for (int j = 301; j < 316; j++) {
                        if (plazasArray.get(i).getCodigoPlaza() == j && plazasArray.get(i).getEstado() == 2 && cont == 0) {
                            /*try {
                                    MetodosTickets.crearTicket(matricula, j, tipo);
                                } catch (SQLException ex) {
                                    Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
                                }*/
                            try {
                                daovehiculo.insertVehiculo(new VehiculoVO(matricula, tipo));
                                daoplaza.updatePlazas(j, plazas);

                                cont++;
                            } catch (SQLException ex) {
                                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            try {

                                daoticket.insertTickets(MetodosTickets.crearTicket(matricula, j, tipo));
                                TicketsVO ticket = daoticket.findByPk(matricula, j);
                                return ticket;
                            } catch (SQLException ex) {
                                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    break;

                default:
                    System.out.println("no hay plazas disponibles");
            }
        }
        return new TicketsVO();
    }

    public static TicketsVO retirarVehiculoNoAbonado(String matricula, int plaza, String pin) {
        PlazasDAO p1 = new PlazasDAO();
        PlazasVO p2 = new PlazasVO(null, 2);
        TicketsDAO t1 = new TicketsDAO();
        TicketsVO t2 = new TicketsVO();
        VehiculoDAO v1 = new VehiculoDAO();
        VehiculoVO v2 = new VehiculoVO();
        try {

            t2 = t1.findByPk(matricula, plaza);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (t2.getCodPlazas() == plaza && t2.getMatricula().equalsIgnoreCase(matricula) && t2.getPin().equalsIgnoreCase(pin)) {

            try {
                p1.updatePlazas(plaza, p2);
            } catch (SQLException ex) {
                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
            }
            Date fechaFin = Date.valueOf(LocalDate.now());
            Time tiempoFin = Time.valueOf(LocalTime.now());
            double precioFin = t2.getPrecioMin() * MetodosTickets.retirarTicket(t2.getTiempoInicio(), t2.getFechaInicio());
            TicketsVO modificado = new TicketsVO(t2.getCodPlazas(), t2.getMatricula(), t2.getPin(), precioFin, t2.getPrecioMin(), Date.valueOf(t2.getFechaInicio()), Time.valueOf(t2.getTiempoInicio()), fechaFin, tiempoFin);
            try {
                t1.updateTickets(plaza, matricula, modificado);
                return modificado;
            } catch (SQLException ex) {
                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new TicketsVO();
    }

    public static void meterVehiculoAbonado(String dni, String matricula, String pin) {
        PlazasDAO p1 = new PlazasDAO();
        PlazasVO p2 = new PlazasVO(matricula, 3);
        PlazasVO p3 = new PlazasVO();
        AbonadosDAO a1 = new AbonadosDAO();
        AbonadosVO a2 = new AbonadosVO();
        try {
            a2 = a1.findByPk(dni);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            p3 = p1.findByFk(matricula);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (a2.getMatricula().equalsIgnoreCase(matricula) && a2.getPinAbonados().equalsIgnoreCase(pin) && a2.getDNI().equalsIgnoreCase(dni)) {
            try {
                p1.updatePlazas(p3.getCodigoPlaza(), p2);
            } catch (SQLException ex) {
                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void sacarVehiculoAbonado(String dni, String matricula, String pin) {
        PlazasDAO p1 = new PlazasDAO();
        PlazasVO p2 = new PlazasVO(matricula, 4);
        PlazasVO p3 = new PlazasVO();
        AbonadosDAO a1 = new AbonadosDAO();
        AbonadosVO a2 = new AbonadosVO();
        try {
            a2 = a1.findByPk(dni);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            p3 = p1.findByFk(matricula);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (a2.getMatricula().equalsIgnoreCase(matricula) && a2.getPinAbonados().equalsIgnoreCase(pin) && a2.getDNI().equalsIgnoreCase(dni)) {
            try {
                p1.updatePlazas(p3.getCodigoPlaza(), p2);
            } catch (SQLException ex) {
                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void CopiaSeguridadAbonados(ArrayList<AbonadosVO> abonado) throws SQLException {
        //Creamos el directorio
        Path directory = Paths.get("./backup");
        try {
            Files.createDirectory(directory);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio pinAbonados.");
            System.out.println(e.toString());
        }
        Path directory2 = Paths.get("./backup/" + LocalDate.now() + LocalTime.now().getHour() + "_" + LocalTime.now().getMinute() + "_" + LocalTime.now().getSecond());
        try {
            Files.createDirectory(directory2);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio pinAbonados.");
            System.out.println(e.toString());
        }
        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        String fichero = "backup/" + LocalDate.now() + LocalTime.now().getHour() + "_" + LocalTime.now().getMinute() + "_" + LocalTime.now().getSecond() + "/Abonados.txt";

        AbonadosDAO a1 = new AbonadosDAO();
        ArrayList<AbonadosVO> listaAbonados = new ArrayList<>();

        listaAbonados = a1.getAll();
        // Estructura try-with-resources. Instancia el objeto con el fichero a escribir
        // y se encarga de cerrar el recurso "flujo" una vez finalizadas las operaciones
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(fichero))) {
            for (AbonadosVO tmp : listaAbonados) {
                flujo.write(tmp.toString());
                flujo.newLine();
            }
            flujo.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void CopiaSeguridadVehiculos(ArrayList<VehiculoVO> vehiculo) throws SQLException {
        VehiculoDAO v1 = new VehiculoDAO();
        ArrayList<VehiculoVO> listaVehiculos = new ArrayList<>();

        listaVehiculos = v1.getAll();
        String fichero = "backup/" + LocalDate.now() + LocalTime.now().getHour() + "_" + LocalTime.now().getMinute() + "_" + LocalTime.now().getSecond() + "/Vehiculos.txt";

        // Estructura try-with-resources. Instancia el objeto con el fichero a escribir
        // y se encarga de cerrar el recurso "flujo" una vez finalizadas las operaciones
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(fichero))) {
            for (VehiculoVO tmp : listaVehiculos) {
                flujo.write(tmp.toString());
                flujo.newLine();
            }
            flujo.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void CopiaSeguridadPlazas(ArrayList<PlazasVO> plaza) throws SQLException {
        PlazasDAO p1 = new PlazasDAO();
        ArrayList<PlazasVO> listaPlazas = new ArrayList<>();
        listaPlazas = p1.getAll();
        String fichero = "backup/" + LocalDate.now() + LocalTime.now().getHour() + "_" + LocalTime.now().getMinute() + "_" + LocalTime.now().getSecond() + "/Plazas.txt";

        // Estructura try-with-resources. Instancia el objeto con el fichero a escribir
        // y se encarga de cerrar el recurso "flujo" una vez finalizadas las operaciones
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(fichero))) {
            for (PlazasVO tmp : listaPlazas) {
                flujo.write(tmp.toString());
                flujo.newLine();
            }
            flujo.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void CopiaSeguridadTickets(ArrayList<TicketsVO> ticket) {
        TicketsDAO t1 = new TicketsDAO();
        ArrayList<TicketsVO> listaTickets = new ArrayList<>();
        String fichero = "backup/" + LocalDate.now() + LocalTime.now().getHour() + "_" + LocalTime.now().getMinute() + "_" + LocalTime.now().getSecond() + "/Tickets.txt";

        // Estructura try-with-resources. Instancia el objeto con el fichero a escribir
        // y se encarga de cerrar el recurso "flujo" una vez finalizadas las operaciones
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(fichero))) {
            for (TicketsVO tmp : listaTickets) {
                flujo.write(ticket.toString());
                flujo.newLine();
            }
            flujo.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void restaurarCopia() throws SQLException {
        Scanner teclado = new Scanner(System.in);
        AbonadosDAO a1 = new AbonadosDAO();
        VehiculoDAO v1 = new VehiculoDAO();
        TicketsDAO t1 = new TicketsDAO();
        PlazasDAO p1 = new PlazasDAO();
        String nombre = "";

        //Imprimimos el fichero
        File f = new File("./backup");
        String[] lista = f.list();
        if (f.exists()) {
            File[] ficheros = f.listFiles();

            System.out.println("Copias de seguridad: ");
            for (File file2 : ficheros) {
                System.out.println(file2.getName());
            }

            System.out.println("Introduce la copia de seguridad que quiere restaurar: ");
            nombre = teclado.nextLine();
            for (String tmp : lista) {
                if (nombre.equalsIgnoreCase(tmp)) {
                    v1.deleteVehiculo();
                    a1.deleteAbonados();
                    p1.deletePlazas();
                    t1.deleteTickets();

                    ArrayList<VehiculoVO> listaVehiculos = new ArrayList<>();
                    try (Scanner datosFichero = new Scanner(new FileInputStream("./backup/" + nombre + "/Vehiculos.txt"), "ISO-8859-1")) {
                        String[] tokens;
                        String linea;

                        while (datosFichero.hasNextLine()) {
                            linea = datosFichero.nextLine();
                            tokens = linea.split("|");
                            System.out.println(tokens[0]);
                            System.out.println(tokens[1]);
                            listaVehiculos.add(new VehiculoVO(tokens[0], Integer.valueOf(tokens[1])));
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    v1.insertVehiculo(listaVehiculos);

                    ArrayList<PlazasVO> listaPlazas = new ArrayList<>();
                    try (Scanner datosFichero = new Scanner(new FileInputStream("./backup/" + nombre + "/Plazas.txt"), "ISO-8859-1")) {
                        String[] tokens;
                        String linea;

                        while (datosFichero.hasNextLine()) {
                            linea = datosFichero.nextLine();
                            tokens = linea.split("|");
                            listaPlazas.add(new PlazasVO(tokens[0], Integer.valueOf(tokens[1])));
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    p1.insertPlazas(listaPlazas);

                    ArrayList<AbonadosVO> listaAbonados = new ArrayList<>();
                    try (Scanner datosFichero = new Scanner(new FileInputStream("./backup/" + nombre + "/Abonados.txt"), "ISO-8859-1")) {
                        String[] tokens;
                        String linea;

                        while (datosFichero.hasNextLine()) {
                            linea = datosFichero.nextLine();
                            tokens = linea.split("|");

                            String separar = tokens[8].trim();
                            String[] fecha = separar.split("-");
                            LocalDate aux = LocalDate.of(Integer.valueOf(fecha[0]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[2]));
                            listaAbonados.add(new AbonadosVO(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], Integer.valueOf(tokens[6]), tokens[7], aux));
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    a1.insertAbonados(listaAbonados);

                    ArrayList<TicketsVO> listaTickets = new ArrayList<>();
                    try (Scanner datosFichero = new Scanner(new FileInputStream("./backup/" + nombre + "/Tickets.txt"), "ISO-8859-1")) {
                        String[] tokens;
                        String linea;

                        while (datosFichero.hasNextLine()) {
                            linea = datosFichero.nextLine();
                            tokens = linea.split("|");

                            String separar1 = tokens[5].trim();
                            String[] fecha1 = separar1.split("-");
                            LocalDate tmp1 = LocalDate.of(Integer.valueOf(fecha1[0]), Integer.valueOf(fecha1[1]), Integer.valueOf(fecha1[2]));

                            String separar2 = tokens[6].trim();
                            String[] fecha2 = separar2.split("-");
                            LocalDate tmp2 = LocalDate.of(Integer.valueOf(fecha2[0]), Integer.valueOf(fecha2[1]), Integer.valueOf(fecha2[2]));

                            String separar3 = tokens[7].trim();
                            String[] hora1 = separar3.split(":");
                            LocalTime tmp3 = LocalTime.of(Integer.valueOf(hora1[0]), Integer.valueOf(hora1[1]), Integer.valueOf(hora1[2]));

                            String separar4 = tokens[7].trim();
                            String[] hora2 = separar4.split(":");
                            LocalTime tmp4 = LocalTime.of(Integer.valueOf(hora2[0]), Integer.valueOf(hora2[1]), Integer.valueOf(hora2[2]));
                            listaTickets.add(new TicketsVO(Integer.valueOf(tokens[0]), tokens[1], tokens[2], Double.valueOf(tokens[3]), Double.valueOf(tokens[4]), Date.valueOf(tmp1), Time.valueOf(tmp3), Date.valueOf(tmp2), Time.valueOf(tmp4)));
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    t1.insertTickets(listaTickets);
                }
            }

        } else {
            System.out.println("El directorio a listar no existe");
        }

    }
}
