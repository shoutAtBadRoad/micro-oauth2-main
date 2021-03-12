package com.jyp.repository;

import com.jyp.entity.UserMsg;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface MsgMongoRepository extends ReactiveMongoRepository<UserMsg, String>,
        ReactiveQueryByExampleExecutor<UserMsg> {

}
