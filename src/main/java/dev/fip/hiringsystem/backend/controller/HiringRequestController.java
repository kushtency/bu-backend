package dev.fip.hiringsystem.backend.controller;

import dev.fip.hiringsystem.backend.dto.request.HiringRequestDTO;
import dev.fip.hiringsystem.backend.model.HiringRequest;
import dev.fip.hiringsystem.backend.service.HiringRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("request/")
public class HiringRequestController {

    private final HiringRequestService hiringRequestService;

    @Autowired
    public HiringRequestController(HiringRequestService hiringRequestService) {
      this.hiringRequestService = hiringRequestService;
    }

    @PostMapping("")
    public ResponseEntity<Object> postHiringRequest(
            @Valid @RequestPart("hiringRequest") HiringRequestDTO hiringRequest,
            @RequestPart("jobDesc") MultipartFile jobDesc,
            @RequestPart("skills") List<MultipartFile> skillDoc,
            @RequestPart("userDocs") List<MultipartFile> userDocs
    ) throws Exception {
        HiringRequest req = hiringRequestService.saveRequest(hiringRequest, jobDesc, skillDoc, userDocs);
        return ResponseEntity.ok(req);
    }
}
