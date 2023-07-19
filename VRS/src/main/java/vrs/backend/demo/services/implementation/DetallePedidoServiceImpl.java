package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.DetallePedido;
import vrs.backend.demo.entities.projections.RankingProductos;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.DetallePedidoRepository;
import vrs.backend.demo.services.DetallePedidoService;

import java.util.Date;
import java.util.List;

@Service
public class DetallePedidoServiceImpl extends BaseServiceImpl<DetallePedido,Long> implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    public  DetallePedidoServiceImpl(BaseRepository<DetallePedido, Long> baseRepository, DetallePedidoRepository detallePedidoRepository) {
        super(baseRepository);
        this.detallePedidoRepository = detallePedidoRepository;
    }
    public List<RankingProductos> rankingProductos(Date diaIn, Date diaEnd){
        //System.out.println("getCountVentas: "+detallePedidoRepository.bestProducts(diaIn, diaEnd).get(0).getCountVentas());
        return detallePedidoRepository.bestProducts(diaIn,diaEnd);
    }
}
