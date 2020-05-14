package com.example.productservice.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ExtraIdentificationCompositeId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1114532005035171651L;

	@Column(name = "product_id")
	Long productId;

	@Column(name = "product_identification_type_id")
	Long identificationTypeId;
}