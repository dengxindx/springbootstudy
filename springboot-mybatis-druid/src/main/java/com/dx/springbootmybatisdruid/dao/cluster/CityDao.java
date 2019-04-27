package com.dx.springbootmybatisdruid.dao.cluster;

import com.dx.springbootmybatisdruid.domain.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CityDao {

    @Select("select * from city where city_name=#{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "description", column = "description")
    })
    City findByName(@Param("cityName") String cityName);
}
