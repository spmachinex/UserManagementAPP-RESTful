package com.becoder.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.becoder.model.UserDtls;
import com.becoder.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDtls user = userRepo.findByEmail(email);

        if (user != null) {
            return new CustomUserDetails(user);
        }

        throw new UsernameNotFoundException("User not available");
    }

    public String generateToken(UserDetails userDetails) {
        try {
            // Logic to generate JWT token
            // Use JwtBuilder from a library like JJwt
            // Set claims, expiration, signing key, etc.
            return Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // Token valid for 10 days
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
        } catch (Exception e) {
            // Handle the exception (e.g., log it or throw a custom exception)
            throw new RuntimeException("Failed to generate JWT token", e);
        }
    }
}
