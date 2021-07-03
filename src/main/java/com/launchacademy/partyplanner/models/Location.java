package com.launchacademy.partyplanner.models;

import com.google.inject.internal.util.Lists;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "locations")
public class Location {

  @Id
  @SequenceGenerator(name = "location_generator",
          sequenceName = "locations_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "location_generator")
  @Column(name = "id", nullable = false, unique = true, columnDefinition = "serial")
  private Integer id;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank
  @Column(name = "city", nullable = false)
  private String city;

  @NotBlank
  @Column(name = "state", nullable = false)
  private String state;

  @Column(name = "rental_price", columnDefinition = "numeric")
  private Double rentalPrice;

  @OneToMany(mappedBy="location")
  @JsonIgnoreProperties("location" )
  private List<Party> parties = new ArrayList<>();
}
