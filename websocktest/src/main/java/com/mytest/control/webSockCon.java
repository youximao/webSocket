package com.mytest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
@Controller
@RequestMapping("/ws")
public class webSockCon {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    class mess{
        String s;
        mess(String s){
            this.s=s;
        }
    }
    @RequestMapping("/noframe")
    public String getNoFrame(){
        return "NoFrame";
    }

    @RequestMapping("/ws")
    public String getIndex(){
        return "index";
    }

    //@MessageMapping("/test")
    @SendTo("/topic/send")
    public Object sendM(String string){
        System.out.println(string);
    //    simpMessagingTemplate.convertAndSend("/topic/send","jiajunl");
        return string;
    }

    @MessageMapping("/chat")
    public void withChat(Principal principal,String mes){
        simpMessagingTemplate.convertAndSendToUser(principal.getName(),"/topic/send",mes);
    }

    @MessageMapping("/test")
    public void sendMessage(String string){
        System.out.println(string);
        simpMessagingTemplate.convertAndSend("/topic/send",string);
    }

    @RequestMapping("/sendAll")
    public void sendAll(){
        simpMessagingTemplate.convertAndSend("/topic/send","fasong suoyou");
    }


    @MessageMapping("/jia")
    public void sendMm(Principal principal,String msg){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpMessagingTemplate.convertAndSend("/topic/send", df.format(new Date()));


        //simpMessagingTemplate.send();
        System.out.println("-------");
    }

}
