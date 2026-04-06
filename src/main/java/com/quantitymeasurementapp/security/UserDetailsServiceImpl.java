package com.quantitymeasurementapp.security;

import com.quantitymeasurementapp.entity.UserEntity;
import com.quantitymeasurementapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

// UserDetailsService - Spring Security ka ek interface
// Spring Security puche: "Rahul naam ka user hai system mein?"
// Hum batayein: "Haan/Nahi, ye lo details"
// Spring Security is interface ki implementation expect karta hai
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Spring Security khud is method ko call karta hai jab user verify karna ho
    // Username leke database se user dhundho
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // Database mein user dhundho
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() ->
                    // User nahi mila → exception throw karo
                    new UsernameNotFoundException("User not found: " + username)
                );

        // UserEntity (hamara custom class) ko Spring Security ka
        // UserDetails object mein convert karo
        // User(username, password, roles) - Spring Security ka built-in class
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                // Role ko GrantedAuthority mein convert karo
                // "ROLE_USER" → SimpleGrantedAuthority("ROLE_USER")
                Collections.singletonList(
                    new SimpleGrantedAuthority(userEntity.getRole())
                )
        );
    }
}
