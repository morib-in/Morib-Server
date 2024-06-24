package org.sopt.jaksim.user.repository;

import org.sopt.jaksim.user.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RedisTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(final String refreshToken);
}
