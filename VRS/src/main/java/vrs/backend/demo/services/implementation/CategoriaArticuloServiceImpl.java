package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.CategoriaArticulo;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.CategoriaArticuloRepository;
import vrs.backend.demo.services.CategoriaArticuloService;

@Service
public class CategoriaArticuloServiceImpl extends BaseServiceImpl<CategoriaArticulo,Long> implements CategoriaArticuloService {

    private CategoriaArticuloRepository categoriaArticuloRepository;

    public CategoriaArticuloServiceImpl(BaseRepository<CategoriaArticulo, Long> baseRepository) {
        super(baseRepository);
    }
}
