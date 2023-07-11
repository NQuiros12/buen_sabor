package vrs.backend.demo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.UsuarioServiceImpl;

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

}
