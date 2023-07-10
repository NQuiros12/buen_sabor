package vrs.backend.demo.websocket;

import lombok.*;
import vrs.backend.demo.enums.EstadoPedido;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PedidoWS {

    private String senderName;
    private String receiverName;
    private EstadoPedido estadoPedido;

}
