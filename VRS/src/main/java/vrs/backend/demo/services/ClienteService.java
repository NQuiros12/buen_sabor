package vrs.backend.demo.services;

import vrs.backend.demo.entities.Cliente;
import vrs.backend.demo.generics.services.BaseService;

public interface ClienteService extends BaseService<Cliente, Long> {

    Cliente findByUsuarioId(Long idUsuario);
}
