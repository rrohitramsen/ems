package com.ems.service;

import com.ems.model.BasicEntity;
import com.ems.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by rohitkumar on 28/01/18.
 */
public class EntityServiceImpl<T extends BasicEntity> implements EntityService {

    @Autowired
    private EntityRepository<T> entityRepository;


    @Override
    public BasicEntity createEntity(BasicEntity entity) {

        entity.getClass()
        return null;
    }

    @Override
    public BasicEntity updateEntity(BasicEntity entity) {
        return null;
    }

    @Override
    public void deleteEntity(Long id) {

    }

    @Override
    public T getEntity(Long id) {
        return entityRepository.findOne(id);
    }

    @Override
    public List<T> getAllEntities() {
        return (List<T>) entityRepository.findAll();
    }
}
