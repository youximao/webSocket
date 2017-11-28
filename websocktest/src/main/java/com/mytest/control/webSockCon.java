package com.mytest.control;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @MessageMapping("/sayall")
    public void withChat(Principal principal,String mes){
        simpMessagingTemplate.convertAndSend("/topic/send","来自:"+principal.getName()+mes);
    }

    // 用户注册
    Map<String,Principal> map=new HashMap<String,Principal>();
    Map<String,Date> timeMap=new HashMap<String,Date>();
    @RequestMapping("/regist")
    @ResponseBody
    public Object sendMessage(Principal principal){
        System.out.println(principal.toString());
        if(!map.containsKey(principal.getName())) {
            map.put(principal.getName(), principal);
            timeMap.put(principal.getName(),new Date());
            simpMessagingTemplate.convertAndSend("/topic/send",principal.getName()+"已经登录");
        }

        return new JSONObject().put("result","ok");
    }




    @MessageMapping("/jia")
    public void sendMm(Principal principal,String msg){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpMessagingTemplate.convertAndSend("/topic/send", df.format(new Date()));
        //simpMessagingTemplate.send();
        System.out.println("-------");
    }


    // sendTOUser的
    @MessageMapping("/chat")
    public void sendSay(Principal principal,String msg){
        System.out.println(msg);
        String []s=msg.split("/");

        Date date=new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String tim=df.format(date);
        tim=" ("+tim+") ";
        simpMessagingTemplate.convertAndSendToUser(s[0],"/topic/say",principal.getName()+tim+"/"+s[1]);
    }


    // 广播发送
    @RequestMapping("/sendAll")
    @ResponseBody
    public Object sendAll(HttpServletRequest httpServletRequest){

        simpMessagingTemplate.convertAndSend("/topic/send","fasong suoyou");
        return new JSONObject().put("result","ok");
    }

    // 获取在线人员
    @RequestMapping("/getuser")
    @ResponseBody
    public Object getNum(Principal principal){
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();

        for (String key: map.keySet()) {
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("name",key);
            Date date=new Date();
            long tt=date.getTime()-timeMap.get(key).getTime();
            String timestr="";
            while(tt>0){
                timestr=tt%60+"/"+timestr;
                tt=tt/60;
            }
            jsonObject1.put("time",timestr);
            jsonArray.add(jsonObject1);
        }
        jsonObject.put("nowuser",principal.getName());
        jsonObject.put("user",jsonArray);
        jsonObject.put("num",map.size());
        jsonObject.put("name",principal.getName());
        return jsonObject;
    }
}
