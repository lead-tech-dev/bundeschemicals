package com.mj.bundes.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mj.bundes.domain.Category;
import com.mj.bundes.domain.Product;

import com.mj.bundes.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/m")
    public String categoryManagement(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList", categoryList);

        return "admin/categoryManagement";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
  
    	public String addCategory(Model model) {
    	Category category = new Category();
    	    model.addAttribute("title", "Add Category");
    		model.addAttribute("category", category);
    	
        return "admin/addCategory";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCategoryPost(@Valid @ModelAttribute("category") Category category, BindingResult result){
    	
    	if (result.hasErrors()) {
            return "admin/saveCategory";
        }
        categoryService.save(category);
        return "redirect:/admin/category/categoryList";
    }
    
    @RequestMapping("/updateCategory")
	public String updateCategory(@RequestParam("id") Long id, Model model) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("category", category);
		
		return "admin/updateCategory";
	}
	
	@RequestMapping(value="/updateCategory", method=RequestMethod.POST)
	public String updateCategoryPost(@ModelAttribute("category") Category category, HttpServletRequest request) {
		categoryService.save(category);
		
		return "redirect:/admin/category/categoryInfo?id="+category.getCategoryId();
	
		}
    
    @RequestMapping("/categoryInfo")
	public String categoryInfo(@RequestParam("id") Long id, Model model) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("category", category);
		
		return "admin/categoryInfo";
	}

    @RequestMapping("/categoryList")
	public String categoryList(Model model) {
		List<Category> categoryList = categoryService.getAllCategory();
		model.addAttribute("categoryList", categoryList);		
		return "admin/categoryList";
		
	}
    
    @RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		categoryService.delete(Long.parseLong(id.substring(8)));
		List<Category> categoryList = categoryService.getAllCategory();
		model.addAttribute("categoryList", categoryList);
		
		return "redirect:/admin/category/categoryList";
	}

}
