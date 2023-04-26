package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.Factura;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.genericos.repositories.BaseRepository;
import vrs.backend.demo.genericos.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.FacturaRepository;
import vrs.backend.demo.repositories.UsuarioRepository;
import vrs.backend.demo.services.FacturaService;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura,Long>implements FacturaService {


    private FacturaRepository facturaRepository;

    public FacturaServiceImpl(BaseRepository<Factura,Long> baseRepository){
        super(baseRepository);
    }

}
