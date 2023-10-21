package com.ceam.admin.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author CeaM
 * 2023/01/20 11:12
 **/
@Getter
public class JwtUser implements UserDetails {

    private Long id;

    private String username;

    private String nickname;

    private String avatar;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Collection<SimpleGrantedAuthority> authorities;

    private boolean enabled;

    public JwtUser(Long id,
                   String username,
                   String password,
                   boolean enabled,
                   String nickname,
                   String avatar,
                   Collection<SimpleGrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.nickname = nickname;
        this.avatar = avatar;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return this.enabled;
    }

    // 返回前端roles
    public Collection getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }
}
