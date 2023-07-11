package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.generics.repositories.BaseRepository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.usuario = :nombreUsuario")
    Usuario findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
}
