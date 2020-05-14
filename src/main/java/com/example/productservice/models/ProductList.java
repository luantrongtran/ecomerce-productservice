package com.example.productservice.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductList {
	List<Product> products;

	public void addProduct(Product product) {
		if (products == null) {
			products = new ArrayList<>();
		}
		products.add(product);
	}
}
