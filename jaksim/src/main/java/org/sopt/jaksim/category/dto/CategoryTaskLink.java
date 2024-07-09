package org.sopt.jaksim.category.dto;

import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.task.domain.Task;

import java.util.List;

public record CategoryTaskLink(
        Category category,
        List<Task> taskList
) {
    public static CategoryTaskLink of(Category category, List<Task> taskList) {
        return new CategoryTaskLink(category, taskList);
    }
}
