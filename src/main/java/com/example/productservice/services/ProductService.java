package com.example.productservice.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.productservice.models.ProductList;

public interface ProductService {
	public ProductList findAll();

}
