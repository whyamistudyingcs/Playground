package com.example.playground.Interfaces;

import com.example.playground.Infrastructure.Connector.FileExtraction.FileExtractorFeignClient;
import com.example.playground.Infrastructure.Connector.dto.HealthDto;
import com.example.playground.Infrastructure.Service.FileExtractionService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/fileExtractor")
@RequiredArgsConstructor
@Slf4j
public class FileExtractRequestController {
    private final FileExtractorFeignClient fileExtractorFeignClient;
    private final FileExtractionService fileExtractionService;

    @GetMapping("/health")
    public HealthDto checkHealth() {
        HealthDto healthStatus = fileExtractorFeignClient.checkFileExtractorHealth();
        return healthStatus;
    }

    @GetMapping("/file/download/{reqId}")
    public ResponseEntity<InputStreamResource> downloadFiles(@PathVariable String reqId) throws IOException {
        Response.Body responseBody = fileExtractionService.getExtractedFiles(reqId);
        InputStreamResource resource = new InputStreamResource(responseBody.asInputStream());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=zipFile_" + reqId + ".zip");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }




}
