package dev.fip.hiringsystem.backend.controller;

import dev.fip.hiringsystem.backend.dto.response.AttachmentResponseData;
import dev.fip.hiringsystem.backend.model.Attachment;
import dev.fip.hiringsystem.backend.service.AttachmentService;
import jakarta.validation.Valid;
import java.io.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// testing
@RestController
@RequestMapping("attachment/")
@Valid
public class AttachmentController {

  private final AttachmentService attachmentService;

  @Autowired
  public AttachmentController(AttachmentService attachmentService) {
    this.attachmentService = attachmentService;
  }

  @PostMapping("upload")
  public ResponseEntity<AttachmentResponseData> uploadDocument(
    @RequestParam("file") MultipartFile file
  ) throws Exception {
    Attachment attachment = null;
    attachment = attachmentService.getFileAttachment(file);
    attachmentService.saveAttachment(attachment);
    String downloadURL = ServletUriComponentsBuilder
      .fromCurrentContextPath()
      .path("/attachment/download/")
      .path(String.valueOf(attachment.getAttachmentID()))
      .toUriString();

    return ResponseEntity.ok(
      AttachmentResponseData
        .builder()
        .fileName(attachment.getFileName())
        .fileSize(attachment.getFileSize())
        .fileType(attachment.getFileType())
        .url(downloadURL)
        .build()
    );
  }

  @GetMapping("download/{fileID}")
  public ResponseEntity<Resource> getAttachment(
    @PathVariable(name = "fileID") int fileID
  ) throws FileNotFoundException {
    Attachment attachment = attachmentService.getAttachment(fileID);
    return ResponseEntity
      .ok()
      .contentType(MediaType.parseMediaType(attachment.getFileType()))
      .header(
        HttpHeaders.CONTENT_DISPOSITION,
        "attachment : filename=\"" + attachment.getFileName() + "\""
      )
      .body(new ByteArrayResource(attachment.getData()));
  }
}
