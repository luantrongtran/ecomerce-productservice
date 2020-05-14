package com.example.productservice.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.productservice.models.ExtraIdentification;
import com.example.productservice.models.ExtraIdentificationCompositeId;
import com.example.productservice.models.Product;
import com.example.productservice.models.ProductIdType;
import com.example.productservice.models.ProductList;
import com.example.productservice.models.ProductSubType;
import com.example.productservice.services.ProductService;

@WebMvcTest
public class ProductControllerTest {

	@MockBean
	ProductService productService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testGetProductList_returnProperListOfProducts() throws Exception {

		// given
		ProductList expectedProductList = new ProductList();

		ProductSubType expectedProductSubType = ProductSubType.builder().id(1l).name("Goods").build();

		ProductIdType isbnType = ProductIdType.builder().id(1l).name("ISBN").build();
		Product expectedProd1 = Product.builder().id(1l).productName("Gone with the Wind")
				.productDescription("The story ...").productSubType(expectedProductSubType).build();

		List<ExtraIdentification> otherProductIds = new ArrayList<>();
		ExtraIdentification anotherId = ExtraIdentification.builder()
				.id(ExtraIdentificationCompositeId.builder().identificationTypeId(isbnType.getId())
						.productId(expectedProd1.getId()).build())
				.product(expectedProd1).idType(isbnType).productIdNumber("ISBN-123").build();
		otherProductIds.add(anotherId);

		expectedProd1.setOtherProductIds(otherProductIds);

		expectedProductList.addProduct(expectedProd1);

		when(productService.findAll()).thenReturn(expectedProductList);

		// when
		ResultActions resultActions = mockMvc.perform(get("/products"));

		// then
		// expect status 200
		resultActions.andExpect(status().isOk());

		// then expect JsON returned contain a list of products
		resultActions.andExpect(jsonPath("$.products").exists());
		// then expect the 1st product does exists
		String firstProdJsonPath = "$.products[0]";
		resultActions.andExpect(jsonPath(firstProdJsonPath).exists());
		// assert 1st product
		// id (primary key)
		resultActions.andExpect(jsonPath(firstProdJsonPath + ".id", is(expectedProd1.getId().intValue())));
		// product ids
		resultActions.andExpect(jsonPath(firstProdJsonPath + ".otherProductIds").exists());
		resultActions.andExpect(jsonPath(firstProdJsonPath + ".otherProductIds[0]").exists());
		resultActions.andExpect(jsonPath(firstProdJsonPath + ".otherProductIds[0].idType.name",
				is(expectedProd1.getOtherProductIds().get(0).getIdType().getName())));
		resultActions.andExpect(jsonPath(firstProdJsonPath + ".otherProductIds[0].productIdNumber",
				is(expectedProd1.getOtherProductIds().get(0).getProductIdNumber())));
		// product name
		resultActions.andExpect(jsonPath(firstProdJsonPath + ".productName").exists());
		resultActions.andExpect(jsonPath(firstProdJsonPath + ".productName", is(expectedProd1.getProductName())));
		// product description
		resultActions.andExpect(jsonPath(firstProdJsonPath + ".productDescription").exists());
		resultActions.andExpect(
				jsonPath(firstProdJsonPath + ".productDescription", is(expectedProd1.getProductDescription())));

		// product sub type
		resultActions.andExpect(jsonPath(firstProdJsonPath + ".productSubType").exists());
		resultActions.andExpect(jsonPath(firstProdJsonPath + ".productSubType.id",
				is(expectedProd1.getProductSubType().getId().intValue())));
		resultActions.andExpect(
				jsonPath(firstProdJsonPath + ".productSubType.name", is(expectedProd1.getProductSubType().getName())));
	}

}
