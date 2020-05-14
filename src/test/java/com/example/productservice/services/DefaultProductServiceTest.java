package com.example.productservice.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.productservice.configurations.DefaultPagingConfiguration;
import com.example.productservice.configurations.PagingConfiguration;
import com.example.productservice.models.ExtraIdentification;
import com.example.productservice.models.ExtraIdentificationCompositeId;
import com.example.productservice.models.Product;
import com.example.productservice.models.ProductIdType;
import com.example.productservice.models.ProductList;
import com.example.productservice.models.ProductSubType;
import com.example.productservice.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class DefaultProductServiceTest {

	DefaultProductService defaultProductService;

	@Mock
	private ProductRepository productRepository;

	private PagingConfiguration pagingConfiguration;

	@Test
	public void testFindAll_ReturnEmptyProductsList() {
		// given
		pagingConfiguration = new DefaultPagingConfiguration();
		defaultProductService = new DefaultProductService(productRepository, pagingConfiguration);

		Pageable pageable = PageRequest.of(0, 50);
		Page<Product> pageProduct = Mockito.mock(Page.class);
		List<Product> mockedLstProducts = new ArrayList<>();
		Mockito.when(pageProduct.getContent()).thenReturn(mockedLstProducts);
		when(productRepository.findAll(pageable)).thenReturn(pageProduct);

		// when
		ProductList lstProducts = defaultProductService.findAll();

		// then
		assertThat(lstProducts).isNotNull();
		assertThat(lstProducts.getProducts()).isNotNull();
		assertThat(lstProducts.getProducts().size()).isEqualTo(mockedLstProducts.size());
	}

	@Test
	public void testFindAll_Return1ItemProductsList() {
		// given
		pagingConfiguration = new DefaultPagingConfiguration();
		defaultProductService = new DefaultProductService(productRepository, pagingConfiguration);

		Pageable pageable = PageRequest.of(0, 50);
		Page<Product> pageProduct = Mockito.mock(Page.class);
		List<Product> mockedLstProducts = new ArrayList<>();

		Product prod1 = Product.builder().id(1l).productName("Toilet Paper")
				.productDescription("Toilet Paper")
				.productSubType(ProductSubType.builder().id(1l).name("Goods").build()).build();

		List<ExtraIdentification> extraIds = new ArrayList<>();
		ProductIdType skuType = ProductIdType.builder().id(1l).name("SKU").build();
		extraIds.add(ExtraIdentification.builder()
				.id(ExtraIdentificationCompositeId.builder().identificationTypeId(skuType.getId())
						.productId(prod1.getId()).build())
				.idType(skuType).productIdNumber("ToiletPaper12p").build());

		prod1.setOtherProductIds(extraIds);
		mockedLstProducts.add(prod1);

		Mockito.when(pageProduct.getContent()).thenReturn(mockedLstProducts);
		when(productRepository.findAll(pageable)).thenReturn(pageProduct);

		// when
		ProductList lstProducts = defaultProductService.findAll();

		// then
		assertThat(lstProducts).isNotNull();
		assertThat(lstProducts.getProducts()).isNotNull();
		assertThat(lstProducts.getProducts().size()).isEqualTo(mockedLstProducts.size());
		assertThat(lstProducts.getProducts().size()).isEqualTo(1);

		// assert 1st item
		Product actualProd1 = lstProducts.getProducts().get(0);
		Product expectedProd1 = mockedLstProducts.get(0);

		assertThat(actualProd1).isEqualTo(expectedProd1);
	}
}
