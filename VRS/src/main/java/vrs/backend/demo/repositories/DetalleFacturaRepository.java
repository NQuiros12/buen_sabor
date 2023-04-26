package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.DetalleFactura;
import vrs.backend.demo.genericos.repositories.BaseRepository;

@Repository
public interface DetalleFacturaRepository extends BaseRepository<DetalleFactura, Long> {
}
