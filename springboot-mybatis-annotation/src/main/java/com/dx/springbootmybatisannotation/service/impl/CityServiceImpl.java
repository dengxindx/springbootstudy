package com.dx.springbootmybatisannotation.service.impl;

import com.dx.springbootmybatisannotation.dao.CityDao;
import com.dx.springbootmybatisannotation.domain.City;
import com.dx.springbootmybatisannotation.service.CityService;
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
