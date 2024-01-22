package dev.fip.hiringsystem.backend.repository;

import dev.fip.hiringsystem.backend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository 
  extends JpaRepository<Location, Integer> {}
