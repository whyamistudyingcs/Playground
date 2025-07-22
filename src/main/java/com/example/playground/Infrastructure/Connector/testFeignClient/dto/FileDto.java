package com.example.playground.Infrastructure.Connector.testFeignClient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    private String name;
    private String path;
    private String type;
    private long size;
}
