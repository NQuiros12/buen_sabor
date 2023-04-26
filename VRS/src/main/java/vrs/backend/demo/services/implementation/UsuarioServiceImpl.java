package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.Usuario;
import vrs.backend.demo.repositories.BaseRepository;
import vrs.backend.demo.repositories.UsuarioRepository;
import vrs.backend.demo.services.UsuarioService;


@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario,Long> implements UsuarioService {


    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(BaseRepository<Usuario,Long> baseRepository){
        super(baseRepository);
    }


}
