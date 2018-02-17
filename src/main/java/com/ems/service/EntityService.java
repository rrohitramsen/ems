package com.ems.service;

import com.ems.model.BasicEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by rohitkumar on 28/01/18.
 */
@Service
public interface EntityService<T extends BasicEntity> {

    /**
     * Create  entity
     * @param entity
     * @return T
     */
    T createEntity(T entity);

    /**
     * Update entity
     * @param entity
     * @param id
     * @return T
     */
    T updateEntity(Long id, T entity);

    /**
     * Partially Update the entity using patch.
     * @param id
     * @return T
     */
    T partiallyUpdateEntity(Long id, Map<String, Object> updates);

    /**
     * Delete entity
     * @param id
     * @return BasicEntity
     */
    void deleteEntity(Long id);


    /**
     * Display entity
     * @param id
     * @return T
     */
    T getEntity(Long id);


    /**
     * Return List of Entitites of Type T.
     * @return List of type T.
     */
     List<T> getAllEntities();

}
