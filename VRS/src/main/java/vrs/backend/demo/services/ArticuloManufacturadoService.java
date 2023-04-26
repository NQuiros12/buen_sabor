package vrs.backend.demo.services;

import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.generics.services.BaseService;


public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado, Long> {
    /*
    //Declaramos una dependencia para que luego Spring la genere, esto seria lo mismo @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;
    public ArticuloManufacturadoService( ArticuloManufacturadoRepository articuloManufacturadoRepository){
        this.articuloManufacturadoRepository = articuloManufacturadoRepository;
    }
    @Override
    @Transactional // Esto significa que los metodos haran transaciones con la base de datos OLTP
    public List<ArticuloManufacturado> findAll() throws Exception {
        try{
            List<ArticuloManufacturado> entities = articuloManufacturadoRepository.findAll();
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticuloManufacturado findById(Long id) throws Exception {
        try{
            Optional<ArticuloManufacturado> entityOptional = articuloManufacturadoRepository.findById(id);
            return entityOptional.get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticuloManufacturado save(ArticuloManufacturado entity) throws Exception {
        try{
            entity = articuloManufacturadoRepository.save(entity);
            return entity;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticuloManufacturado update(Long id, ArticuloManufacturado entity) throws Exception {
        try{
            Optional<ArticuloManufacturado> entityOptional = articuloManufacturadoRepository.findById(id);
            ArticuloManufacturado articuloManufacturado = entityOptional.get();
            articuloManufacturado = articuloManufacturadoRepository.save(articuloManufacturado);
            return articuloManufacturado;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if (articuloManufacturadoRepository.existsById(id)){
                articuloManufacturadoRepository.deleteById(id);
                return true;
            }
            else{
                throw new Exception();
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

     */
}
