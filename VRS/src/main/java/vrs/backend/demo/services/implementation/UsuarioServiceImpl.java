package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.enums.Rol;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.repositories.UsuarioRepository;
import vrs.backend.demo.services.UsuarioService;

import java.util.List;

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

    @Override
    public Usuario searchByIdAuth0(String idAuth0) {
        return usuarioRepository.findByAuth0Id(idAuth0);
    }

    @Override
    public List<Usuario> searchByRol(Rol rol) {
        return usuarioRepository.findByRol(rol);
    }

}
