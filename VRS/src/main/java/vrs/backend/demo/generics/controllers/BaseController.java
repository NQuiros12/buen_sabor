package vrs.backend.demo.generics.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import vrs.backend.demo.generics.entities.Base;

import java.io.Serializable;

public interface BaseController<E extends Base,ID extends Serializable> {

    public ResponseEntity<?> getAll();
    public ResponseEntity<?> getAll(Integer page);

    public ResponseEntity<?> getOne(@PathVariable ID id);

    public ResponseEntity<?> save(@RequestBody E entity);

    public ResponseEntity<?> update( @RequestBody E entity, @PathVariable Long id);

    public ResponseEntity<?> delete(@PathVariable ID id);

}