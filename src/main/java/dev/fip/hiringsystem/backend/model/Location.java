package dev.fip.hiringsystem.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "locationID")
  @Getter
  private int locationID;

  @Getter
  @Setter
  private String country;

  @Getter
  @Setter
  private String city;

  @Getter
  @Setter
  private String Address;

  @OneToOne
  @JoinColumn(name = "requestID")
  @Setter
  private HiringRequest hiringLocation;
}
