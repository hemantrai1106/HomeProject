package com.srisaiHome.srisaiHome.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srisaiHome.helper.Message;
import com.srisaiHome.srisaiHome.Repository.UserRepository;
import com.srisaiHome.srisaiHome.Service.ProductServices;
import com.srisaiHome.srisaiHome.model.Products;
import com.srisaiHome.srisaiHome.model.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	public ProductServices ps1;
	@Autowired
	public UserRepository UserResp;
	
	
	
	
	@PostMapping("/update-Product/{cid}")
	public String updateProductform(@PathVariable("cid") int id,Model m)
	{
		m.addAttribute("title", "Update Product");
		m.addAttribute("page", "UpdateProduct");
		Products p1=ps1.getProductId(id).get();
		
		System.out.println("update"+p1);
		m.addAttribute("product", p1);
		return "updateProduct";
	}
	
	
	
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User us ,BindingResult result,@RequestParam(value="agreement",defaultValue="false")boolean agreement, Model m,HttpSession session)
	{
		try {
		
		if(result.hasErrors())
		{
			session.setAttribute("message", new Message());
			System.out.println("Error:"+result.toString());
			m.addAttribute("user", us);
			return "signup";
		}
		if(!agreement)
		{
			System.out.println("please check aggrements");
			throw new Exception("please check aggrements");
		}
		else
		{
		User result1=	this.UserResp.save(us);
		System.out.println(result1);
		
		m.addAttribute("user", new User());
		session.setAttribute("message", new Message("Register Successfully!!","alert-success"));
		return "signup";
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("user", us);
			session.setAttribute("message", new Message("Something went wrong!!"+e.getMessage(),"alert-danger"));
			return "signup";
		}
		
	}

	
	@GetMapping("/productDashboard")
	public String getAllProducts(Model m)
	{
		List<Products> productList=ps1.getProducts();
		Set<String> productTypeList=productList.stream().map(p->p.getProductType()).collect(Collectors.toSet());
		System.out.println(productTypeList);
		m.addAttribute("productTypeList", productTypeList);
		m.addAttribute("productList", productList);
		return "productDashboard";
	}
	
	
	
	
	@PostMapping("/productUpdate")
	public String updateProduct(@ModelAttribute("product") Products ps,Model m)
	{
		m.addAttribute("title", "Update Product");
		System.out.println("products return"+ps);
		Products p1=ps1.saveProduct(ps);
	System.out.println(p1);
		
		
		return "updateProduct";
	}

}
