package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.genericos.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.ArticuloManufacturadoRepository;
import vrs.backend.demo.genericos.repositories.BaseRepository;
import vrs.backend.demo.services.ArticuloManufacturadoService;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {

    ArticuloManufacturadoRepository articuloManufacturadoRepository;

    public ArticuloManufacturadoServiceImpl(BaseRepository<ArticuloManufacturado, Long> baseRepository) {
        super(baseRepository);
    }
}
