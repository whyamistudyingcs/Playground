package com.example.playground.Infrastructure.Connector;

import com.example.playground.Infrastructure.Connector.dto.HealthDto;
import feign.Param;
import feign.RequestLine;
import feign.Response;

// https://stackoverflow.com/questions/29985205/using-requestline-with-feign
public interface FileExtractorFeignClient {
    @RequestLine("GET file/download/{reqId}")
    Response extractFiles(@Param("reqId") String reqId);

    @RequestLine("GET Health")
    HealthDto checkFileExtractorHealth();
}
