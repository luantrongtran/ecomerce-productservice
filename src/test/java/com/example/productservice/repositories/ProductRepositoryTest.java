package com.example.productservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.productservice.models.Product;

@DataJpaTest
public class ProductRepositoryTest {
	@Autowired
	ProductRepository productRepo;

	@Test
	public void testFindAllPageable() {
		Iterable<Product> iter = productRepo.findAll();
	}
}