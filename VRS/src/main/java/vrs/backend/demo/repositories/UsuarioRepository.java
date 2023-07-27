package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.enums.Rol;
import vrs.backend.demo.generics.repositories.BaseRepository;

import java.util.List;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.usuario = :nombreUsuario")
    Usuario findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
    @Query("SELECT u FROM Usuario u WHERE u.idAuth0 = :idAuth0")
    Usuario findByAuth0Id(@Param("idAuth0") String idAuth0);
    @Query("SELECT u FROM Usuario u WHERE u.rol = :rol")
    List<Usuario> findByRol(@Param("rol") Rol rol);
}
