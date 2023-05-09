package vrs.backend.demo.generics.services.implementation;
import java.lang.reflect.Field;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vrs.backend.demo.generics.entities.Base;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.BaseService;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {

    protected BaseRepository<E, ID> repository;

    public BaseServiceImpl(BaseRepository<E,ID> baseRepository){
        repository = baseRepository;
    }

    @Override
    public List<E> findAll() throws Exception {
        try{
            List<E> entities = repository.findAll();
            return entities;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception{
        try{
            List<E> entities = repository.findAll();
            int start  =(int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), entities.size());

            Page<E> page = new PageImpl<>(entities.subList(start,end), pageable,entities.size());

            return page;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public E findById(ID id) throws Exception {
        try {
            Optional<E> entityOptinal = repository.findById(id);
            return entityOptinal.get();
        } catch (Exception  e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public E save(E entity) throws Exception {
        try{
            entity = repository.save(entity);
            return entity;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

//    @Override
//    public E update(ID id, E entity) throws Exception {
//        try {
//            Optional<E> entityOptional = repository.findById(id);
//            if (entityOptional.isPresent()) {
//                E entityUpdate = entityOptional.get();
//                repository.deleteById(id);
//                entityUpdate = repository.save(entity);
//                return entityUpdate;
//            } else {
//                throw new Exception("Entidad no encontrada con el id: " + id);
//            }
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }


    @Override
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = repository.findById(id);
            if (entityOptional.isPresent()) {
                E entityUpdate = entityOptional.get();
                Field[] fields = entity.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(entity);
                    if (value != null) {
                        field.set(entityUpdate, value);
                    }
                }
                entityUpdate = repository.save(entityUpdate);
                return entityUpdate;
            } else {
                throw new Exception("Entity not found with id: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }





    @Override
    public boolean delete(ID id) throws Exception {
        try{
            if (repository.existsById(id)){
                repository.deleteById(id);
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
}
