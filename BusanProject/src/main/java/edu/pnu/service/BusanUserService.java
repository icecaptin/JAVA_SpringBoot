package edu.pnu.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.entity.BusanUser;
import edu.pnu.repository.BusanUserRepository;

@Service
public class BusanUserService implements UserDetailsService {

    private final BusanUserRepository busanuserRepository;

    @Autowired
    public BusanUserService(BusanUserRepository busanuserRepository) {
        this.busanuserRepository = busanuserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BusanUser> userOptional = busanuserRepository.findByUsername(username);
        
        if (userOptional.isPresent()) {
            BusanUser user = userOptional.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), Collections.emptyList()
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
