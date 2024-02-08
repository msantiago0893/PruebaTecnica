package com.app.prueba.ejercicio.Ejercicio4;

import com.app.prueba.enumm.CausaVisita;

import java.util.LinkedList;

public class Paciente {
  String nombre;
  LinkedList<Visita> visitas;

  public Paciente(String nombre) {
    this.nombre = nombre;
    this.visitas = new LinkedList<>();
  }

  public String nombre () {
    return nombre;
  }

  public void agregarVisita(Integer anio, CausaVisita causa) {
    visitas.add(new Visita(anio, causa));
  }

  public Visita visita(int i) {
    if (i >= 0 && i < visitas.size()) {
      return visitas.get(i);
    } else {
      return null;
    }
  }
}
