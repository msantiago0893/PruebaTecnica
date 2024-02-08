package com.app.prueba.ejercicio.Ejercicio4;

import com.app.prueba.ejercicio.Ejercicio2.TorneoTenis;
import com.app.prueba.enumm.CausaVisita;
import com.app.prueba.exception.FormatoDeFicheroIncorrecto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedList;

public class ManejoPacientes {
  private static final Logger log = LoggerFactory.getLogger(ManejoPacientes.class);

  public static void guardarPacientes(String nomFich, LinkedList<Paciente> pacientes) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFich))) {
      for (Paciente paciente : pacientes) {
        writer.write("Nombre: " + paciente.nombre);
        writer.newLine();
        writer.write("Visitas:");
        writer.newLine();
        for (Visita visita : paciente.visitas) {
          writer.write("Año: " + visita.anio);
          writer.newLine();
          writer.write("Causa: " + visita.causa);
          writer.newLine();
        }
        writer.newLine();
      }
    } catch (IOException e) {
      log.error("Se ha producido un error al guardar el archivo: {}", e.getMessage());
      e.printStackTrace();
    }
  }

  public static LinkedList<Paciente> leerPacientes(String nomFich) throws FormatoDeFicheroIncorrecto {
    LinkedList<Paciente> pacientes = new LinkedList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(nomFich))) {
      String line;
      Paciente paciente = null;

      while ((line = reader.readLine()) != null) {
        if (line.startsWith("Nombre: ")) {
          if (paciente != null) {
            pacientes.add(paciente);
          }
          paciente = new Paciente(line.substring("Nombre: ".length()));
        } else if (line.equals("Visitas:")) {
          if (paciente == null) {
            throw new FormatoDeFicheroIncorrecto("Error en el formato del fichero.");
          }
        } else if (line.startsWith("Año: ")) {
          int anio = Integer.parseInt(line.substring("Año: ".length()));
          String causaStr = reader.readLine();
          if (causaStr == null || !causaStr.startsWith("Causa: ")) {
            throw new FormatoDeFicheroIncorrecto("Error en el formato del fichero.");
          }
          causaStr = causaStr.substring("Causa: ".length());
          CausaVisita causa = CausaVisita.valueOf(causaStr);
          paciente.agregarVisita(anio, causa);
        }
      }

      if (paciente != null) {
        pacientes.add(paciente);
      }

      return pacientes;
    } catch (IOException | IllegalArgumentException e) {
      throw new FormatoDeFicheroIncorrecto("Error al leer el fichero: " + e.getMessage());
    }
  }


  public static void visualizarArchivo(String nombreArchivo) {
    try {
      LinkedList<Paciente> pacientesLeidos = leerPacientes(nombreArchivo);

      for (Paciente paciente : pacientesLeidos) {
        log.info("Nombre: {}", paciente.nombre());
        log.info("Visitas:");
        for (int i = 0; i < paciente.visitas.size(); i++) {
          Visita visita = paciente.visita(i);
          if (visita != null) {
            log.info(" Año: {}", visita.anio);
            log.info(" Causa: {}", visita.causa);
          }
        }
        log.info("");
      }
    } catch (FormatoDeFicheroIncorrecto e) {
      log.error("Error al leer el archivo");
    }
  }

  public static void visualizarVisitaIesima(Paciente paciente, int posicionVisita) {
    log.info("Visualizar visita i-ésima");
    Visita visitaEnPosicion = paciente.visita(posicionVisita);

    if (visitaEnPosicion != null) {
      log.info(" Visita en posición: {}", posicionVisita);
      log.info(" Año: {}", visitaEnPosicion.anio);
      log.info(" Causa: {}", visitaEnPosicion.causa);
    } else {
      log.info("null");
    }
  }

  public static void main(String[] args) {
    LinkedList<Paciente> pacientes = new LinkedList<>();
    Paciente paciente1 = new Paciente("Juan García");
    paciente1.agregarVisita(2023, CausaVisita.CONSULTA);
    paciente1.agregarVisita(2022, CausaVisita.ENFERMEDAD);

    Paciente paciente2 = new Paciente("Jose Morelos");
    paciente2.agregarVisita(2018, CausaVisita.ENFERMEDAD);
    paciente2.agregarVisita(2019, CausaVisita.TRAUMATISMO);

    pacientes.add(paciente1);
    pacientes.add(paciente2);

    guardarPacientes("pacientes.txt", pacientes);

    visualizarArchivo("pacientes.txt");

    Paciente paciente = pacientes.get(0);
    int posicionVisita = 1;
    visualizarVisitaIesima(paciente, posicionVisita);
  }
}
