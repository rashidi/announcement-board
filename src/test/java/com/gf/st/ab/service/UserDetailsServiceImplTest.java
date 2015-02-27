package com.gf.st.ab.service;

import com.gf.st.ab.AbstractMockTests;
import com.gf.st.ab.domain.User;
import com.gf.st.ab.domain.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.*;

/**
 * @author Rashidi Zin
 */
public class UserDetailsServiceImplTest extends AbstractMockTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserDetailsServiceImpl $;

    private User user = new User("zues@greek.com", "zeus", "th3m!GH7yZ3u5");

    @Test
    public void usernameIsNull() {
        expect.expect(IllegalArgumentException.class);
        expect.expectMessage("username is required");

        $.loadUserByUsername(null);
    }

    @Test
    public void usernameIsEmpty() {
        expect.expect(IllegalArgumentException.class);
        expect.expectMessage("username is required");

        $.loadUserByUsername("");
    }

    @Test
    public void loginWithUsername() {
        when(userRepository.findOneByUsernameIgnoreCase(user.getUsername())).thenReturn(user);

        $.loadUserByUsername(user.getUsername());

        verify(userRepository).findOneByUsernameIgnoreCase(user.getUsername());
    }

    @Test
    public void loginWithEmail() {
        when(userRepository.findOneByEmailIgnoreCase(user.getEmail())).thenReturn(user);

        $.loadUserByUsername(user.getEmail());

        verify(userRepository).findOneByEmailIgnoreCase(user.getEmail());
    }

    @Test
    public void loginWithNotExistingAccount() {
        expect.expect(UsernameNotFoundException.class);
        expect.expectMessage("invalid username");

        final String username = "Hercules";

        when(userRepository.findOneByUsernameIgnoreCase(username)).thenReturn(null);

        $.loadUserByUsername(username);

        verify(userRepository).findOneByEmailIgnoreCase(username);
    }
}
