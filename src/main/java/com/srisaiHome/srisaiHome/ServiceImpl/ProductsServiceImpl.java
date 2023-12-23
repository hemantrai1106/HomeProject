package com.srisaiHome.srisaiHome.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.srisaiHome.srisaiHome.Repository.ProductRepository;
import com.srisaiHome.srisaiHome.Service.ProductServices;
import com.srisaiHome.srisaiHome.model.Products;

@ComponentScan
@Service
public class ProductsServiceImpl implements ProductServices{
	
	
	@Autowired
	public ProductRepository Ps;

	@Override
	public List<Products> getProducts() {
		// TODO Auto-generated method stub
//		List<Products> productList=new ArrayList<>();
//		Products p1=new Products(1,"Petrol",10,true,120,"General");
//		Products p2=new Products(2,"Desiel",10,true,100,"General");
//		Products p3=new Products(3,"Thread",10,true,60,"Agriculture");
//		Products p4=new Products(4,"Uriya",10,true,320,"Agriculture");
//		productList.add(p4);
//		productList.add(p2);
//		productList.add(p3);
//		productList.add(p1);
		
		
		List<Products> productList=(List<Products>) this.Ps.findAll();
		System.out.println(productList);
			 
			 
			 
			 
			 
		return productList;
	}

	@Override
	public Products saveProduct(Products product) {
		// TODO Auto-generated method stub
		Products prod=this.Ps.save(product);
		return prod;
	}

	@Override
	public Optional<Products> getProductId(int id) {
		// TODO Auto-generated method stub
		Optional<Products> prod=this.Ps.findById(id);
		return prod;
	}

	@Override
	public Products updateProduct(Products ps) {
		// TODO Auto-generated method stub
		
	Products p=	this.Ps.save(ps);
		return p;
	}

}
