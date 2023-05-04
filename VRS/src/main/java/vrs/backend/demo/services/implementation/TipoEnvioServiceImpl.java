package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.TipoEnvio;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.TipoEnvioRepository;
import vrs.backend.demo.services.TipoEnvioService;

@Service
public class TipoEnvioServiceImpl extends BaseServiceImpl<TipoEnvio, Long> implements TipoEnvioService {

    private TipoEnvioRepository tipoEnvioRepository;

    public TipoEnvioServiceImpl(BaseRepository<TipoEnvio, Long> baseRepository) {
        super(baseRepository);
    }
}
