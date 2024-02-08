package com.app.prueba.ejercicio.Ejercicio2;

import com.app.prueba.ejercicio.Ejercicio1.LongitudPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TorneoTenis {
  private static final Logger log = LoggerFactory.getLogger(TorneoTenis.class);
  public static Integer calcularJuegos(int jugadores, int canchas) {
    return Math.min(jugadores / 2, canchas);
  }

  public static void main(String[] args) {

    Integer juegosEnParalelo = calcularJuegos(10, 3);

    log.info("Número máximo de juegos en paralelo: {}", juegosEnParalelo);
  }
}
