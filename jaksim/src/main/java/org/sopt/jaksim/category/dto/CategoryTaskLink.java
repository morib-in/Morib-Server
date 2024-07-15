package org.sopt.jaksim.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.task.domain.Task;

import java.util.List;

public record CategoryTaskLink(
        @JsonProperty("category")
        CategoryCheckResponse categoryCheckResponse,
        @JsonProperty("tasks")
        List<TaskWithTaskTimer> taskWithTaskTimerList
) {
    public static CategoryTaskLink of(CategoryCheckResponse categoryCheckResponse, List<TaskWithTaskTimer> taskWithTaskTimerList) {
        return new CategoryTaskLink(categoryCheckResponse, taskWithTaskTimerList);
    }
}
