package com.gf.st.ab.domain;

import com.gf.st.ab.AnnouncementBoardApplicationTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author Rashidi Zin
 */
public class UserTokenRepositoryTest extends AnnouncementBoardApplicationTests {

    @Autowired
    UserTokenRepository $;

    UserToken token = new UserToken("gremlins", "Z3JlbWxpbnMgYXJlIHByZXR0eQ==");

    @Before
    public void init() {
        token = $.save(token);
    }

    @Test
    public void findOne() {

        assertNotNull(
                $.findOne(token.getUsername())
        );

    }

    @After
    public void delete() {
        $.delete(token);
    }
}
