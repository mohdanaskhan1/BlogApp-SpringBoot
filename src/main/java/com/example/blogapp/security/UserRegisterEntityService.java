package com.example.blogapp.security;

import com.example.blogapp.entity.UserRegisterEntity;
import com.example.blogapp.repository.UserRegisterEntityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterEntityService implements UserDetailsService {

    private final UserRegisterEntityRepository userRegisterEntityRepository;

    public UserRegisterEntityService(UserRegisterEntityRepository userRegisterEntityRepository) {
        this.userRegisterEntityRepository = userRegisterEntityRepository;
    }

    public UserDetails save(UserRegisterEntity userAuth) {
        return userRegisterEntityRepository.save(userAuth);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRegisterEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

    }
}
