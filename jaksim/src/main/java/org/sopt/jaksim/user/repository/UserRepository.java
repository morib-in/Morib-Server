package org.sopt.jaksim.user.repository;

import org.sopt.jaksim.user.domain.Platform;
import org.sopt.jaksim.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByPlatformAndEmail(Platform platform, String email);
}
