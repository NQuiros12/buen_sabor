-- Ranking productos
select am.denominacion, count(am.id)
from detalle_pedido dp
         inner join articulo_manufacturado am
                    on am.id = dp.fk_articulo_manufacturado
         inner join pedido p
                    on dp.fk_pedido = p.id
where date(p.fecha) between '2023-06-14' and '2023-06-18'
group by am.id;
-- Ranking clientes
select count(0) as countCompras ,concat(c.nombre,' ',c.apellido) as nombreCompleto
from pedido p
inner join cliente c
    on p.fk_cliente = c.id
inner join usuario u
    on c.fk_usuario = u.id
where date(p.fecha) between '2023-06-13' and '2023-06-15'
    and u.id_auth0 is null
group by c.id
order by 1 desc;

-- visualizacion pedidos por cliente
select p.*,dp.*
from pedido p
inner join cliente c
    on p.fk_cliente = c.id
inner join detalle_pedido dp
    on p.id = dp.fk_pedido
where c.id = 2;
