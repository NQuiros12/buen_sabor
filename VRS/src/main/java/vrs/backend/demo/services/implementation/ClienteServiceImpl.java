package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.Cliente;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.ClienteRepository;
import vrs.backend.demo.repositories.UsuarioRepository;
import vrs.backend.demo.services.ClienteService;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long> implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRepository, ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        super(baseRepository);
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public Cliente findByUsuarioId(Long idUsuario) {
        return clienteRepository.findByUsuarioId(idUsuario);

    }

    public void postCliente(Cliente cliente) throws Exception {
        Usuario usuario = usuarioRepository.findByNombreUsuario(cliente.getUsuario().getUsuario());
        if (usuario != null) {
            Cliente verifyCliente = clienteRepository.findByUsuarioId(usuario.getId());
            System.out.println(verifyCliente.getNombre());
            if (verifyCliente != null) {
                throw new Exception("El cliente ya existe"); // Puedes personalizar el mensaje de excepción según tus necesidades
            }
        }
        super.save(cliente);
        // Resto del código si el cliente no existe
    }
}
