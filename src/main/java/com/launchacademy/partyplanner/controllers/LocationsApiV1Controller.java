package com.launchacademy.partyplanner.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.launchacademy.partyplanner.services.LocationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.launchacademy.partyplanner.models.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/vq/locations")
public class LocationsApiV1Controller {
  private LocationService locationService;

  @Autowired
  public LocationsApiV1Controller(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping
  public Page<Location> getLocationsApi(Pageable pageable){
    return locationService.findAll(pageable);
  }

//  @GetMapping
//  public Map<String, Iterable<Location>> getLocationsApi(Pageable pageable){
//    Map<String, Iterable<Location>> locationsApi = new HashMap<>();
//    locationsApi.put("locations", this.locationService.findAll(pageable));
//    return locationsApi;
//  }

  @NoArgsConstructor
  private class LocationNotFoundException extends RuntimeException {};

  @ControllerAdvice
  private class LocationNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String locationNotFoundHandler(LocationNotFoundException ex) {
      return ex.getMessage();
    }
  }

  @GetMapping("/{id}")
  public Location getOne(@PathVariable Integer id) {
    return locationService.findById(id).orElseThrow(() -> new LocationNotFoundException());
  }
}
