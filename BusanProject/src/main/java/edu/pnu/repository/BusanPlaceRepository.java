package edu.pnu.repository;

import edu.pnu.entity.BusanPlace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusanPlaceRepository extends JpaRepository<BusanPlace, Long> {
	
}
