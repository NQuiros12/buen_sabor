package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.Cliente;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.genericos.repositories.BaseRepository;
import vrs.backend.demo.genericos.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.ClienteRepository;
import vrs.backend.demo.repositories.UsuarioRepository;
import vrs.backend.demo.services.ClienteService;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente,Long>implements ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(BaseRepository<Cliente,Long> baseRepository){
        super(baseRepository);
    }

}
