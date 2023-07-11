package vrs.backend.demo.services;

import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.generics.services.BaseService;

import java.util.List;


public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado, Long> {
    void updateArticuloManufacturado(ArticuloManufacturado articuloRecibido, Long idPrevio) throws Exception;
    void saveArticuloManufacturado(ArticuloManufacturado articuloManufacturado) throws Exception;
    List<ArticuloManufacturado> buscarPorNombre(String nombreArtMan);
}
