package com.example.demo.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

//@CacheConfig(cacheNames = "users")
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

    @Cacheable(cacheNames = "my")
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

//    @Cacheable(cacheNames = "my2")
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

    @Modifying
    @Query("update User u set u.name=:newname where u.name=:oldname")
    void updateUser(@Param("newname") String newname, @Param("oldname") String oldname);
}
