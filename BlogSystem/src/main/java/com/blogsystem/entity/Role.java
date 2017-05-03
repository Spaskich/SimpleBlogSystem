package com.blogsystem.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by Spaskich on 1.5.2017 г..
 */
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public Role() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
