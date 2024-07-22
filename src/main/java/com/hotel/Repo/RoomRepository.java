package com.hotel.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    // Additional query methods can be defined here if needed
}
