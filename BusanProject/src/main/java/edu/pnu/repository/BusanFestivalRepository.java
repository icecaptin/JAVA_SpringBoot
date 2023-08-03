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
    
    @Query(value = "SELECT tag_word, COUNT(*) AS tag_count " +
            "FROM ( " +
            "  SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(tags, ',', n), ',', -1) AS tag_word " +
            "  FROM busanfestival " +
            "  CROSS JOIN ( " +
            "    SELECT 1 AS n UNION ALL " +
            "    SELECT 2 UNION ALL " +
            "    SELECT 3 UNION ALL " +
            "    SELECT 4 UNION ALL " +
            "    SELECT 5 UNION ALL " +
            "    SELECT 6 UNION ALL " +
            "    SELECT 7 UNION ALL " +
            "    SELECT 8 UNION ALL " +
            "    SELECT 9 UNION ALL " +
            "    SELECT 10 UNION ALL " +
            "    SELECT 11 UNION ALL " +
            "    SELECT 12 UNION ALL " +
            "    SELECT 13 UNION ALL " +
            "    SELECT 14 UNION ALL " +
            "    SELECT 15 UNION ALL " +
            "    SELECT 16 UNION ALL " +
            "    SELECT 17 UNION ALL " +
            "    SELECT 18 UNION ALL " +
            "    SELECT 19 UNION ALL " +
            "    SELECT 20 " +
            "  ) AS numbers " +
            ") AS tag_words " +
            "WHERE tag_word <> '' " +
            "GROUP BY tag_word " +
            "ORDER BY tag_count DESC " +
            "LIMIT 20",
            nativeQuery = true)
    List<Object[]> findTop20FestivalTags();
}
