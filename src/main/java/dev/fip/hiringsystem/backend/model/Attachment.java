package dev.fip.hiringsystem.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "attachmentID")
  @Getter
  private int attachmentID;

  @Lob
  @Column(columnDefinition = "mediumblob")
  @Getter
  @Setter
  private byte[] data;

  @Getter
  @Setter
  private String fileType;

  @Getter
  @Setter
  private long fileSize;

  @Getter
  @Setter
  private String fileName;

  @Getter
  @Setter
  private LocalDateTime uploadedAt;

  @Getter
  @Setter
  private DocType docType;

  @ManyToOne
  @JoinColumn(name = "requestID")
  @Setter
  private HiringRequest requestAttachment;

  @OneToOne
  @JoinColumn(name = "skillID")
  @Setter
  private Skill skillAttachment;
}
