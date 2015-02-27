package com.gf.st.ab;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.rules.ExpectedException.none;

/**
 * @author Rashidi Zin
 */

@RunWith(MockitoJUnitRunner.class)
public class AbstractMockTests {

    @Rule
    public ExpectedException expect = none();

    @Test
    public void isTrue() {
        assertTrue(true);
    }
}
