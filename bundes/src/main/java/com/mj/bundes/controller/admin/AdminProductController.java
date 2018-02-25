package com.mj.bundes.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mj.bundes.domain.Category;
import com.mj.bundes.domain.Product;
import com.mj.bundes.service.ProductService;
import com.mj.bundes.service.CategoryService;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private  CategoryService  categoryService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("categoryList", categoryService.getAllCategory());
		model.addAttribute("product", product);
		return "admin/addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {
		productService.save(product);

		MultipartFile productImage = product.getProductImage();

		try {
			byte[] bytes = productImage.getBytes();
			String name = product.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/product/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/product/productList";
	}
	
	@RequestMapping("/productInfo")
	public String productInfo(@RequestParam("id") Long id, Model model) {
		Product product = productService.findOne(id);
		model.addAttribute("product", product);
		
		return "admin/productInfo";
	}
	
	@RequestMapping("/updateProduct")
	public String updateProduct(@RequestParam("id") Long id, Model model) {
		Product product = productService.findOne(id);
		model.addAttribute("categoryList", categoryService.getAllCategory());
		model.addAttribute("product", product);
		
		return "admin/updateProduct";
	}
	
	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public String updateProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {
		productService.save(product);
		
		MultipartFile productImage = product.getProductImage();
		
		if(!productImage.isEmpty()) {
			try {
				byte[] bytes = productImage.getBytes();
				String name = product.getId() + ".png";
				
				Files.delete(Paths.get("src/main/resources/static/image/product/"+name));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/product/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/admin/product/productInfo?id="+product.getId();
	}
	
	@RequestMapping("/productList")
	public String productList(Model model) {
		List<Product> productList = productService.findAll();
	
		model.addAttribute("productList", productList);	
		
		 
		return "admin/productList";
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		productService.removeOne(Long.parseLong(id.substring(8)));
		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);
		
		return "redirect:/admin/product/productList";
	}

}
