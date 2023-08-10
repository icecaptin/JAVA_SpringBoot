package edu.pnu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.dto.BusanFoodCommentDTO;
import edu.pnu.dto.BusanPlaceCommentDTO;
import edu.pnu.dto.BusanFestivalCommentDTO;
import edu.pnu.dto.BusanTourCommentDTO;
import edu.pnu.entity.BusanComment;

@Repository
public interface BusanCommentRepository extends JpaRepository<BusanComment, Integer> {
	List<BusanComment> findByFoodId(Integer foodId);

	List<BusanComment> findByPlaceId(Integer placeId);

	List<BusanComment> findByTourId(Integer tourId);

	List<BusanComment> findByFestivalId(Integer festivalId);

	List<BusanFoodCommentDTO> findFoodCommentsByFoodId(Integer foodId);

	List<BusanPlaceCommentDTO> findPlaceCommentsByPlaceId(Integer placeId);

	List<BusanFestivalCommentDTO> findFestivalCommentsByFestivalId(Integer festivalId);

	List<BusanTourCommentDTO> findTourCommentsByTourId(Integer tourId);
}
