package edu.pnu.repository;

import edu.pnu.entity.BusanTour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusanTourRepository extends JpaRepository<BusanTour, Long> {
	
}
