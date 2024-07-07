package org.sopt.jaksim.category.dto;

import org.sopt.jaksim.mset.domain.Mset;

import java.util.List;

public record CategoryCreateRequest (
        String name,
        String startDate,
        String endDate,
        List<Mset> msets
)
{

}



