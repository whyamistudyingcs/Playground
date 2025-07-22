package com.example.playground.Infrastructure.Connector.testFeignClient;

import com.example.playground.Infrastructure.Connector.testFeignClient.dto.DirDto;

import feign.Headers;
import feign.RequestLine;

public interface TestFeignClient {
    @RequestLine("POST /testRequest")
    @Headers("Content-Type: application/json")
    DirDto testEndpoint(DirDto dirDto);
}
