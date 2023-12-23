package com.srisaiHome.srisaiHome.Repository;

import org.springframework.data.repository.CrudRepository;

import com.srisaiHome.srisaiHome.model.Products;
import com.srisaiHome.srisaiHome.model.User;

public interface ProductRepository extends CrudRepository<Products, Integer> {

}
