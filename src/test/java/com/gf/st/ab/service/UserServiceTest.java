package com.gf.st.ab.service;

import com.gf.st.ab.AbstractMockTests;
import com.gf.st.ab.domain.User;
import com.gf.st.ab.domain.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

/**
 * @author Rashidi Zin
 */
public class UserServiceTest extends AbstractMockTests {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserService $;

    private User user = new User("prometheus@olympian.com", "prometheus", "z3u$'5|d3k!cK");

    @Test
    public void create() throws Exception {
        when(repository.findOneByEmailIgnoreCase(user.getEmail())).thenReturn(null);
        when(repository.findOneByUsernameIgnoreCase(user.getUsername())).thenReturn(null);
        when(repository.save(user)).thenReturn(user);

        $.create(user);

        verify(repository).findOneByEmailIgnoreCase(user.getEmail());
        verify(repository).findOneByUsernameIgnoreCase(user.getUsername());
        verify(repository).save(user);
    }

    @Test
    public void createWithExistingUsername() throws Exception {
        expect.expect(Exception.class);
        expect.expectMessage("username / email is already existed");

        when(repository.findOneByEmailIgnoreCase(user.getEmail())).thenReturn(user);
        when(repository.findOneByUsernameIgnoreCase(user.getUsername())).thenReturn(user);

        $.create(user);

        verify(repository).findOneByEmailIgnoreCase(user.getEmail());
        verify(repository).findOneByUsernameIgnoreCase(user.getUsername());
        verify(repository, times(0)).save(user);
    }
}
