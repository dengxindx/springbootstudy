package com.dx.springbootmybatis.service.impl;

import com.dx.springbootmybatis.dao.CityDao;
import com.dx.springbootmybatis.domain.City;
import com.dx.springbootmybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 *
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    public City findCityByName(String cityName) {
        return cityDao.findByName(cityName);
    }

}
