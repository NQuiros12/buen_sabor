package vrs.backend.demo.generics.services;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vrs.backend.demo.generics.entities.Base;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends Base, ID extends Serializable> {
    public List<E> findAll() throws Exception;
    public Page<E> findAll(Pageable pageable) throws Exception;
    public E findById(ID id) throws Exception;
    public E save(E entity) throws Exception;

    @Transactional
    List<E> saveAll(List<E> entities) throws Exception;

    public E update(ID id, E entity) throws Exception;
    public boolean delete(ID id) throws Exception;

}
