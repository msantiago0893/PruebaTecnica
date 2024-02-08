package com.app.prueba.controller;

import com.app.prueba.model.request.LocationRequest;
import com.app.prueba.model.response.LocationResponse;
import com.app.prueba.service.ILocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/locations")
public class LocationController {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private ILocationService locationService;

  @GetMapping("/search")
  public List<LocationResponse> searchLocations(
    @RequestParam(name = "access_token") String accessToken,
    @RequestParam Double lat,
    @RequestParam Double lng
  ) {
    log.info("Fetching all locations");

    return locationService.findAll();
  }

  @PostMapping
  public ResponseEntity<?> save(
    @RequestBody @Valid @NotNull LocationRequest locationRequest,
    BindingResult bindingResult
  ) {
    log.info("Creating location: {}", locationRequest);

    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body("Verifique qué todos los campos sean válidos");
    }

    this.locationService.save(locationRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con exito");
  }
}
