package vrs.backend.demo.genericos.services.implementation;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vrs.backend.demo.genericos.entities.Base;
import vrs.backend.demo.genericos.repositories.BaseRepository;
import vrs.backend.demo.genericos.services.BaseService;


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

    @Override
    public E update(ID id, E entity) throws Exception {
        try{
            Optional<E> entityOptional = repository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = repository.save(entity);
            return entityUpdate;
        }
        catch (Exception e){
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
