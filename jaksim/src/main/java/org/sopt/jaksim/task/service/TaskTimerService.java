package org.sopt.jaksim.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.global.exception.NotFoundException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.task.domain.Task;
import org.sopt.jaksim.task.domain.TaskTimer;
import org.sopt.jaksim.task.dto.StopTimerRequest;
import org.sopt.jaksim.task.repository.TaskTimerRepository;
import org.sopt.jaksim.user.facade.UserFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskTimerService {
    private final TaskTimerRepository taskTimerRepository;
    private final UserFacade userFacade;

    public void calculateTaskTimerOnStop(Long taskId, StopTimerRequest stopTimerRequest) {
//        userFacade.getUserByPrincipal().getId()
        TaskTimer taskTimer = taskTimerRepository.findByUserIdAndTargetDateAndTaskId(1L, stopTimerRequest.targetDate(), taskId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.NOT_FOUND)
        );
        taskTimer.setTargetTime(taskTimer.getTargetTime() + stopTimerRequest.elapsedTime());
        taskTimerRepository.save(taskTimer);
    }

    public Map<Long, TaskTimer> getTaskTimerMapByTaskList(Long userId, LocalDate targetDate, List<Task> taskList) {
        List<Long> taskIdList = taskList.stream().map(Task::getId).collect(Collectors.toList());
        return taskTimerRepository.findByUserIdAndTargetDateAndTaskIdIn(userId, targetDate, taskIdList).stream().collect(Collectors.toMap(TaskTimer::getTaskId, task -> task));
    }

    public int getTaskTimeByTaskId(Long userId, LocalDate targetDate, Long taskId) {
        TaskTimer taskTimer = taskTimerRepository.findByUserIdAndTargetDateAndTaskId(userId, targetDate, taskId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.NOT_FOUND)
        );
        return taskTimer.getTargetTime();
    }
}
