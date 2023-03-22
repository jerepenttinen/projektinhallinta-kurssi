package com.tamkstudents.cookbook.Domain.Dao;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity(name = "login_user")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginUserDao implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_user_seq")
    @SequenceGenerator(name = "login_user_seq", sequenceName = "login_user_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "login_user_name")
    private String loginUsername;

    @Column(name = "login_user_password")
    private String password;

    @Column(name = "login_user_firstname")
    private String firstName;

    @Column(name = "login_user_lastname")
    private String lastName;

    @Column(name = "user_profile")
    private Long profileId;

    @Column(name = "user_email")
    private String email;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
