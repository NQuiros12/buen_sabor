package vrs.backend.demo.repositories;


import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.DetalleArticuloManufacturado;
import vrs.backend.demo.generics.repositories.BaseRepository;
@Repository
public interface DetalleArticuloManufacturadoRepository extends BaseRepository<DetalleArticuloManufacturado, Long> {
}
