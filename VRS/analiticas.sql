
select am.denominacion, count(am.id)
from detalle_pedido dp
         inner join articulo_manufacturado am
                    on am.id = dp.fk_articulo_manufacturado
         inner join pedido p
                    on dp.fk_pedido = p.id
where date(p.fecha) between '2023-06-14' and '2023-06-18'
group by am.id;