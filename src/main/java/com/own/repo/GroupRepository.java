package com.own.repo;

import com.own.models.Groups;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Groups, Long> {
    @Cacheable(cacheNames = "group")
    Optional<Groups> findByType(String type);

}
