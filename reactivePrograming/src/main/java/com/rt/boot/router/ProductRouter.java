package com.rt.boot.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.rt.boot.handdler.ProductHandler;

@Configuration
public class ProductRouter {
	
	@Autowired
	private ProductHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> productRout(){
		return RouterFunctions.route()
				.GET("/reactive/prods", handler::getAllProd)
				.GET("/reactive/prod/{id}", handler::getById)
				.POST("/reactive/save", handler::save)
				.PUT("/reactive/update", handler::update)
				.DELETE("/reactive/delete/{id}", handler::delete)
				.build();
	}

}
