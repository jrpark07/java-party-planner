package com.launchacademy.partyplanner.controllers;

import com.launchacademy.partyplanner.models.Party;
import com.launchacademy.partyplanner.services.LocationService;

import com.launchacademy.partyplanner.services.PartyService;
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
@RequestMapping("/parties")
public class PartiesController {

  private PartyService partyService;
  private LocationService locationService;

  @Autowired
  public PartiesController(PartyService partyService, LocationService locationService) {
    this.partyService = partyService;
    this.locationService = locationService;
  }

  @GetMapping("/{id}")
  public String getPartyShow(@PathVariable Integer id, Model model){
    try {
      model.addAttribute("party", partyService.findById(id));
    } catch (IndexOutOfBoundsException err) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return "parties/show";
  }

  @GetMapping("/new")
  public String getNewParty(@ModelAttribute Party party, Model model, Pageable pageable) {
    model.addAttribute("locations", locationService.findAll(pageable));
    return "parties/new";
  }

  @PostMapping
  public String createParty(@ModelAttribute @Valid Party party, Model model, BindingResult bindingResult, Pageable pageable) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("locations", locationService.findAll(pageable));
      return "parties/new";
    } else {
      partyService.save(party);
      return "redirect:/parties/" + party.getId();
    }
  }
}
