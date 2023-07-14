package vrs.backend.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.generics.services.BaseService;


public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado, Long> {
    void updateArticuloManufacturado(ArticuloManufacturado articuloRecibido, Long idPrevio) throws Exception;
    void saveArticuloManufacturado(ArticuloManufacturado articuloManufacturado) throws Exception;
    Page<ArticuloManufacturado> buscarPorNombre(String nombreArtMan, Integer page, String orderPrice) throws Exception;
    Page<ArticuloManufacturado> findByCategoryWithPagination(String nombreCategoria, Pageable pageable, String orderPrice);
}
