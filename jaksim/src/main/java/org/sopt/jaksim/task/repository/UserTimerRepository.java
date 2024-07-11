package org.sopt.jaksim.task.repository;

import org.sopt.jaksim.task.domain.UserTimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserTimerRepository extends JpaRepository<UserTimer, Long> {
    UserTimer findByUserIdAndTargetDate(Long userId, String targetDate);
    UserTimer findByUserId(Long userId);
}
