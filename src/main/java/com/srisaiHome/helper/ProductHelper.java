package com.srisaiHome.helper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.srisaiHome.srisaiHome.model.Products;

public class ProductHelper {

	
	
	
	public Map<String,List<Products>> productDetails(List<Products> psList)
	{
		Map<String,List<Products>> productMap=new HashMap<String, List<Products>>();
		
		Set<String> productTypeList=psList.stream().map(p->p.getProductType()).collect(Collectors.toSet());
		
		for(var ptype:productTypeList)
		{
			List<Products> prod=psList.stream().filter(p->p.getProductType().equalsIgnoreCase(ptype)).collect(Collectors.toList());
			
		}
	
		return productMap;
		
	}
}
