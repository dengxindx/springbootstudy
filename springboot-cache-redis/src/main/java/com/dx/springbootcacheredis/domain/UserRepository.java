package com.dx.springbootcacheredis.domain;

import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @CacheEvict(value = "user2", beforeInvocation = true,allEntries = true)
    void deleteById(Long id);

    @Query("from User u where u.name=:name")
    @Cacheable(value = "user2", key = "#p0")
    User findByName2(@Param("name")String name);

    @CacheEvict(value = "user2", key = "#p0", beforeInvocation = true,allEntries = true)
    void deleteByName(String name);

    @Modifying
    @Query("update User u set u.age=:age where u.name=:name")
//    @Update("update User u set u.age=:age where u.name=:name")
    @CacheEvict(value = "user2", key = "#p0")
//    @CachePut(value = "user2", key = "#p0") // 返回值void  更新后的缓存中无数据
    void updateByName(@Param("name")String name, @Param("age")Integer age);
}
