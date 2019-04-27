package com.dx.springbootmybatisannotation.dao;

import com.dx.springbootmybatisannotation.domain.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper //标志为mybatis的mapper
public interface CityDao {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    @Select("select * from city where city_name=#{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "description", column = "description")
    })
    City findByName(@Param("cityName") String cityName);
}
