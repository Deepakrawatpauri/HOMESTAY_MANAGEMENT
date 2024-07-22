package com.hotel.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entities.HomestayDetail;


public interface HomestayDetailRepository extends JpaRepository<HomestayDetail ,Long> {
    // Additional query methods can be defined here if needed
}