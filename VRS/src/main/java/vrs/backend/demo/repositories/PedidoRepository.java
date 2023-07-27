package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.Pedido;
import vrs.backend.demo.entities.projections.CostosGanancias;
import vrs.backend.demo.entities.projections.TopClientes;
import vrs.backend.demo.enums.EstadoPedido;
import vrs.backend.demo.generics.repositories.BaseRepository;

import java.util.Date;
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

    @Query("select pedido from Pedido pedido where pedido.usuarioEntrega.idAuth0 = :idAuth0")
    List<Pedido> pedidosByUsuarioEntrega(String idAuth0);
    //Pedidos por dia
    @Query("select count(pedido) from Pedido pedido where date(pedido.fecha) between :diaIn and :diaEnd group by pedido.fecha")
    List<Integer> pedidosByDay(Date diaIn, Date diaEnd);
    //Top Clientes
    @Query("select count(0) as countCompras ,u.usuario as emailUsuario \n" +
            "from Pedido p \n" +
            "inner join Cliente c \n" +
            "    on p.cliente.id = c.id \n" +
            "inner join Usuario u \n" +
            "    on c.usuario.id = u.id \n" +
            "where date(p.fecha) between :diaIn and :diaEnd \n" +
            "group by u.id \n" +
            "order by 1 desc")
    List<TopClientes> topClientes(Date diaIn, Date diaEnd);
    //Costos y Ganancias
    @Query(value = "select sum(monto) ganancias,\n" +
            "       sum(am.precio_compra) costos \n" +
            "from pedido p \n" +
            "inner join detalle_pedido dp \n" +
            "    on p.id = dp.fk_pedido \n" +
            "inner join articulo_manufacturado am \n" +
            "    on dp.fk_articulo_manufacturado = am.id \n" +
            "where date(p.fecha) between :diaIn and :diaEnd \n" +
            "group by date(p.fecha);",
            nativeQuery = true)
    List<CostosGanancias> costosGananciasByDate(Date diaIn, Date diaEnd);

}
