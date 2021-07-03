package com.launchacademy.partyplanner.services;

import com.launchacademy.partyplanner.models.Location;
import com.launchacademy.partyplanner.models.Party;
import com.launchacademy.partyplanner.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PartyService {
  private PartyRepository partyRepository;

  @Autowired
  public PartyService(PartyRepository partyRepository) {
    this.partyRepository = partyRepository;
  }

  public List<Party> findAll() {
    return (List<Party>) this.partyRepository.findAll();
  }

  public void save(Party party) {
    partyRepository.save(party);
  }

  public Party findById(Integer id) {
    return partyRepository.findById(id).get();
  }
}
