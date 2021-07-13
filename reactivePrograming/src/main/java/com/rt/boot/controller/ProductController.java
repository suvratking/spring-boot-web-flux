package com.rt.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rt.boot.documents.Product;
import com.rt.boot.dto.ProdDto;
import com.rt.boot.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/prods")
	public Flux<ProdDto> getProducts(){
		return productService.getAllProd();
	}
	
	@GetMapping("/prod/{id}")
	public Mono<ProdDto> getProduct(@PathVariable String id){
		return productService.getById(id);
	}
	
	@PostMapping("/save")
	public Mono<Product> saveProd(@RequestBody ProdDto prod){
		return productService.save(prod);
	}
	
	@PutMapping("/update")
	public Mono<Product> updateProd(@RequestBody ProdDto prod){
		return productService.update(prod);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Void> deleteProd(@PathVariable String id){
		return productService.delete(id);
	}

}
