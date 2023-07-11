package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.UnidadMedida;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.services.UnidadMedidaService;
import vrs.backend.demo.repositories.UnidadMedidaRepository;
@Service
public class UnidadMedidaServiceImpl extends BaseServiceImpl<UnidadMedida,Long> implements UnidadMedidaService {
    private final UnidadMedidaRepository unidadMedidaRepository;

    public UnidadMedidaServiceImpl(BaseRepository<UnidadMedida,Long> baseRepository, UnidadMedidaRepository unidadMedidaRepository){
        super(baseRepository);
        this.unidadMedidaRepository = unidadMedidaRepository;
    }


        }
