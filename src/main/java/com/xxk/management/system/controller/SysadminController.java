package com.xxk.management.system.controller;

import com.xxk.core.file.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Controller
@RequestMapping("/sysadmin")
public class SysadminController extends BaseController {

    private static Logger log = Logger.getLogger(SysadminController.class);

    /*@RequestMapping("/index")
    public ModelAndView  index() {
        Map<String, Object> result = new HashMap<>();
        return new ModelAndView("/index", "result", result);
    }*/

    @RequestMapping("/user_admin")
    public ModelAndView  listUser() {
        Map<String, Object> result = new HashMap<>();
        return new ModelAndView("/sysadmin/user_admin", "result", result);
    }

    @RequestMapping("/offices_admin")
    public ModelAndView  offices_tab() {
        Map<String, Object> result = new HashMap<>();
        return new ModelAndView("/sysadmin/offices_admin", "result", result);
    }

    @RequestMapping("/device_admin")
    public ModelAndView  device_tab() {
        Map<String, Object> result = new HashMap<>();
        return new ModelAndView("/sysadmin/device_admin", "result", result);
    }

    @RequestMapping("/stock_admin")
    public ModelAndView  stock_tab() {
        Map<String, Object> result = new HashMap<>();
        return new ModelAndView("/sysadmin/stock_admin", "result", result);
    }

    @RequestMapping("/material_admin")
    public ModelAndView  material_tab() {
        Map<String, Object> result = new HashMap<>();
        return new ModelAndView("/sysadmin/material_admin", "result", result);
    }

}
