package org.sopt.jaksim.category.dto;

import java.time.LocalDate;

public record CategoryCheckResponse(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate) {
    public static CategoryCheckResponse of(Long id, String name, LocalDate startDate, LocalDate endDate) {
        return new CategoryCheckResponse(id, name, startDate, endDate);
    }
}
