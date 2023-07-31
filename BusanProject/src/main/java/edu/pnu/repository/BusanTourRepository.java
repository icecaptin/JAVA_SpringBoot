package edu.pnu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pnu.entity.BusanTour;

@Repository
public interface BusanTourRepository extends JpaRepository<BusanTour, Long> {

    @Query("SELECT t FROM BusanTour t WHERE t.tags LIKE %:tag%")
    List<BusanTour> findByTagsContaining(@Param("tag") String tag);

    @Query("SELECT t FROM BusanTour t WHERE t.tags IN :tags GROUP BY t.id HAVING COUNT(DISTINCT t.tags) = :tagCount")
    List<BusanTour> findByAllTagsIn(@Param("tags") List<String> tags, @Param("tagCount") Long tagCount);

    @Query("SELECT DISTINCT t.tags FROM BusanTour t")
    List<String> findDistinctTags();
}
