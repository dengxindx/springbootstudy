package com.dx.springbootcacheredis.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@CacheConfig(cacheNames = "user")
public interface UserRepository extends JpaRepository<User, Long>{

    @Cacheable(key = "#p0", condition = "#p0.length() < 3")
    User findByName(String name);
}
