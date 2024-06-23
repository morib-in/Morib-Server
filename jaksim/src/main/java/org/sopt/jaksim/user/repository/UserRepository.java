package org.sopt.jaksim.user.repository;

import org.sopt.jaksim.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
