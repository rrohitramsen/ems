package com.ems.api;

import com.ems.model.Product;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rohitkumar on 03/02/18.
 */
@RestController
@RequestMapping("/products")
@Api(value="Entity Management System API", description="CRUD operations on Product.")
public class ProductController extends EntityController<Product> {
}
