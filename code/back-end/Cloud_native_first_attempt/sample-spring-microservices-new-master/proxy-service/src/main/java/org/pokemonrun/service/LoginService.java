package org.pokemonrun.service;


import org.pokemonrun.client.UserClient;
import org.pokemonrun.entity.JwtToken;
import org.pokemonrun.exception.CustomException;
import org.pokemonrun.repository.JwtTokenRepository;
import org.pokemonrun.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class LoginService implements ILoginService
{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenRepository jwtTokenRepository;
    @Autowired
    private UserClient UserClient;

    @Override
    public String login(String username, String password) {
        try {
            if (!UserClient.login(username,password) ) {
                throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
            }
            else
            {
                String token = jwtTokenProvider.createToken(username);
                return token;
            }

        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public boolean logout(String token) {
         jwtTokenRepository.delete(new JwtToken(token));
         return true;
    }

    @Override
    public Boolean isValidToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }

    @Override
    public String createNewToken(String token) {
        String username = jwtTokenProvider.getUsername(token);
        String newToken =  jwtTokenProvider.createToken(username);
        return newToken;
    }
}
