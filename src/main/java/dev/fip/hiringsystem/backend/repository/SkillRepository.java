package dev.fip.hiringsystem.backend.repository;

import dev.fip.hiringsystem.backend.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository 
  extends JpaRepository<Skill, Integer> {}
