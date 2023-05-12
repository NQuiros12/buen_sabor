package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Domicilio;
import vrs.backend.demo.generics.repositories.BaseRepository;

@Repository
public interface DomicilioRepository extends BaseRepository<Domicilio, Long> {
}
