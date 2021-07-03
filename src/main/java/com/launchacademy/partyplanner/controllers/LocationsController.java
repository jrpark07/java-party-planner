package com.launchacademy.partyplanner.controllers;

import com.launchacademy.partyplanner.models.Location;
import com.launchacademy.partyplanner.models.Party;
import com.launchacademy.partyplanner.services.LocationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Controller
@RequestMapping("/locations")
public class LocationsController {

  private LocationService locationService;

  @Autowired
  public LocationsController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping
  public String getLocationsIndex(Model model) {
//    Iterable<Location> locations = locationService.findAll();
    model.addAttribute("locations", locationService.findAll());
    return "locations/index";
  }

  @GetMapping("/{id}")
  public String getLocationShow(@PathVariable Integer id, Model model){
    Optional<Location> location = locationService.findById(id);
    if(location.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } else {
      model.addAttribute("location", location.get());
      List<Party> parties = location.get().getParties();
      if(parties.size() > 0) {
        model.addAttribute("parties", parties);
      }
    }
    return "locations/show";
  }

  @GetMapping("/new")
  public String getNewLocation(Model model) {
    Location location = new Location();
    model.addAttribute("location", location);
    return "locations/new";
  }

  @PostMapping
  public String createLocation(@ModelAttribute @Valid Location location, BindingResult bindingResult, Pageable pageable) {
    if (bindingResult.hasErrors()) {
      return "locations/new";
    } else {
      try{
      locationService.save(location);
      return "redirect:/locations";
      }catch (Exception exception) {
      exception.printStackTrace();
      return "locations/new";
      }
    }
  }
}


