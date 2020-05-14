package com.example.productservice.configurations;

public interface PagingConfiguration {
	default public int getRowsPerPage() {
		return 50;
	}
}
