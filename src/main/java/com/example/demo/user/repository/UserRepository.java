package com.example.demo.user.repository;

import com.example.demo.user.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

//    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);

}
