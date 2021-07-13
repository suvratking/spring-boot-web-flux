package com.rt.boot.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.rt.boot.documents.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, ObjectId> {

}
