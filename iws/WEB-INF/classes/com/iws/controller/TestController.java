package com.iws.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iws.pojo.TestPojo;
import com.iws.service.TestService;

@Controller
@RequestMapping(value="/test")
public class TestController {

    @Resource(name = "testServiceImpl")
    private TestService testService;
    
    @RequestMapping(value="/showAll")
    public ModelAndView showAll(HttpServletRequest request,HttpServletResponse response) {
       List<TestPojo> testPojo = testService.selectAll();
       
       String foward = "test/test";      

       return new ModelAndView(foward, "testPojo",testPojo);
    }
}
