package vrs.backend.demo.services;

import com.mercadopago.resources.preference.Preference;
import org.springframework.data.domain.Page;
import vrs.backend.demo.entities.MercadoPagoItem.ItemMercadoPago;
import vrs.backend.demo.entities.Pedido;
import vrs.backend.demo.enums.EstadoPedido;
import vrs.backend.demo.generics.services.BaseService;

import java.util.List;

public interface PedidoService extends BaseService<Pedido,Long> {
    void savePedido(Pedido pedido) throws Exception;
    void updatePedido(Pedido pedidoRecibido, Long id) throws Exception;
    void cambiarEstadoEnvio(Long pedidoId, EstadoPedido estado) throws Exception;
    Preference crearPreferencia(ItemMercadoPago itemMercadoPago);
    Page<Pedido> PedidosByRechazadosEntregados(Integer page);
    List<Pedido> PedidosNotRechazadosYEntregados();

    List<Pedido> pedidosByUsuarioEntrega(String idAuth0);
    void cambiarPagoConfirmado(Long pedidoId, boolean pago) throws Exception;
}
