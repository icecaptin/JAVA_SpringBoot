package edu.pnu.repository;

import edu.pnu.entity.BusanFestival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusanFestivalRepository extends JpaRepository<BusanFestival, Long> {
	
}
