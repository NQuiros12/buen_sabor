package vrs.backend.demo.services;

import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.enums.Rol;
import vrs.backend.demo.generics.services.BaseService;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Long> {

    Usuario searchByUsuario(String nombreUsuario);
    Usuario searchByIdAuth0(String idAuth0);
    List<Usuario> searchByRol(Rol rol);
}
