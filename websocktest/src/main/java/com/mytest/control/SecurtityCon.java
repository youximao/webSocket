package com.mytest.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/11/13 0013.
 */
@Controller
public class SecurtityCon {

    @RequestMapping("/login")
    public String getLogin(HttpServletRequest request){
        //return "securtityht/login";
        request.getSession();
        return "login";
    }
    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }
    @RequestMapping("/ee")
    public String getError(){
        return "login";
    }
}
