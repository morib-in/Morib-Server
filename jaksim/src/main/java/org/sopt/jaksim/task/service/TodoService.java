package org.sopt.jaksim.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.task.domain.Task;
import org.sopt.jaksim.task.domain.TaskTimer;
import org.sopt.jaksim.task.domain.Todo;
import org.sopt.jaksim.task.domain.TodoTask;
import org.sopt.jaksim.task.dto.StartTimerRequest;
import org.sopt.jaksim.task.dto.StopTimerRequest;
import org.sopt.jaksim.task.dto.TaskInTodoCard;
import org.sopt.jaksim.task.dto.TodoCardResponse;
import org.sopt.jaksim.task.repository.TodoRepository;
import org.sopt.jaksim.user.facade.UserFacade;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserTimerService userTimerService;
    private final TaskTimerService taskTimerService;
    private final TodoTaskService todoTaskService;
    private final TaskService taskService;

    public void startTimer(LocalDate targetDate, StartTimerRequest startTimerRequest) {
//        Long userId = userFacade.getUserByPrincipal().getId();
        Long userId = 1L;
        Todo todo = Todo.init(userId);
        // 해당 날짜에 생성한 Todo가 없다면
        if (!isExist(targetDate)) {
            // Todo 생성
            todo.setTargetDate(targetDate);
            todo = todoRepository.save(todo);
        }
        // 있다면 가져오기
        else {
            todo = todoRepository.findByUserIdAndTargetDate(userId, targetDate);
        }
        todoTaskService.excute(todo, startTimerRequest.taskIdList());
    }

    public boolean isExist(LocalDate targetDate) {
        return todoRepository.findByTargetDate(targetDate) != null;
    }

    public TodoCardResponse getTodoCard(LocalDate targetDate) {
        //        Long userId = userFacade.getUserByPrincipal().getId();
        Long userId = 1L;
        Todo todo = todoRepository.findByUserIdAndTargetDate(userId, targetDate);
        List<TodoTask> todoTaskList = todoTaskService.getTodoTaskByTodoId(todo.getId());
        Map<Long, TodoTask> todoTaskMap = todoTaskService.getTodoTaskByTodoId(todo.getId()).stream().collect(Collectors.toMap(TodoTask::getTaskId, task -> task));
        int targetTime = userTimerService.getTotalTimeToday(String.valueOf(targetDate)).targetTime();
        List<Task> taskList = taskService.getTasksByTodoTask(todoTaskList);
        Map<Long, TaskTimer> taskTimerMap = taskTimerService.getTaskTimerMapByTaskList(taskList);
        List<TaskInTodoCard> taskInTodoCardList = new ArrayList<>();
        for (Task task : taskList) {
            taskInTodoCardList.add(TaskInTodoCard.of(task, taskTimerMap.get(task.getId())));
        }
        return TodoCardResponse.of(targetTime, taskInTodoCardList);
    }
}
