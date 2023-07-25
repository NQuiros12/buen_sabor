package vrs.backend.demo.services;

import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.generics.services.BaseService;

public interface UsuarioService extends BaseService<Usuario, Long> {

    Usuario searchByUsuario(String nombreUsuario);
    Usuario searchByIdAuth0(String idAuth0);
}
