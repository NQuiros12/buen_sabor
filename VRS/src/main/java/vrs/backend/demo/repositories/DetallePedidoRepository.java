package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.DetallePedido;
import vrs.backend.demo.generics.repositories.BaseRepository;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido,Long> {
}
