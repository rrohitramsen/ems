package com.ems.service;

import com.ems.model.Product;
import com.ems.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rohitkumar on 03/02/18.
 */
@Service
@Primary
@Resource(name="productService")
public class ProductService implements EntityService<Product> {

    private static final Logger LOGGER = Logger.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createEntity(Product entity) {

        Product product = productRepository.save(entity);
        LOGGER.info(product + " stored in data base successfully.");

        return product;
    }

    @Override
    public Product updateEntity(Long id, Product entity) {
        return null;
    }

    @Override
    public void deleteEntity(Long id) {
        productRepository.delete(id);
    }

    @Override
    public Product getEntity(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> getAllEntities() {
        return productRepository.findAll();
    }
}
