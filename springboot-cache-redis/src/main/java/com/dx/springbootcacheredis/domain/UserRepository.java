package com.dx.springbootcacheredis.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
//@CacheConfig(cacheNames = "user")
public interface UserRepository extends JpaRepository<User, Long>{

    @Cacheable(value = "user", key = "#p0", condition = "#p0.length() < 3")
    User findByName(String name);

    @CachePut(value = "user2")
    User findByIdAndName(Long id, String name);

    @CacheEvict(value = "user2", allEntries = true)
    void deleteById(Long id);
}
