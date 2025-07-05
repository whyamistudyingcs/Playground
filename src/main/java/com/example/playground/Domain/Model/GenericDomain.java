package com.example.playground.Domain.Model;

import lombok.Data;

import java.util.Date;

@Data
public class GenericDomain {
    private Long id;
    private Date createdDate;
    private Date modifiedDate;

    // This should be enabled after implementing auditorAware
//    private String updatedBy;
//    private String createdBy;
}
