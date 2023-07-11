package vrs.backend.demo.controllers;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.Cliente;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.ClienteServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(path = "/clientes")
public class ClienteController extends BaseControllerImpl<Cliente, ClienteServiceImpl> {

    private final ClienteServiceImpl clienteServiceImpl;


    @GetMapping("/getByUsuarioId/{idUsuario}")
    public Cliente searchByName(@PathVariable Long idUsuario) {
        return clienteServiceImpl.findByUsuarioId(idUsuario);
    }

    @Override
    @Transactional
    @PostMapping("")
    public ResponseEntity<?> save(Cliente entity)  {
        try {
            clienteServiceImpl.postCliente(entity);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear cliente "+ e.getMessage());
        }
    }
}
