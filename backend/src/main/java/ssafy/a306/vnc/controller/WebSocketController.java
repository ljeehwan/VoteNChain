package ssafy.a306.vnc.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import ssafy.a306.vnc.model.ChartVO;

@Controller
public class WebSocketController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChartVO sendMessage(@Payload ChartVO chatVO) {
        System.out.println("받았다.");
        return chatVO;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChartVO addUser(@Payload ChartVO chatVO, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatVO.getSender());
        return chatVO;
    }


    @MessageMapping("/socket/chart/{hashcode}/receive")
    @SendTo("/socket/chart/{hashcode}/send")
    public ChartVO chartSocketHandler(@DestinationVariable String hashcode,@Payload ChartVO data){

        System.out.println("받았다.");
        return data;
    }
}