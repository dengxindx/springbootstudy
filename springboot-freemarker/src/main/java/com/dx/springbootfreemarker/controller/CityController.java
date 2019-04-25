package com.dx.springbootfreemarker.controller;

import com.dx.springbootfreemarker.domain.City;
import com.dx.springbootfreemarker.service.CityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "返回单个城市信息页面", notes = "根据传入的id到mysql库查询数据，设置model，返回页面String跳转到该页面")
    @ApiImplicitParam(name = "id", value = "城市id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public String findOneCity(Model model, @PathVariable("id") Long id) {
        model.addAttribute("city", cityService.findCityById(id));
        return "city";
    }

    @ApiOperation(value = "返回所有城市信息页面", notes = "查询mysql库，设置model，返回页面String跳转到该页面")
    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public ModelAndView findAllCity(Model model) {
        List<City> cityList = cityService.findAllCity();
        model.addAttribute("cityList",cityList);
        ModelAndView mv = new ModelAndView("cityList");
        return mv;
    }
}
