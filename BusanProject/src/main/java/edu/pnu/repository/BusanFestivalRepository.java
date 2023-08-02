package edu.pnu.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.pnu.entity.BusanFestival;

@Repository
public interface BusanFestivalRepository extends JpaRepository<BusanFestival, Long> {
    @Query("SELECT f FROM BusanFestival f WHERE f.startDate >= CURRENT_DATE ORDER BY f.startDate ASC")
    List<BusanFestival> findUpcomingFestivals(Pageable pageable);
    
    BusanFestival findById(long id);
}
