package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hms.entity.Bed;
import com.hms.entity.Room;

public interface BedRepository extends JpaRepository<Bed, Long> {
	@Query("SELECT b FROM Bed b WHERE b.status='AVAILABLE'")
	List<Bed> findAllAvailableBeds();
	@Query("SELECT b FROM Bed b WHERE b.status='OCCUPIED'")
	public List<Bed> findAllOccupiedBeds();
	List<Bed> findByRoom(Room room);
}
