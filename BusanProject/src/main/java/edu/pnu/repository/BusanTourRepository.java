package edu.pnu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.pnu.entity.BusanTour;

@Repository
public interface BusanTourRepository extends JpaRepository<BusanTour, Long> {
    
    @Query("SELECT DISTINCT t.tags FROM BusanTour t")
    List<String> findDistinctTags();
}
