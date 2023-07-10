package vrs.backend.demo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PedidoControllerWS {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/pedidows") // /app/pedidows
    @SendTo("/pedidows/public")
    public PedidoWS receiveMessage(@Payload PedidoWS pedidoWS){
        return pedidoWS;
    }

    @MessageMapping("/private-pedidows") // /app/private-pedidows
    public PedidoWS recMessage(@Payload PedidoWS pedidoWS){
        simpMessagingTemplate.convertAndSendToUser(pedidoWS.getReceiverName(),"/private",pedidoWS); // /user/David/private
        return pedidoWS;
    }
}
