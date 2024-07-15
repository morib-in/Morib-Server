package org.sopt.jaksim.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
public record FilteredResourceResponse(
    LocalDate date,
    @JsonProperty("categories")
    List<CategoryTaskLink> categoryTaskLinkList)
{
    public static FilteredResourceResponse of(LocalDate date, List<CategoryTaskLink> categoryTaskLinkList) {
        return new FilteredResourceResponse(date, categoryTaskLinkList);
    }

    public static FilteredResourceResponse init(LocalDate date) {
        return new FilteredResourceResponse(date, new ArrayList<>());
    }
}
