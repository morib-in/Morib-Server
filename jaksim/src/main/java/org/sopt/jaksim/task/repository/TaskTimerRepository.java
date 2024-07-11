package org.sopt.jaksim.task.repository;

import org.sopt.jaksim.task.domain.TaskTimer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTimerRepository extends JpaRepository<TaskTimer, Long> {
    List<TaskTimer> findByTaskIdIn(List<Long> taskId);
}
