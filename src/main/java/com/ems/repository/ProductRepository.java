package com.ems.repository;

import com.ems.model.Product;
import org.springframework.stereotype.Repository;

/**
 * Created by rohitkumar on 03/02/18.
 */
@Repository
public interface ProductRepository extends EntityRepository<Product> {
}
