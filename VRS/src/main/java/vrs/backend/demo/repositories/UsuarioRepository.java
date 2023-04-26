package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.genericos.repositories.BaseRepository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

}
