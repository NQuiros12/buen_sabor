package vrs.backend.demo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.enums.Rol;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.UsuarioServiceImpl;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(path = "/usuarios")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl> {

    private final UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/getByUsuario/{nombreUsuario}")
    public Usuario searchByName(@PathVariable String nombreUsuario) {
        return usuarioServiceImpl.searchByUsuario(nombreUsuario);
    }
    @GetMapping("/getByIdAuth0/{idAuth0}")
    public Usuario searchByIdAuth0(@PathVariable String idAuth0) {
        return usuarioServiceImpl.searchByIdAuth0(idAuth0);
    }

    @GetMapping("/getByRol/{rol}")
    public List<Usuario> searchByRol(@PathVariable Rol rol) {
        return usuarioServiceImpl.searchByRol(rol);
    }

}
