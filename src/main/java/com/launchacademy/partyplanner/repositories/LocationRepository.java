package com.launchacademy.partyplanner.repositories;

import com.launchacademy.partyplanner.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {
  List<Location> findAllByName(String name);
}
