package dev.fip.hiringsystem.backend.repository;

import dev.fip.hiringsystem.backend.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository
  extends JpaRepository<Attachment, Integer> {}
