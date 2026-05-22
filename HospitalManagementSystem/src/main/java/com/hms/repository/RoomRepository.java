package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.Bed;
import com.hms.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	@Query("SELECT b FROM Bed b WHERE b.room.roomId = :roomId")
	List<Bed> findByRoomId(@Param("roomId") Long roomId);
	@Query("SELECT r FROM Room r WHERE r.availabilityStatus = true")
	List<Room> findAvailableRooms();
}
