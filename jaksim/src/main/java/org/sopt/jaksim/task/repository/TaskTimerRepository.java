package org.sopt.jaksim.task.repository;

import org.sopt.jaksim.task.domain.TaskTimer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTimerRepository extends JpaRepository<TaskTimer, Long> {
}
