package dev.fip.hiringsystem.backend.service;

import dev.fip.hiringsystem.backend.model.Attachment;
import dev.fip.hiringsystem.backend.repository.AttachmentRepository;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentService {

  private final AttachmentRepository attachmentRepository;

  @Autowired
  public AttachmentService(AttachmentRepository attachmentRepository) {
    this.attachmentRepository = attachmentRepository;
  }

  public Attachment saveAttachment(Attachment attachment) {
    return attachmentRepository.save(attachment);
  }

  public Attachment getFileAttachment(MultipartFile file) throws Exception {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    try {
      if (fileName.contains("..")) {
        throw new InvalidFileNameException(
                fileName, "filename contains invalid path sequence"
        );
      }
      
      Attachment attachment = Attachment
        .builder()
        .fileName(fileName)
        .fileSize(file.getSize())
        .fileType(file.getContentType())
        .data(file.getBytes())
        .uploadedAt(LocalDateTime.now())
        .build();

      return attachment;
    } catch (Exception e) {
      throw new Exception("Could not save file");
    }
  }

  public Attachment getAttachment(int fileID) throws FileNotFoundException {
    return attachmentRepository
      .findById(fileID)
      .orElseThrow(() ->
        new FileNotFoundException("file not found with id" + fileID)
      );
  }
}
