package com.rt.boot.service;

import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.boot.documents.Product;
import com.rt.boot.dto.ProdDto;
import com.rt.boot.repo.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Flux<ProdDto> getAllProd(){
		Flux<Product> prods = productRepository.findAll().log();
		Flux<ProdDto> prod = prods.map(product -> ProdDto.builder().id(product.getId().toHexString()).name(product.getName()).category(product.getCategory()).price(product.getPrice()).build());
		System.out.println(prod);
		return prod;
	}
	
	public Mono<ProdDto> getById(String id){
		Mono<Product> pr = productRepository.findById(new ObjectId(id)).log();
		Mono<ProdDto> mono = pr.map(product -> ProdDto.builder().id(product.getId().toString()).name(product.getName()).category(product.getCategory()).price(product.getPrice()).build());
		return mono;
	}

	public Mono<Product> save(ProdDto prod) {
		Product product = new Product();
		BeanUtils.copyProperties(prod, product);
		return productRepository.save(product).log();
	}

	public Mono<Product> update(ProdDto prod) {
		Product product = new Product();
		BeanUtils.copyProperties(prod, product);
		product.setId(new ObjectId(prod.getId()));
		return productRepository.save(product);
	}

	public Mono<Void> delete(String id) {
		return productRepository.deleteById(new ObjectId(id)).log();
	}

}
