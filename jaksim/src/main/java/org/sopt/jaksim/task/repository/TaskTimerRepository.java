package org.sopt.jaksim.task.repository;

import org.sopt.jaksim.task.domain.TaskTimer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskTimerRepository extends JpaRepository<TaskTimer, Long> {
    List<TaskTimer> findByUserIdAndTargetDateAndTaskIdIn(Long userId, LocalDate targetDate, List<Long> taskId);
    Optional<TaskTimer> findByUserIdAndTargetDateAndTaskId(Long userId, LocalDate targetDate, Long taskId);
}
