package org.sopt.jaksim.mset.repository;

import org.sopt.jaksim.mset.domain.TaskMset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskMsetRepository extends JpaRepository<TaskMset, Long> {
    List<TaskMset> findByTaskId(Long taskId);
}
