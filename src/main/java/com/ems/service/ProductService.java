package com.ems.service;

import com.ems.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rohitkumar on 03/02/18.
 */
@Service
public class ProductService implements EntityService<Product> {

    @Override
    public Product createEntity(Product entity) {
        return null;
    }

    @Override
    public Product updateEntity(Long id, Product entity) {
        return null;
    }

    @Override
    public void deleteEntity(Long id) {

    }

    @Override
    public Product getEntity(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllEntities() {
        return null;
    }
}
