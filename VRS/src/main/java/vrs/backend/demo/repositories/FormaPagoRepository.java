package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.FormaPago;
import vrs.backend.demo.generics.repositories.BaseRepository;

@Repository
public interface FormaPagoRepository extends BaseRepository<FormaPago,Long> {
}
