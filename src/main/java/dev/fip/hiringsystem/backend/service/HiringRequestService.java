package dev.fip.hiringsystem.backend.service;

import dev.fip.hiringsystem.backend.dto.request.HiringRequestDTO;
import dev.fip.hiringsystem.backend.model.*;
import dev.fip.hiringsystem.backend.repository.HiringRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class HiringRequestService {

  private final HiringRequestRepository hiringRequestRepository;
  private final AttachmentService attachmentService;

  public HiringRequestService(
      HiringRequestRepository hiringRequestRepository,
      AttachmentService attachmentService
  ){
    this.hiringRequestRepository = hiringRequestRepository;
    this.attachmentService = attachmentService;
  }

  public HiringRequest saveRequest(
      HiringRequestDTO hRequest,
      MultipartFile jobDesc,
      List<MultipartFile> skillDoc,
      List<MultipartFile> userDocs) throws Exception {
    HiringRequest req = HiringRequest.builder()
        .title(hRequest.getJobTitle())
        .role(Role.valueOf(hRequest.getRole()))
        .salary(hRequest.getSalary())
        .build();

    Location location = Location.builder()
        .country(hRequest.getLocation().getCountry())
        .city(hRequest.getLocation().getCity())
        .Address(hRequest.getLocation().getAddress()).build();
    location.setHiringLocation(req);
    req.setLocation(location);

    Attachment JD = attachmentService.getFileAttachment(jobDesc);
    JD.setDocType(DocType.JD);
    JD.setRequestAttachment(req);
    req.addUserAttachments(JD);

    userDocs.forEach((file) -> {
      try {
        Attachment userDoc = attachmentService.getFileAttachment(file);
        userDoc.setRequestAttachment(req);
        userDoc.setDocType(DocType.USER_DOC);
        req.addUserAttachments(userDoc);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    hRequest.getSkills().forEach(skillRequest -> {
      skillDoc.forEach(file -> {
        if(skillRequest.getDocName().equalsIgnoreCase(file.getOriginalFilename())){
          Skill skill = Skill.builder()
              .name(skillRequest.getName())
              .experience(skillRequest.getExperience()).build();
          try {
            Attachment skillD = attachmentService.getFileAttachment(file);
            skillD.setDocType(DocType.SKILL);
            skill.setDocument(skillD); skillD.setSkillAttachment(skill);
            skill.setHiringSkill(req);
            req.addSkills(skill);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
      });
    });



    return hiringRequestRepository.save(req);
  }
}
