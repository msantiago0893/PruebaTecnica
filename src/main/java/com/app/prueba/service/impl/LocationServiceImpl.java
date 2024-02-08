package com.app.prueba.service.impl;

import com.app.prueba.entity.LocationEntity;
import com.app.prueba.model.request.LocationRequest;
import com.app.prueba.model.response.LocationResponse;
import com.app.prueba.repository.LocationRepository;
import com.app.prueba.service.ILocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements ILocationService {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private LocationRepository locationRepository;

  @Override
  public List<LocationResponse> findAll() {
    log.info("location service - Fetching all users");
    List<LocationEntity> listLocations = locationRepository.findAll();

    return listLocations.stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
  }

  private LocationResponse convertToResponse(LocationEntity locationEntity) {
    LocationResponse response = new LocationResponse();
    response.setId(locationEntity.getId());
    response.setNombre(locationEntity.getNombre());
    response.setLatitud(locationEntity.getLatitud());
    response.setLongitud(locationEntity.getLongitud());
    return response;
  }

  @Override
  public void save(LocationRequest location) {
    log.info("location service - save user");

    LocationEntity locationSave = new LocationEntity();
    locationSave.setLatitud(location.getLatitud());
    locationSave.setLongitud(location.getLongitud());
    locationSave.setNombre(location.getNombre());
    locationRepository.save(locationSave);
  }
}
