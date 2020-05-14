package com.example.productservice.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice.models.ProductList;
import com.example.productservice.services.ProductService;

@RestController
public class ProductController {

	ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = "/products")
	public ProductList listAll() {
		ProductList productList = productService.findAll();
		return productList;
	}
}
