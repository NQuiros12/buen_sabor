package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Producto;
import vrs.backend.demo.generics.repositories.BaseRepository;

@Repository
public interface ProductoRepository extends BaseRepository<Producto,Long> {
}
