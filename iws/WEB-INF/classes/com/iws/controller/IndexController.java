package com.iws.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="index")
public class IndexController {

    private static final Logger log =  Logger.getLogger(ItsmOrderController.class);
    
    @RequestMapping(value="fowardAdmin")
    public ModelAndView fowardAdmin(HttpServletRequest request,HttpServletResponse response)
    {
        log.info("start [toAdmin] method!");
        
        String foward = "admin/index";
        String title = null;
        
        log.info("end [toAdmin] method!");
        return new ModelAndView(foward,"title",title);
    }
}
