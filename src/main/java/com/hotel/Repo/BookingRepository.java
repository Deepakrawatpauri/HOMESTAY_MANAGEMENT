package com.hotel.Repo;


import org.springframework.data.jpa.repository.JpaRepository;



public interface BookingRepository extends JpaRepository<com.hotel.entities.Booking, Long> {
    // Additional query methods can be defined here if needed
}
