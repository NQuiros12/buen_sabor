package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.entities.CategoriaArticulo;
import vrs.backend.demo.generics.repositories.BaseRepository;

import java.util.List;

@Repository
public interface CategoriaArticuloRepository extends BaseRepository<CategoriaArticulo,Long> {
    @Query("SELECT nombreCategoria FROM CategoriaArticulo nombreCategoria WHERE nombreCategoria.denominacion like concat('%',:nombreCategoria,'%') ")
    List<CategoriaArticulo> findByName(@Param("nombreCategoria") String nombreCategoria);
}
