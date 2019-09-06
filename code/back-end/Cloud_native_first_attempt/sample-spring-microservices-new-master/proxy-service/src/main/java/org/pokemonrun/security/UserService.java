package org.pokemonrun.security;
import org.pokemonrun.bean.MongoUserDetails;
import org.pokemonrun.client.UserClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserClient userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String [] authorities=new String[0];
        authorities[0]="ROLE_USER";
        MongoUserDetails userDetails = new MongoUserDetails(username,"hhh",1,
                false, false,true,authorities);
        return userDetails;
    }



}
