package com.example.productservice.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties("id")
@Entity
@Table(name="product_identification_types")
public class ExtraIdentification {

	@EmbeddedId
	ExtraIdentificationCompositeId id;

	@JsonBackReference
	@ManyToOne
	@MapsId("productId")
	private Product product;

	@ManyToOne
	@MapsId("identificationTypeId")
	private ProductIdType idType;

	@Column(name = "product_id_number")
	private String productIdNumber;
}
