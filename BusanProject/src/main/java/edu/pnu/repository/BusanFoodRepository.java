package edu.pnu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.entity.BusanFood;

@Repository
public interface BusanFoodRepository extends JpaRepository<BusanFood, Long> {
	
}
