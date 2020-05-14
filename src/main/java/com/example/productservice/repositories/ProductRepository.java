package com.example.productservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.productservice.models.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
