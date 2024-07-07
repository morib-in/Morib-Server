package org.sopt.jaksim.task.repository;

import org.sopt.jaksim.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
