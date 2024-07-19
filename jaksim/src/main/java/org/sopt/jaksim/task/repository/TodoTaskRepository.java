package org.sopt.jaksim.task.repository;

import org.sopt.jaksim.task.domain.TodoTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoTaskRepository extends JpaRepository<TodoTask, Long> {
    List<TodoTask> findByTodoId(Long todoId);
    void deleteByTaskId(Long taskId);
}
