package com.dx.springbootmybatisdruid.service;

import com.dx.springbootmybatisdruid.domain.User;

public interface UserService {
    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param userName
     * @return
     */
    User findByName(String userName);
}
