package org.sopt.jaksim.category.dto;

import org.sopt.jaksim.mset.domain.Mset;
import org.sopt.jaksim.mset.domain.MsetDTO;
import org.sopt.jaksim.task.domain.Task;

import java.util.List;

public record TaskMsetLinkResponse(
        Task task,
        List<MsetDTO> msets
) {
    public static TaskMsetLinkResponse of(Task task, List<MsetDTO> msets) {
        return new TaskMsetLinkResponse(task, msets);
    }
}
