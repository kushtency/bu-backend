package dev.fip.hiringsystem.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "skillID")
  @Getter
  private int skillID;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private int experience;

  @OneToOne(mappedBy = "skillAttachment", cascade = CascadeType.ALL)
  @Getter
  @Setter
  // File validation
  private Attachment document;

  @ManyToOne
  @JoinColumn(name = "requestID", nullable = false)
  @Setter
  private HiringRequest hiringSkill;
}
