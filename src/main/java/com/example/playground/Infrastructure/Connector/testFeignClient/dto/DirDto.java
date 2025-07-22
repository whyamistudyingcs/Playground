package com.example.playground.Infrastructure.Connector.testFeignClient.dto;

import java.util.List;
import lombok.Data;

@Data
public class DirDto {
    private String name;
    private List<FileDto> files;
}
