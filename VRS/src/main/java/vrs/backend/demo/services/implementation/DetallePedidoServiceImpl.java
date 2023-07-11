package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.DetallePedido;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.DetallePedidoRepository;
import vrs.backend.demo.services.DetallePedidoService;

@Service
public class DetallePedidoServiceImpl extends BaseServiceImpl<DetallePedido,Long> implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    public  DetallePedidoServiceImpl(BaseRepository<DetallePedido, Long> baseRepository, DetallePedidoRepository detallePedidoRepository) {
        super(baseRepository);
        this.detallePedidoRepository = detallePedidoRepository;
    }
}
