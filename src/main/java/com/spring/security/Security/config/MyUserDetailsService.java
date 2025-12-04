package com.spring.security.Security.config;

import com.spring.security.Security.entity.MyUser;
import com.spring.security.Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//this class will go and fetch the data from the database
@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository repository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findByUsername(username);
        System.out.println(user.get().getUsername());
        return  user.map(MyUserDetails::new)
                .orElseThrow(()->
                        new UsernameNotFoundException("user not found "+ username));
    }
}
