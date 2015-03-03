package com.gf.st.ab;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Rashidi Zin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AnnouncementBoardApplication.class)
@WebAppConfiguration
public class AbstractTests {

    @Test
    public void isTrue() {
        Assert.assertTrue(true);
    }
}
