package dev.fip.hiringsystem.backend.service;

import dev.fip.hiringsystem.backend.model.Skill;
import dev.fip.hiringsystem.backend.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
  private final SkillRepository skillRepository;

  @Autowired
  public SkillService(SkillRepository skillRepository){
    this.skillRepository = skillRepository;
  }

  public Skill saveSkill(Skill skill) {
    return skillRepository.save(skill);
  }
}
