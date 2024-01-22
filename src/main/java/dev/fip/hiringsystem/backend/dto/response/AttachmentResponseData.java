package dev.fip.hiringsystem.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AttachmentResponseData {

  private String url;
  private String fileName;
  private String fileType;
  private long fileSize;
}
