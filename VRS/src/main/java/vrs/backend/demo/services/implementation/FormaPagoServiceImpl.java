package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.FormaPago;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.FormaPagoRepository;
import vrs.backend.demo.services.FormaPagoService;

@Service
public class FormaPagoServiceImpl extends BaseServiceImpl<FormaPago,Long> implements FormaPagoService {

    private FormaPagoRepository formaPagoRepository;

    public FormaPagoServiceImpl(BaseRepository<FormaPago, Long> baseRepository) {
        super(baseRepository);
    }
}
