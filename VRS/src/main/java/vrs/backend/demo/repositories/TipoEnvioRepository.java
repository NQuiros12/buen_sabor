package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.TipoEnvio;
import vrs.backend.demo.generics.repositories.BaseRepository;

@Repository
public interface TipoEnvioRepository extends BaseRepository<TipoEnvio, Long> {
}
