package org.sopt.jaksim.task.dto;

import java.time.LocalDate;

public record TaskCreateRequest (
    String name,
    LocalDate startDate,
    LocalDate endDate
)
{

}