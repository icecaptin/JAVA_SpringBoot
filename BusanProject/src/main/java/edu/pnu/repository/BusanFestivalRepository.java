package edu.pnu.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.pnu.entity.BusanFestival;

@Repository
public interface BusanFestivalRepository extends JpaRepository<BusanFestival, Integer> {
    @Query("SELECT f FROM BusanFestival f WHERE f.startDate >= CURRENT_DATE ORDER BY f.startDate ASC")
    List<BusanFestival> findUpcomingFestivals(Pageable pageable);
    
    BusanFestival findById(long id);
    
    @Query("SELECT DISTINCT p.tags FROM BusanFestival p WHERE p.tags IS NOT NULL")
    List<String> findAllTags();
    
//    @Query(value = """
//            SELECT tag_word, COUNT(*) AS tag_count \
//            FROM ( \
//              SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(tags, ',', n), ',', -1) AS tag_word \
//              FROM busanfestival \
//              CROSS JOIN ( \
//                SELECT 1 AS n UNION ALL \
//                SELECT 2 UNION ALL \
//                SELECT 3 UNION ALL \
//                SELECT 4 UNION ALL \
//                SELECT 5 UNION ALL \
//                SELECT 6 UNION ALL \
//                SELECT 7 UNION ALL \
//                SELECT 8 UNION ALL \
//                SELECT 9 UNION ALL \
//                SELECT 10 UNION ALL \
//                SELECT 11 UNION ALL \
//                SELECT 12 UNION ALL \
//                SELECT 13 UNION ALL \
//                SELECT 14 UNION ALL \
//                SELECT 15 \
//              ) AS numbers \
//            ) AS tag_words \
//            WHERE tag_word <> '' \
//            GROUP BY tag_word \
//            ORDER BY tag_count DESC \
//            LIMIT 20\
//            """,
//            nativeQuery = true)
//    List<Object[]> findTop15FestivalTags();
    
    
}
