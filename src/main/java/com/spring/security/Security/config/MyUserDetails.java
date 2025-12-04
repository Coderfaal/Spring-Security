package com.spring.security.Security.config;

import com.spring.security.Security.entity.MyUser;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {



    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    public MyUserDetails(MyUser user) {
        System.out.println("from MyUserDetails()"+user);
        username=user.getUsername();
        password = user.getPassword();
        authorities = Arrays.stream(user.getRole().split(","))
                .map(role -> new SimpleGrantedAuthority(
                        "ROLE_"+   role))                                                                     //for multiple roles just mention role,   for one role  user.getRole()))   // .map(SimpleGrantedAuthority::new) old code
                .collect( Collectors.toList());
        // String role = "ADMIN,USER,OPS,HR"
        // CONVERTING A COMMA SEPARATED STRING INTO AN ARRAYLIST

    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
