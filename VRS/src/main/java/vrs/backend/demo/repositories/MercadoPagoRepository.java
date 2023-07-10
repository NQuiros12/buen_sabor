package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.MercadoPago;
import vrs.backend.demo.generics.repositories.BaseRepository;
@Repository
public interface MercadoPagoRepository extends BaseRepository<MercadoPago,Long> {
}
