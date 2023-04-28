package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Pedido;
import vrs.backend.demo.generics.repositories.BaseRepository;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long> {
}
