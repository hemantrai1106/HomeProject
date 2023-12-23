package com.srisaiHome.srisaiHome.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srisaiHome.helper.Message;
import com.srisaiHome.srisaiHome.Repository.UserRepository;
import com.srisaiHome.srisaiHome.Service.ProductServices;
import com.srisaiHome.srisaiHome.model.Products;
import com.srisaiHome.srisaiHome.model.User;


@Controller
@RequestMapping("/user")
public class homeController {

	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	public ProductServices ps1;
	@Autowired
	public UserRepository UserResp;

	@GetMapping("/home")
	public String HomePage(Model m)
	{
		m.addAttribute("title", "Home- kisan kendra");
		return "home1";
	}

	@GetMapping("/addProducts")
	public String addProducts(Model m,HttpSession session)
	{
		m.addAttribute("product", new Products());
		m.addAttribute("page", "addProduct");
		session.setAttribute("message", new Message());
		return "addProduct";

	}
	
	
	@PostMapping("/productAdd")
	public String addProduct(@Valid @ModelAttribute("product") Products ps,BindingResult result,Model m,HttpSession session)
	{
		try {
			if(result.hasErrors())
			{
				session.setAttribute("message", new Message());
				System.out.println("Error:"+result.toString());
				m.addAttribute("product", ps);
				m.addAttribute("page", "addProduct");
				return "addProduct";
			}
			else {
		System.out.println(ps);
		Products prod=this.ps1.saveProduct(ps);
		System.out.println(prod);
		session.setAttribute("message", new Message("product added Successfully!!","alert-success"));
		m.addAttribute("product", new Products());
		m.addAttribute("page", "addProduct");
		return "addProduct";
			}
		}
		catch(Exception e)
		{
			session.setAttribute("message", new Message("Something went wrong!!","alert-danger"));
			m.addAttribute("product", ps);
			m.addAttribute("page", "addProduct");
			return "addProduct";
		}

	}
	
	
	
	
	@GetMapping("/Login")
	public String LoginPage(Model m)
	{
		m.addAttribute("title", "Login- kisan kendra");

		m.addAttribute("user", new User());
		return "login";
	}
	
	
	
	@PostMapping("/LoginUser")
	public String LoginUser(@Valid @ModelAttribute("user") User us ,BindingResult result,Model m)
	{
		m.addAttribute("title", "Login- kisan kendra");

		System.out.println(us);
//		if(result.hasErrors())
//		{
//			//session.setAttribute("message", new Message());
//			System.out.println("Error:"+result.toString());
//			m.addAttribute("user", us);
//			return "login";
//		}
		
		if(us.getUsername()!=null && us.getPassword()!=null)
		{
			List<User> listofUser=UserResp.findByName(us.getUsername(),us.getPassword());
			if(listofUser.size()>0)
			{
				System.out.println("user"+listofUser);
				List<Products> productList=ps1.getProducts();
				Set<String> productTypeList=productList.stream().map(p->p.getProductType()).collect(Collectors.toSet());
				System.out.println(productTypeList);
				m.addAttribute("productTypeList", productTypeList);
				m.addAttribute("productList", productList);
				return "productDashboard";
			}
		}
		return "login";
	}
	
	
	
	@GetMapping("/about")
	public String AboutPage(Model m)
	{
		m.addAttribute("title", "About- kisan kendra");
		
		return "about";
	}

	
	
	
	
	@GetMapping("/signUp")
	public String signUp(Model m,HttpSession session)
	{
		m.addAttribute("title", "SignUp- kisan kendra");

		m.addAttribute("user", new User());
		session.setAttribute("message", new Message());
		return "signup";
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
			us.setRole("USER");
			//us.setPassword(this.passwordEncoder.encode(us.getPassword()));
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
	
	
	
	@PostMapping("/productUpdate")
	public String updateProduct(@ModelAttribute("product") Products ps,Model m,HttpSession session)
	{
		System.out.println("products return"+ps);
		m.addAttribute("title", "Update Product");
		System.out.println("products return"+ps);
		Products p1=ps1.saveProduct(ps);
		if(p1!=null)
		{
			session.setAttribute("message", new Message("Updated Successfully!!","alert-success"));
		}
	System.out.println(p1);
		
		
		return "updateProduct";
	}
}
