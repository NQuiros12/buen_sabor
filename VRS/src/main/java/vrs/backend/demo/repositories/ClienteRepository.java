package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Cliente;


@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {
    // Métodos
}