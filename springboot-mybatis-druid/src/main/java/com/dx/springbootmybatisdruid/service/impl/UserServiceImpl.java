package com.dx.springbootmybatisdruid.service.impl;

import com.dx.springbootmybatisdruid.dao.cluster.CityDao;
import com.dx.springbootmybatisdruid.dao.master.UserDao;
import com.dx.springbootmybatisdruid.domain.City;
import com.dx.springbootmybatisdruid.domain.User;
import com.dx.springbootmybatisdruid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;    // 主数据源

    @Autowired
    private CityDao cityDao;    // 从数据源

    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("温岭市");
        user.setCity(city);
        return user;
    }
}
