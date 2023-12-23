package com.srisaiHome.srisaiHome.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.srisaiHome.srisaiHome.model.Products;

public interface ProductServices {
	
	
	
	public List<Products> getProducts();
	
	public Products saveProduct(Products ps);
	
	public Optional<Products> getProductId(int id);
	
	public Products updateProduct(Products ps);
}
