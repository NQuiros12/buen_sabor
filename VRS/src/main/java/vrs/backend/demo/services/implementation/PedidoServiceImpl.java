package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.Pedido;
import vrs.backend.demo.enums.EstadoPedido;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.PedidoRepository;
import vrs.backend.demo.services.PedidoService;

import java.util.Optional;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido,Long> implements PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository) {
        super(baseRepository);
    }

    //Cambiar estado de Envio
    public void cambiarEstadoEnvio(Long pedidoId, EstadoPedido estado) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);

        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedido.setEstadoPedido(estado);
            pedidoRepository.save(pedido);
        }
    }
}
