package com.launchacademy.partyplanner.services;

import com.launchacademy.partyplanner.models.Location;
import com.launchacademy.partyplanner.repositories.LocationRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

@Service
public class LocationService {
  private LocationRepository locationRepository;

  @Autowired
  public LocationService(
          LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  public void save(Location location) {
    locationRepository.save(location);
  }

  public Optional<Location> findById(Integer id) {
    return locationRepository.findById(id);
  }

  public Page<Location> findAll(Pageable pageable) {
    return locationRepository.findAll(pageable);
  }

  public List<Location> findAll() {
    return (List<Location>) locationRepository.findAll();
  }

}
