package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.CategoriaArticulo;
import vrs.backend.demo.generics.repositories.BaseRepository;

@Repository
public interface CategoriaArticuloRepository extends BaseRepository<CategoriaArticulo,Long> {
}
