package edu.pnu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.pnu.entity.BusanPlace;

@Repository
public interface BusanPlaceRepository extends JpaRepository<BusanPlace, Integer> {

	Optional<BusanPlace> findById(Integer id);
	
	@Query("SELECT p.tags FROM BusanPlace p WHERE p.tags IS NOT NULL")
    List<String> findAllTags();
	
//	@Query(value = """
//	        SELECT tag_word, COUNT(*) AS tag_count \
//	        FROM ( \
//	          SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(tags, ',', n), ',', -1) AS tag_word \
//	          FROM busanplace \
//	          CROSS JOIN ( \
//	            SELECT 1 AS n UNION ALL \
//	            SELECT 2 UNION ALL \
//	            SELECT 3 UNION ALL \
//	            SELECT 4 UNION ALL \
//	            SELECT 5 UNION ALL \
//	            SELECT 6 UNION ALL \
//	            SELECT 7 UNION ALL \
//	            SELECT 8 UNION ALL \
//	            SELECT 9 UNION ALL \
//	            SELECT 10 UNION ALL \
//	            SELECT 11 UNION ALL \
//	            SELECT 12 UNION ALL \
//	            SELECT 13 UNION ALL \
//	            SELECT 14 UNION ALL \
//	            SELECT 15 \
//	          ) AS numbers \
//	        ) AS tag_words \
//	        WHERE tag_word <> '' \
//	        GROUP BY tag_word \
//	        ORDER BY tag_count DESC \
//	        LIMIT 20\
//	        """,
//	        nativeQuery = true)
//	List<Object[]> findTop15PlaceTags();
//    
    
}
