package org.sopt.jaksim.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.global.exception.NotFoundException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.task.domain.TaskTimer;
import org.sopt.jaksim.task.dto.StopTimerRequest;
import org.sopt.jaksim.task.repository.TaskTimerRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskTimerService {
    private final TaskTimerRepository taskTimerRepository;

    public void calculateTaskTimerOnStop(Long taskId, StopTimerRequest stopTimerRequest) {
        TaskTimer taskTimer = taskTimerRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.NOT_FOUND)
        );
        taskTimer.setTargetTime(taskTimer.getTargetTime() + stopTimerRequest.elapsedTime());
        taskTimerRepository.save(taskTimer);
    }
}
