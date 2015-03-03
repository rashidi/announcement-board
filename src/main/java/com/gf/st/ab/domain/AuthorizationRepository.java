package com.gf.st.ab.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Rashidi Zin
 */
public interface AuthorizationRepository extends MongoRepository<Authorization, String> {


}
