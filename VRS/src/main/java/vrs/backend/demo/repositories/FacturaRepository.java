package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Factura;

@Repository
public interface FacturaRepository extends BaseRepository<Factura, Long> {
    // Métodos
}