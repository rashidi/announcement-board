package com.gf.st.ab;

import com.gf.st.ab.domain.Authorization;
import com.gf.st.ab.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static com.gf.st.ab.domain.UserStatus.ACTIVE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpStatus.*;

@IntegrationTest("server.port:0")
@DirtiesContext
public class AnnouncementBoardApplicationTests extends AbstractTests {

	@Value("${local.server.port}")
	private int port;

	private String url;
	private TestRestTemplate restTemplate;
	private Authorization authorization;

	private User user = new User("morpheus@nebuchadnezzar.com", "morpheus", "ey3|348th31");

	@Before
	public void init() {
		url = String.format("http://localhost:%d", this.port);

		createUser();
		authorize();

		restTemplate = new TestRestTemplate(authorization.getUsername(), authorization.getToken());
	}

	@Test
	public void getUser() {
		ResponseEntity<User> response = restTemplate.getForEntity(String.format("%s/users/%s", url, user.getId()), User.class);

		assertEquals(OK, response.getStatusCode());
		assertEquals(user.getUsername(), response.getBody().getUsername());
		assertEquals(user.getEmail(), response.getBody().getEmail());
	}

	@Test
	public void getNonExistingUser() {
		ResponseEntity<User> response = restTemplate.getForEntity(url + "/users/50m3r480MnUm8eR", User.class);

		assertEquals(NOT_FOUND, response.getStatusCode());
	}

	@After
	public void delete() {
		deleteUser();
		logout();
	}

	private void createUser() {
		ResponseEntity<User> response = new TestRestTemplate().postForEntity(url + "/users", user, User.class);

		assertEquals(CREATED, response.getStatusCode());
		assertEquals(ACTIVE, response.getBody().getStatus());

		user.setId(response.getBody().getId());
	}

	private void authorize() {
		ResponseEntity<Authorization> response = new TestRestTemplate().postForEntity(url + "/authorization/login", user, Authorization.class);

		assertEquals(OK, response.getStatusCode());

		authorization = response.getBody();

		assertNotNull(authorization.getUsername());
		assertNotNull(authorization.getToken());
	}

	private void deleteUser() {
		String userUrl = String.format("%s/users/%s", url, user.getId());

		restTemplate.delete(userUrl);

		ResponseEntity<User> response = restTemplate.getForEntity(userUrl, User.class);

		assertEquals(NOT_FOUND, response.getStatusCode());
	}

	private void logout() {
		ResponseEntity response = restTemplate.postForEntity(url + "/authorization/logout", null, Authorization.class);

		assertEquals(OK, response.getStatusCode());
	}
}
