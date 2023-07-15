package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Pedido;
import vrs.backend.demo.enums.EstadoPedido;
import vrs.backend.demo.generics.repositories.BaseRepository;

import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long> {
    //Query para obtener estados sin paginacion
    @Query("select pedido from Pedido pedido where pedido.estadoPedido = :estadoPedido order by pedido.fecha")
    List<Pedido> pedidosByState(@Param("estadoPedido") EstadoPedido estadoPedido);
    //Query estados del cocinero
    @Query("select pedido from Pedido pedido where pedido.estadoPedido in (:estado1,:estado2) order by pedido.fecha")
    List<Pedido> pedidosBy2States(EstadoPedido estado1, EstadoPedido estado2);
    @Query("select pedido from Pedido pedido where pedido.estadoPedido not in (:estado1,:estado2) order by pedido.fecha")
    List<Pedido> pedidosNot2States(EstadoPedido estado1, EstadoPedido estado2);
    @Query("select pedido from Pedido pedido where pedido.id = :idInput order by pedido.fecha")
    List<Pedido> pedidosById(long idInput);
    @Query("select pedido from Pedido pedido where pedido.cliente.id = :IdCliente")
    List<Pedido> pedidosByCliente(long IdCliente);

}
