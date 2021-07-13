package com.rt.boot.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "product1")
public class Product {
	
	@Id
	private ObjectId id;
	
	private String name;
	
	private String category;
	
	private Double price;

}
