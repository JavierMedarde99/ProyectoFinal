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

    public static void EliminarVehiculo(String matricula) {
        try {
            VehiculoDAO daoVehiculo = new VehiculoDAO();
            VehiculoVO vehiculo = daoVehiculo.findByPk(matricula);
            daoVehiculo.deleteVehiculo(vehiculo);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public static void depositarVehiculo(String matricula, int tipo) throws SQLException {
        PlazasDAO p = new PlazasDAO();
        ArrayList<PlazasVO> listaPlazas = new ArrayList<>();
        ArrayList<PlazasVO> listaPlazasLibres = new ArrayList<>();
        ArrayList<PlazasVO> listaTurismo = new ArrayList<>();
        ArrayList<PlazasVO> listaMotocicleta = new ArrayList<>();
        ArrayList<PlazasVO> listaCaravana = new ArrayList<>();
        listaPlazas = p.getAll();

        //if(comprobarMatricula(matricula)==false){
        //Coge todas las plazas libres del parking
        for (PlazasVO tmp : listaPlazas) {
            if (tmp.getEstado() == 2) {
                listaPlazasLibres.add(tmp);
            }
        }

        //Muestra las plazas libres de turismo
        System.out.println("Plazas de turismo libres: ");
        for (PlazasVO tmp : listaPlazasLibres) {
            if (tmp.getCodigoPlaza() > 100 && tmp.getCodigoPlaza() <= 115) {
                listaTurismo.add(tmp);
            }
        }
        listaTurismo.forEach(System.out::println);

        //Muestra las plazas libres de motocicleta
        System.out.println("Plazas de motocicleta libres: ");
        for (PlazasVO tmp : listaPlazasLibres) {
            if (tmp.getCodigoPlaza() > 200 && tmp.getCodigoPlaza() <= 215) {
                listaMotocicleta.add(tmp);
            }
        }
        listaMotocicleta.forEach(System.out::println);

        //Muestra las plazas libres de caravanas
        System.out.println("Plazas de caravana libres: ");
        for (PlazasVO tmp : listaPlazasLibres) {
            if (tmp.getCodigoPlaza() > 300 && tmp.getCodigoPlaza() <= 315) {
                listaCaravana.add(tmp);
            }
        }
        listaCaravana.forEach(System.out::println);

        switch (tipo) {
            case 1:
                if (!listaTurismo.isEmpty()) {
                    System.out.println("Plaza: " + listaTurismo.get(0));
                    PlazasVO plazaLibre = listaTurismo.get(0);
                    plazaLibre.setEstado(1);
                    plazaLibre.setMatricula(matricula);
                    p.updatePlazas(listaTurismo.get(0).getCodigoPlaza(), plazaLibre);
                    MetodosTickets.crearTicket(matricula, listaTurismo.get(0).getCodigoPlaza(), tipo);
                }
                break;

            case 2:
                if (!listaMotocicleta.isEmpty()) {
                    System.out.println("Plaza: " + listaMotocicleta.get(0));
                    PlazasVO plazaLibre = listaMotocicleta.get(0);
                    plazaLibre.setEstado(1);
                    plazaLibre.setMatricula(matricula);
                    p.updatePlazas(listaMotocicleta.get(0).getCodigoPlaza(), plazaLibre);
                    MetodosTickets.crearTicket(matricula, listaMotocicleta.get(0).getCodigoPlaza(), tipo);

                }
                break;

            case 3:
                if (!listaCaravana.isEmpty()) {
                    System.out.println("Plaza: " + listaCaravana.get(0));
                    PlazasVO plazaLibre = listaCaravana.get(0);
                    plazaLibre.setEstado(1);
                    plazaLibre.setMatricula(matricula);
                    p.updatePlazas(listaCaravana.get(0).getCodigoPlaza(), plazaLibre);
                    MetodosTickets.crearTicket(matricula, listaCaravana.get(0).getCodigoPlaza(), tipo);
                }
                break;
            default:
                System.out.println("Tipo de vehículo incorrecto");
        }
        //}else{
        System.out.println("La matricula ya existe");
        //}
    }

    public static void depositarVehiculoAbonado(String dni, String matricula, String pin) throws SQLException {
        AbonadosDAO a1 = new AbonadosDAO();
        AbonadosVO a2 = a1.findByPk(dni);
        PlazasDAO p1 = new PlazasDAO();
        PlazasVO p2 = new PlazasVO();

        a2 = a1.findByPk(dni);

        if (matricula.equalsIgnoreCase(a2.getMatricula()) && pin == a2.getPinAbonados()) {
            if (p1.findByFk(matricula) != null) {
                p2 = p1.findByFk(matricula);
                p1.updatePlazas(p2.getCodigoPlaza(), new PlazasVO(matricula, 3));
                escribirFicheroPin(a2);
            }
        }
    }

    public static void retirarVehiculo(String matricula, int codPlaza, String pin) throws SQLException {
        PlazasDAO p1 = new PlazasDAO();
        PlazasVO p2 = new PlazasVO();
        TicketsDAO t1 = new TicketsDAO();
        TicketsVO t2 = new TicketsVO();
        VehiculoDAO v1 = new VehiculoDAO();
        VehiculoVO v2 = new VehiculoVO();

        if (p2.getCodigoPlaza() == codPlaza && p2.getMatricula().equalsIgnoreCase(matricula) && p1.findByPk(codPlaza) != null) {
            p2 = p1.findByPk(codPlaza);
            if (t1.findByPk(matricula, codPlaza) != null) {
                t2 = t1.findByPk(matricula, codPlaza);
                if (t2.getPin().equalsIgnoreCase(pin)) {
                    v2 = v1.findByPk(matricula);
                    Double precioMin = MetodosTickets.calcularPrecioMinuto(v2);
                    long tiempo = MetodosTickets.retirarTicket(t2.getTiempoInicio(), t2.getFechaInicio());
                    t1.updateTickets(codPlaza, matricula, new TicketsVO(codPlaza, matricula, pin, precioMin * tiempo, precioMin, Date.valueOf(t2.getFechaInicio()), Time.valueOf(t2.getTiempoInicio()), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now())));
                    p1.updatePlazas(codPlaza, new PlazasVO("", 2));
                }
            }
        }
    }

    public static void retirarVehiculoAbonado(String dni, String matricula, String pin) throws SQLException {
        PlazasDAO p1 = new PlazasDAO();
        PlazasVO p2 = new PlazasVO();
        AbonadosDAO a1 = new AbonadosDAO();
        AbonadosVO a2 = new AbonadosVO();

        a2 = a1.findByPk(dni);

        if (p1.findByFk(matricula) != null && a2.getMatricula().equalsIgnoreCase(matricula) && a2.getPinAbonados().equalsIgnoreCase(pin)) {
            p1.updatePlazas(p2.getCodigoPlaza(), new PlazasVO(p2.getMatricula(), 4));
        }

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

    public static void meterVehiculoNoAbonado(String matricula, int tipo) {
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
                                System.out.println("hola");
                                daoticket.insertTickets(MetodosTickets.crearTicket(matricula, j, tipo));
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
                                System.out.println("hola");
                                daoticket.insertTickets(MetodosTickets.crearTicket(matricula, j, tipo));
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

    }

    public static void retirarVehiculoNoAbonado(String matricula, int plaza, String pin) {
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
            } catch (SQLException ex) {
                Logger.getLogger(MetodosVehiculos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
