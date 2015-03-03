package com.gf.st.ab.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Rashidi Zin
 */
public interface UserTokenRepository extends MongoRepository<UserToken, String> {


}
