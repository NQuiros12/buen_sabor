package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Cliente;
import vrs.backend.demo.generics.repositories.BaseRepository;


@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.usuario.id = :usuarioId")
    Cliente findByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT c FROM Cliente c WHERE c.usuario.idAuth0 = :usuarioIdAuth0")
    Cliente findByUsuarioIdAuth0(@Param("usuarioIdAuth0") String usuarioIdAuth0);
}