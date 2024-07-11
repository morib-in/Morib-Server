package org.sopt.jaksim.task.repository;

import org.sopt.jaksim.task.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findByTargetDate(LocalDate targetDate);
    Todo findByUserIdAndTargetDate(Long userId, LocalDate targetDate);
}
