package com.example.productservice.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@Entity
@Table(name = "tables")
public class Product {
	@JsonProperty("id")
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * e.g. ISBN, Universal Product Code American (UPCA), or Universal Product Code
	 * Europe (UPCE)
	 */
	@JsonProperty("otherProductIds")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@JsonManagedReference
	private List<ExtraIdentification> otherProductIds;

	@JsonProperty("productName")
	@Column(name = "product_name")
	private String productName;

	@JsonProperty("productDescription")
	@Column(name = "product_description")
	private String productDescription;

	@JsonProperty("productSubType")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_sub_type")
	private ProductSubType productSubType;
}
