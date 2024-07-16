package org.sopt.jaksim.category.dto;

import org.sopt.jaksim.mset.domain.Mset;

import java.time.LocalDate;
import java.util.List;

public record CategoryCreateRequest (
        String name,
        LocalDate startDate,
        LocalDate endDate,
        List<Mset> msets
)
{

}



