package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.DetalleFactura;

@Repository
public interface DetalleFacturaRepository extends BaseRepository<DetalleFactura, Long> {
}
