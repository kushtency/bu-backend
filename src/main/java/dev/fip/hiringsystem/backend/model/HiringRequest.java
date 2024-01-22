package dev.fip.hiringsystem.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HiringRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "requestID")
  @Getter
  private int requestID;

  @Getter
  @Setter
  private Role role;

  @Getter
  @Setter
  private int salary;

  @Getter
  @Setter
  private String title;

  @OneToOne(mappedBy = "hiringLocation", cascade = CascadeType.ALL)
  @Getter
  @Setter
  private Location location;

  @OneToMany(mappedBy = "hiringSkill", cascade = CascadeType.ALL)
  @Getter
  @Setter
  private List<Skill> skills;

  @OneToMany(mappedBy = "requestAttachment", cascade = CascadeType.ALL)
  @Getter
  @Setter
  private List<Attachment> userAttachments;

  public void addUserAttachments(Attachment attachment){
    if(userAttachments == null){
      this.userAttachments = new ArrayList<>();
    }
    this.userAttachments.add(attachment);
  }

  public void addSkills(Skill skill){
    if(skills == null){
      this.skills = new ArrayList<>();
    }
    this.skills.add(skill);
  }
}
