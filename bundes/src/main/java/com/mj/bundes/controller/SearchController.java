package com.mj.bundes.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mj.bundes.domain.Category;
import com.mj.bundes.domain.Product;
import com.mj.bundes.domain.User;
import com.mj.bundes.service.CategoryService;
import com.mj.bundes.service.ProductService;
import com.mj.bundes.service.UserService;

@Controller
public class SearchController {
	@Autowired
	private UserService userService;
	
    @Autowired
    private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@RequestMapping("/searchByCategory")
	public String searchByCategory(@RequestParam("category") String category, Model model, Principal principal) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}

		String classActiveCategory = "active" + category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);

		List<Product> productList = productService.getProductsByMainCategory(category);

		if (productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "productshelf";
		}

		model.addAttribute("productList", productList);
		model.addAttribute("category", category);

		return "productshelf";
	}

	@RequestMapping("/searchProduct")
	public String searchProduct(@ModelAttribute("keyword") String keyword, Principal principal, Model model) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}

		List<Product> productList = productService.blurrySearch(keyword);

		if (productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "productshelf";
		}

		model.addAttribute("productList", productList);

		return "productshelf";
	}

	@RequestMapping("/search")
	public String getProductsByKeyword(@RequestParam(value = "na", required = false) String productName,
			@RequestParam(value = "mc", required = false) String mainCategoryName,
			@RequestParam(value = "sc", required = false) String subCategoryName,
			@RequestParam(value = "st", required = false) String sortType,
			@RequestParam(value = "lp", required = false) String lowerPrice,
			@RequestParam(value = "hp", required = false) String higherPrice, Model model) throws IOException {
		List<Category> mainCategoryNameList = categoryService.getAllCategory();
		// filter initiate
		boolean nameFilter = false;
		boolean mainCategoryFilter = false;
		boolean subCategoryFilter = false;
		boolean needSort = false;
		boolean priceFilter = false;

		int lowerPrice_i = 0;
		int higherPrice_i = 0;

		/* All the required filter check here */
		if (productName != null) {
			nameFilter = true;
		}
		if (mainCategoryName != null) {
			mainCategoryFilter = true;
		}
		if (subCategoryName != null) {
			subCategoryFilter = true;
		}
		if (sortType != null) {
			needSort = true;
		}
		if (lowerPrice != null && higherPrice != null) {
			lowerPrice_i = Integer.parseInt(lowerPrice);
			higherPrice_i = Integer.parseInt(higherPrice);
			priceFilter = true;
		}

		// get all product
		List<Product> products = productService.findAll();

		// Filter by product name
		Iterator<Product> iter = products.iterator();

		if (nameFilter) {
			while (iter.hasNext()) {
				Product product = iter.next();
				if (!product.getName().toLowerCase().contains(productName.toLowerCase())) {
					iter.remove();
				}
			}
		}
		// Filter by product mainCategory
		iter = products.listIterator();
		if (mainCategoryFilter) {
			while (iter.hasNext()) {
				Product product = iter.next();
				Category category = product.getProductCategory();
				if (!category.getMainCategoryName().equalsIgnoreCase(mainCategoryName)) {
					iter.remove();
				}
			}
		}
		// Filter by product subCategory
		iter = products.listIterator();
		if (subCategoryFilter) {
			while (iter.hasNext()) {
				Product product = iter.next();
				Category category = product.getProductCategory();
				if (!category.getSubCategoryName().equalsIgnoreCase(subCategoryName)) {
					iter.remove();
				}
			}
		}

		// Filter by product price
		iter = products.listIterator();
		if (priceFilter) {
			while (iter.hasNext()) {
				Product product = iter.next();
				if (product.getProductPrice() <= lowerPrice_i || product.getProductPrice() >= higherPrice_i) {
					iter.remove();
				}
			}
		}
		// create subCategory List

		Set<String> subCategoryList = new HashSet<String>();
		for (Product product : products) {
			subCategoryList.add(product.getProductCategory().getSubCategoryName());

		}
		// sort product
		if (needSort) {
			products = productService.sort(products, sortType);
		}

		model.addAttribute("products", products);
		model.addAttribute("mainCategoryNameList", mainCategoryNameList);
		model.addAttribute("activeAll", true);

		return "productshelf";
	}
}
