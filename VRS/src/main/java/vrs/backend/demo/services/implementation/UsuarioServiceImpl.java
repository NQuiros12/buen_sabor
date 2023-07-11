package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.repositories.UsuarioRepository;
import vrs.backend.demo.services.UsuarioService;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario,Long> implements UsuarioService {


    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(BaseRepository<Usuario,Long> baseRepository, UsuarioRepository usuarioRepository){
        super(baseRepository);
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario searchByUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

}
