package com.gf.st.ab.service;

import com.gf.st.ab.AbstractMockTests;
import com.gf.st.ab.domain.Authorization;
import com.gf.st.ab.domain.AuthorizationRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Rashidi Zin
 */
public class UserDetailsServiceImplTest extends AbstractMockTests {

    @Mock
    AuthorizationRepository repository;

    @InjectMocks
    UserDetailsServiceImpl $;

    private Authorization authorization = new Authorization("zeus", "th3m!GH7yZ3u5");

    @Test
    public void usernameIsEmpty() {
        expect.expect(UsernameNotFoundException.class);
        expect.expectMessage("invalid username");

        $.loadUserByUsername("");
    }

    @Test
    public void loadUserByUsername() {
        when(repository.findOne(authorization.getUsername())).thenReturn(authorization);

        $.loadUserByUsername(authorization.getUsername());

        verify(repository).findOne(authorization.getUsername());
    }

    @Test
    public void loadWithNotExistingAccount() {
        expect.expect(UsernameNotFoundException.class);
        expect.expectMessage("invalid username");

        final String username = "Hercules";

        when(repository.findOne(username)).thenReturn(null);

        $.loadUserByUsername(username);

        verify(repository).findOne(username);
    }
}
