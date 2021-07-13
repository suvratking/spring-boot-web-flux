package com.rt.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdDto {
	
	private String id;
	
	private String name;
	
	private String category;
	
	private Double price;

}
