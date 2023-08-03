package edu.pnu.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.entity.BusanUser;
import edu.pnu.repository.BusanUserRepository;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private BusanUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        BusanUser busanuser = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + id));

        return new org.springframework.security.core.userdetails.User(
        		busanuser.getId(),
        		busanuser.getPassword(),
                new ArrayList<>()
        );
    }
}

