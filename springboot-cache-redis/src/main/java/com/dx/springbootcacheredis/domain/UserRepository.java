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
//    @Cacheable(value = "user", key = "#a0")
//    @Cacheable(value = "user", key = "#root.args[0]")
//    @Cacheable(value = "user", key = "#root.args")
//    @Cacheable(value = "user", key = "getTargetClass() + getMethodName() + getArgs()")
//    @Cacheable(value = "user")
    User findByName(String name);

    @CachePut(value = "user2")
    User findByIdAndName(Long id, String name);

    @CacheEvict(value = "user2", allEntries = true)
    void deleteById(Long id);
}
