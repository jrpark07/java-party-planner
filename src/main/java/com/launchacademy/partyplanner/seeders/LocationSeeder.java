package com.launchacademy.partyplanner.seeders;

import com.launchacademy.partyplanner.models.Location;

import com.launchacademy.partyplanner.models.Party;
import com.launchacademy.partyplanner.services.LocationService;

import com.launchacademy.partyplanner.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class LocationSeeder implements CommandLineRunner {
  private LocationService locationService;
  private PartyService partyService;

  @Autowired
  public LocationSeeder(LocationService locationService, PartyService partyService) {
   this.locationService = locationService;
   this.partyService = partyService;
  }

  @Override
  public void run(String... args) throws Exception {
      Location location1 = new Location();
      Location location2 = new Location();
      Party party1 = new Party();
      Party party2 = new Party();
      Party party3 = new Party();

    if (locationService.findAll().size() ==0 ) {
      location1.setName("Launch");
      location1.setCity("Boston");
      location1.setState("MA");
      location1.setRentalPrice(72.59);
      locationService.save(location1);

      location2.setName("5guys");
      location2.setState("NY");
      location2.setCity("New York");
      location2.setRentalPrice(88.79);
      locationService.save(location2);
    }

    if (partyService.findAll().size() ==0 ) {
      party1.setName("Ceremony");
      party1.setDescription("CA");
      party1.setLocation(location1);
      partyService.save(party1);

      party2.setName("new years party");
      party2.setDescription("starts at 10");
      party2.setLocation(location2);
      partyService.save(party2);

      party3.setName("birthday party");
      party3.setDescription("50 years!");
      party3.setLocation(location2);
      partyService.save(party3);

    }
  }
}
