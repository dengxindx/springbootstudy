package com.dx.springbootmybatisdruid.dao.master;

import com.dx.springbootmybatisdruid.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    @Select("select * from user where user_name=#{userName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "description", column = "description")
    })
    User findByName(@Param("userName") String userName);
}
