package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Cliente;
import vrs.backend.demo.generics.repositories.BaseRepository;


@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {
    // Métodos
}