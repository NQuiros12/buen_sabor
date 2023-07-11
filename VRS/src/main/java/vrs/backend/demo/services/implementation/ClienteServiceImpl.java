package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.Cliente;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.ClienteRepository;
import vrs.backend.demo.services.ClienteService;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente,Long>implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(BaseRepository<Cliente,Long> baseRepository, ClienteRepository clienteRepository){
        super(baseRepository);
        this.clienteRepository = clienteRepository;
    }

}
