package org.sopt.jaksim.mset.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.mset.domain.CategoryMset;
import org.sopt.jaksim.mset.domain.TaskMset;
import org.sopt.jaksim.mset.repository.CategoryMsetRepository;
import org.sopt.jaksim.mset.repository.TaskMsetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskMsetService {
    private final TaskMsetRepository taskMsetRepository;
    public List<TaskMset> getByTaskId(Long taskId) {
        return taskMsetRepository.findByTaskId(taskId);
    }
}
