package org.sopt.jaksim.mset.repository;

import org.sopt.jaksim.mset.domain.Mset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsetRepository extends JpaRepository<Mset, Long> {
}
