package top.microfrank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.microfrank.ResponseMessage;

/**
 * Created by Frank Local on 2017/9/13.
 */
@Controller
public class HtmlController {
    @Autowired
    private SimpMessagingTemplate webSocket;
    @RequestMapping("/")
    public String index(){
        return "ws";
    }
    @RequestMapping("/send")
    @ResponseBody
    public String send(){
        webSocket.convertAndSend("/topic/getResponse",new ResponseMessage("hahahaha"));
        return "OK";
    }
}
