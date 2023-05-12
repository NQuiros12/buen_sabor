package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.DetalleArticuloManufacturado;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.DetalleArticuloManufacturadoRepository;
import vrs.backend.demo.services.DetalleArticuloManufacturadoService;
@Service
public class DetalleArticuloManufacturadoServiImpl extends BaseServiceImpl<DetalleArticuloManufacturado,Long> implements DetalleArticuloManufacturadoService {
    private DetalleArticuloManufacturadoRepository detalleArticuloManufacturadoRepository;

    public DetalleArticuloManufacturadoServiImpl(BaseRepository<DetalleArticuloManufacturado,Long> baseRepository){
        super(baseRepository);
    }
}
