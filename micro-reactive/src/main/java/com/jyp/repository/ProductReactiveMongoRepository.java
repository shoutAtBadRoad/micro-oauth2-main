package com.jyp.repository;

import com.jyp.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ProductReactiveMongoRepository extends ReactiveMongoRepository<Product, String>,
                                                    ReactiveQueryByExampleExecutor<Product> {

}
