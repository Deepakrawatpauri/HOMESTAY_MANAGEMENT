package com.hotel.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Additional query methods can be defined here if needed
}

