package com.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hotel.Repo.UserRepository;
import com.hotel.entities.User;


@Service
public class UserDetailsServicesImpl implements UserDetailsService {
@ Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	User user=	userRepository.findByEmail(email);
		
		if(user!=null) {
			return new CustomUserDetails(user);
		}
		
		
		throw new UsernameNotFoundException("user not available");
	}

}
