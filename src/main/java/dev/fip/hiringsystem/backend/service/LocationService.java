package dev.fip.hiringsystem.backend.service;

import dev.fip.hiringsystem.backend.model.Location;
import dev.fip.hiringsystem.backend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
  private final LocationRepository locationRepository;

  @Autowired
  public LocationService(LocationRepository locationRepository){
    this.locationRepository = locationRepository;
  }

  public Location saveLocation(Location location) {
    return locationRepository.save(location);
  }
}
