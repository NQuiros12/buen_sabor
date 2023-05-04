package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.ArticuloInsumo;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.ArticuloInsumoRepository;
import vrs.backend.demo.services.ArticuloInsumoService;

@Service
public class ArticuloInsumoServiceImpl extends BaseServiceImpl<ArticuloInsumo,Long> implements ArticuloInsumoService {

    private ArticuloInsumoRepository articuloInsumoRepository;

    public ArticuloInsumoServiceImpl(BaseRepository<ArticuloInsumo, Long> baseRepository) {
        super(baseRepository);
    }
}
