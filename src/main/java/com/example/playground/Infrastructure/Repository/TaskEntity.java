package com.example.playground.Infrastructure.Repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SCHEDULE_TASK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity extends AbstractBaseEntity{
    private String taskName;
    private String target;
    private String cronExpression;
    private Boolean enabled;
}
