package com.gf.st.ab.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Pattern;
import java.util.Date;

import static com.gf.st.ab.domain.UserStatus.ACTIVE;

/**
 * @author Rashidi Zin
 */
@Document
public class User {

    @Id
    private String id;

    @Pattern(regexp = "^[\\\\p{L} .'-]+$", message = "invalid name format")
    private String name;

    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "password is required")
    private String password;

    @Email(regexp = ".+@.+\\..+", message = "email address is invalid")
    private String email;

    private Date created = new Date();

    private UserStatus status = ACTIVE;

    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
