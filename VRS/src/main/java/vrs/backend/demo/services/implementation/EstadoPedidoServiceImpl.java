package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.EstadoPedido;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.EstadoPedidoRepository;
import vrs.backend.demo.services.EstadoPedidoService;

@Service
public class EstadoPedidoServiceImpl extends BaseServiceImpl<EstadoPedido,Long> implements EstadoPedidoService {

    private EstadoPedidoRepository estadoPedidoRepository;

    public EstadoPedidoServiceImpl(BaseRepository<EstadoPedido, Long> baseRepository) {
        super(baseRepository);
    }
}
