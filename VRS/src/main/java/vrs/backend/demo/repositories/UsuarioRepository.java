package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Usuario;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

}
