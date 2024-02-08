package com.app.prueba.service;

import com.app.prueba.model.request.LocationRequest;
import com.app.prueba.model.response.LocationResponse;

import java.util.List;

public interface ILocationService {
  public List<LocationResponse> findAll();
  public void save(LocationRequest location);
}
