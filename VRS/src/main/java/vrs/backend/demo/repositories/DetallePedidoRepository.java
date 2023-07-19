package vrs.backend.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vrs.backend.demo.entities.DetallePedido;
import vrs.backend.demo.entities.projections.RankingProductos;
import vrs.backend.demo.generics.repositories.BaseRepository;

import java.util.Date;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido,Long> {
    //Ranking de productos
    @Query(value = "select  am.denominacion as denominacion, count(am.id) as countVentas \n" +
            "from detalle_pedido dp\n" +
            "         inner join articulo_manufacturado am\n" +
            "                    on am.id = dp.fk_articulo_manufacturado\n" +
            "         inner join pedido p\n" +
            "                    on dp.fk_pedido = p.id\n" +
            "where date(p.fecha) between :diaIn and :diaEnd \n" +
            "group by am.id,am.denominacion;",
            nativeQuery = true)

    List<RankingProductos> bestProducts(Date diaIn, Date diaEnd);
}
