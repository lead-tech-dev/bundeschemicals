package com.mj.bundes.controller.admin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mj.bundes.service.ProductService;

@RestController
public class AdminResourceController {

	@Autowired
	private ProductService ProductService;
	
	@RequestMapping(value="/admin/Product/removeList", method=RequestMethod.POST)
	public String removeList(
			@RequestBody ArrayList<String> ProductIdList, Model model
			){
		
		for (String id : ProductIdList) {
			String ProductId =id.substring(8);
			ProductService.removeOne(Long.parseLong(ProductId));
		}
		
		return "delete success";
	}
}
