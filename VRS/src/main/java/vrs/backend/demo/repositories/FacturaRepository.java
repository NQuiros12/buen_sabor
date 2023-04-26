package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Factura;
import vrs.backend.demo.genericos.repositories.BaseRepository;

@Repository
public interface FacturaRepository extends BaseRepository<Factura, Long> {
    // Métodos
}