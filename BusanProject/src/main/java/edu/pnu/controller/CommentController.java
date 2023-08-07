package edu.pnu.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dto.BusanFoodCommentDTO;
import edu.pnu.dto.BusanPlaceCommentDTO;
import edu.pnu.entity.BusanComment;
import edu.pnu.entity.BusanFood;
import edu.pnu.entity.BusanPlace;
import edu.pnu.repository.BusanCommentRepository;
import edu.pnu.repository.BusanFoodRepository;
import edu.pnu.repository.BusanPlaceRepository;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private BusanFoodRepository busanFoodRepository;
	@Autowired
	private BusanPlaceRepository busanPlaceRepository;
	@Autowired
	private BusanCommentRepository busanCommentRepository;

	// 댓글집어넣기
	@PostMapping("/addComment")
	public String addComment(@RequestBody BusanComment comment) {
		if (comment.getPlace() != null) {
			BusanPlace busanPlace = busanPlaceRepository.findById(comment.getPlace().getId()).orElse(null);
			if (busanPlace != null) {
				// 새로운 댓글 생성 및 설정
				BusanComment newComment = new BusanComment();
				newComment.setPlacecomment(comment.getPlacecomment());
				newComment.setPlace(busanPlace);

				busanCommentRepository.save(newComment);
				return "Comment added successfully for BusanPlace";
			}
		} else if (comment.getFood() != null) {
			BusanFood busanFood = busanFoodRepository.findById(comment.getFood().getId()).orElse(null);
			if (busanFood != null) {
				// 새로운 댓글 생성 및 설정
				BusanComment newComment = new BusanComment();
				newComment.setFoodcomment(comment.getFoodcomment());
				newComment.setFood(busanFood);

				busanCommentRepository.save(newComment);
				return "Comment added successfully for BusanFood";
			}
		}
		return "Entity not found";
	}

	// 댓글불러오기
	@GetMapping("/foodcomment/{id}")
	public ResponseEntity<BusanFoodCommentDTO> getFoodCommentsByFoodId(@PathVariable Integer id) {
		BusanFood food = busanFoodRepository.findById(id).orElse(null);
		if (food != null) {
			List<BusanComment> foodComments = busanCommentRepository.findByFoodId(id);

			BusanFoodCommentDTO dto = new BusanFoodCommentDTO();
			dto.setName(food.getName());
			dto.setGugun(food.getGugun());
			dto.setAddr(food.getAddr());

			List<String> commentList = foodComments.stream().map(BusanComment::getFoodcomment)
					.collect(Collectors.toList());
			dto.setFoodComments(commentList);

			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/placecomment/{id}")
	public ResponseEntity<BusanPlaceCommentDTO> getPlaceCommentsByPlaceId(@PathVariable Integer id) {
	    BusanPlace place = busanPlaceRepository.findById(id).orElse(null);
	    if (place != null) {
	        List<BusanComment> placeComments = busanCommentRepository.findByPlaceId(id);

	        BusanPlaceCommentDTO dto = new BusanPlaceCommentDTO();
	        dto.setName(place.getName());
	        dto.setGugun(place.getGugun());
	        dto.setAddr(place.getAddr());

	        List<String> commentList = placeComments.stream()
	                .map(BusanComment::getPlacecomment)
	                .collect(Collectors.toList());
	        dto.setPlaceComments(commentList);

	        return ResponseEntity.ok(dto);
	    }
	    return ResponseEntity.notFound().build();
	}


}
