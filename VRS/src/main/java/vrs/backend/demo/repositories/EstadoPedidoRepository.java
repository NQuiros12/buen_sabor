package vrs.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.EstadoPedido;
import vrs.backend.demo.entities.Factura;
import vrs.backend.demo.generics.repositories.BaseRepository;

@Repository
public interface EstadoPedidoRepository extends BaseRepository<EstadoPedido, Long> {
}
