package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.DetallePedido;
import vrs.backend.demo.entities.Domicilio;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.DetallePedidoRepository;
import vrs.backend.demo.repositories.DomicilioRepository;
import vrs.backend.demo.services.DomicilioService;

@Service
public class DomicilioServiceImpl extends BaseServiceImpl<Domicilio,Long> implements DomicilioService {
    private DomicilioRepository domicilioRepository;

    public DomicilioServiceImpl(BaseRepository<Domicilio, Long> baseRepository) {
        super(baseRepository);
    }
}
