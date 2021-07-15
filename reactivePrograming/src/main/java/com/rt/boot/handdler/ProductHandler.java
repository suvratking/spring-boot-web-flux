package com.rt.boot.handdler;

import java.time.Duration;

import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.rt.boot.documents.Product;
import com.rt.boot.dto.ProdDto;
import com.rt.boot.repo.ProductRepository;
import com.rt.boot.utils.Response;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductHandler {

	@Autowired
	private ProductRepository productRepository;
	
	public Mono<ServerResponse> getAllProd(ServerRequest request){
		Flux<Product> prods = productRepository.findAll().log().delayElements(Duration.ofSeconds(1));
		Flux<ProdDto> prod = prods.map(this::productDtoMappig);
		System.out.println(prod);
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(prod, ProdDto.class);
	}
	
	public Mono<ServerResponse> getById(ServerRequest request){
		String id = request.pathVariable("id");
		Mono<Product> pr = productRepository.findById(new ObjectId(id)).log();
		Mono<ProdDto> mono = pr.map(this::productDtoMappig);
		return ServerResponse.ok().body(mono, ProdDto.class);
	}
	
	public Mono<ServerResponse> save(ServerRequest request){
		Mono<Product> prod = request.bodyToMono(ProdDto.class).flatMap(p -> {
			Product product = new Product();
			BeanUtils.copyProperties(p, product);
			return productRepository.save(product).log();
		});
		return ServerResponse.ok().body(prod, Product.class);
	}
	
	public Mono<ServerResponse> update(ServerRequest request){
		Mono<Product> prod = request.bodyToMono(ProdDto.class).flatMap(p -> {
			Product product = new Product();
			BeanUtils.copyProperties(p, product);
			product.setId(new ObjectId(p.getId()));
			return productRepository.save(product).log();
		});
		return ServerResponse.ok().body(prod, Product.class);
	}
	
	public Mono<ServerResponse> delete(ServerRequest request){
		String id = request.pathVariable("id");
		return ServerResponse.ok().body(productRepository.deleteById(new ObjectId(id)).log(), Response.class);
	}
	
	private ProdDto productDtoMappig(Product product) {
		return ProdDto.builder().
				id(product.getId().toString()).
				name(product.getName()).
				category(product.getCategory()).
				price(product.getPrice()).
				build();
	}
	
}
