package com.example.productservice.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.productservice.configurations.PagingConfiguration;
import com.example.productservice.models.Product;
import com.example.productservice.models.ProductList;
import com.example.productservice.repositories.ProductRepository;

@Service
public class DefaultProductService implements ProductService {

	ProductRepository productRepository;

	PagingConfiguration pagingConfiguration;

	public DefaultProductService(ProductRepository productRepository, PagingConfiguration pagingConfiguration) {
		this.productRepository = productRepository;
		this.pagingConfiguration = pagingConfiguration;
	}

	@Override
	public ProductList findAll() {
		Pageable defaultPageable = PageRequest.of(0, pagingConfiguration.getRowsPerPage());
		List<Product> lstProducts = productRepository.findAll(defaultPageable).getContent();
		ProductList productList = ProductList.builder().products(lstProducts).build();
		return productList;
	}

}
