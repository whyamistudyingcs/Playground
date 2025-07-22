package com.example.playground.Interfaces;


import com.example.playground.Infrastructure.Connector.testFeignClient.TestFeignClient;
import com.example.playground.Infrastructure.Connector.testFeignClient.dto.DirDto;
import com.example.playground.Infrastructure.Connector.testFeignClient.dto.FileDto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    private final TestFeignClient testFeignClient;

    @Autowired
    public TestController(TestFeignClient testFeignClient) {
        this.testFeignClient = testFeignClient;
    }

    @PostMapping("/test-feign")
    public String testFeign() {
        DirDto dummyDirDto = new DirDto();
        // Set dummy values for testing
        dummyDirDto.setName("dummyName");
        dummyDirDto.setFiles(Arrays.asList(
            new FileDto("file1.txt", "/dummy/path/file1.txt", "text/plain", 1234),
            new FileDto("file2.jpg", "/dummy/path/file2.jpg", "image/jpeg", 5678)
        ));
        DirDto response = testFeignClient.testEndpoint(dummyDirDto);
        return "OK";
    }

    @PostMapping("/testRequest")
    public ResponseEntity<DirDto> testRequest(@RequestBody DirDto dirDto) {
        return ResponseEntity.ok(dirDto);
    }
}