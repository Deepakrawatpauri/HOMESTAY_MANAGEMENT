package com.hotel.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
   public User findByEmail(String email);
	
	
}

