package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.generics.repositories.BaseRepository;

import java.util.List;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado,Long> {

    @Query("SELECT artMan FROM ArticuloManufacturado artMan WHERE artMan.denominacion like concat('%',:nombreArtMan,'%') order by artMan.precioVenta")
    List<ArticuloManufacturado> findByName(@Param("nombreArtMan") String nombreArtMan);

    @Query("SELECT artMan from ArticuloManufacturado artMan where artMan.categoria.denominacion = :nombreCategoria")
    List<ArticuloManufacturado> artByCategoria(@Param("nombreCategoria") String nombreCategoria);

}
