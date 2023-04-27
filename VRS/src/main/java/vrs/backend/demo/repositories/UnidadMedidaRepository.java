package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.entities.UnidadMedida;
import vrs.backend.demo.generics.repositories.BaseRepository;

@Repository
public interface UnidadMedidaRepository extends BaseRepository<UnidadMedida,Long> {
}
