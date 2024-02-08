package com.app.prueba.model.request;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LocationRequest {
  @NotNull
  @DecimalMin("-90.0")
  @DecimalMax("90.0")
  private Double latitud;

  @NotNull
  @DecimalMin("-180.0")
  @DecimalMax("180.0")
  private Double longitud;

  @NotNull
  @Size(min = 1, max = 255)
  private String nombre;

  public Double getLatitud() {
    return latitud;
  }

  public void setLatitud(Double latitud) {
    this.latitud = latitud;
  }

  public Double getLongitud() {
    return longitud;
  }

  public void setLongitud(Double longitud) {
    this.longitud = longitud;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
