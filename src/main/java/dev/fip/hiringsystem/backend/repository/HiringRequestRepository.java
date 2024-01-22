package dev.fip.hiringsystem.backend.repository;

import dev.fip.hiringsystem.backend.model.HiringRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiringRequestRepository
  extends JpaRepository<HiringRequest, Integer> {}
