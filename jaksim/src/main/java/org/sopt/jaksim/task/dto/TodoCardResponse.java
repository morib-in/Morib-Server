package org.sopt.jaksim.task.dto;

import java.util.List;

public record TodoCardResponse(
        int totalTimeOfToday,
        List<TaskInTodoCard> task
) {
    public static TodoCardResponse of(int targetTime, List<TaskInTodoCard> taskInTodoCardList) {
        return new TodoCardResponse(targetTime, taskInTodoCardList);
    }
}
