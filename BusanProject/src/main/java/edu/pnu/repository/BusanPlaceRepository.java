package edu.pnu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.entity.BusanPlace;

@Repository
public interface BusanPlaceRepository extends JpaRepository<BusanPlace, Long> {

	BusanPlace findById(long id);
}
