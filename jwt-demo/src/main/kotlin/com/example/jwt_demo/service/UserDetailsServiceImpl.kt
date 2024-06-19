package com.example.jwt_demo.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        if (username == "user") {
            return User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build()
        } else {
            throw UsernameNotFoundException("User not found")
        }
    }
}
