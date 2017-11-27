package com.mytest.control;

import com.mytest.DAO.UserLoginReg;
import com.mytest.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "regist";
    }

    @RequestMapping("/regist")
    public String getRegist(){
        return "regist";
    }

    @Autowired
    UserLoginReg userLoginReg;

    @RequestMapping("/registuser")
    public String getRegistUser(HttpServletRequest request){
        String name=request.getParameter("name");
        String passwd=request.getParameter("passwd");
        userLoginReg.save(new User(name,passwd));
        return "login";
    }

}
