package com.example.playground.Infrastructure.Service;

import com.example.playground.Infrastructure.Connector.FileExtractorFeignClient;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileExtractionService {
    private final FileExtractorFeignClient fileExtractorFeignClient;

    public Response.Body getExtractedFiles(String reqId) {
        Response response = fileExtractorFeignClient.extractFiles(reqId);
        validateResponse(response);
        return response.body();
    }

    private void validateResponse(Response response) {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            log.error("[FileExtractionService] File does not exist. Status: {}", response.status());
            throw new RuntimeException("File not found");
        } else if (response.status() != HttpStatus.OK.value()) {
            log.error("[FileExtractionService] Failed to retrieve files. Status: {}", response.status());
            throw new RuntimeException("Failed to retrieve files");
        }
    }
}
