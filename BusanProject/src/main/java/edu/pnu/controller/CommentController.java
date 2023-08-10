package edu.pnu.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dto.BusanFoodCommentDTO;
import edu.pnu.dto.BusanPlaceCommentDTO;
import edu.pnu.entity.BusanComment;
import edu.pnu.entity.BusanFestival;
import edu.pnu.entity.BusanFood;
import edu.pnu.entity.BusanPlace;
import edu.pnu.entity.BusanTour;
import edu.pnu.entity.BusanUser;
import edu.pnu.repository.BusanCommentRepository;
import edu.pnu.repository.BusanFestivalRepository;
import edu.pnu.repository.BusanFoodRepository;
import edu.pnu.repository.BusanPlaceRepository;
import edu.pnu.repository.BusanTourRepository;
import edu.pnu.repository.BusanUserRepository;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private BusanFoodRepository busanFoodRepository;
	@Autowired
	private BusanPlaceRepository busanPlaceRepository;
	@Autowired
	private BusanCommentRepository busanCommentRepository;
	@Autowired
	private BusanUserRepository busanUserRepository;
	@Autowired
	private BusanTourRepository busanTourRepository;
	@Autowired
	private BusanFestivalRepository busanFestivalRepository;

	// 댓글집어넣기
	@PostMapping("/addComment")
	public String addComment(@RequestBody BusanComment comment) {
		BusanUser user = busanUserRepository.findByUsername(comment.getUser().getUsername()).orElse(null);

		if (user == null) {
			return "User not found";
		}

		if (comment.getPlace() != null) {
			BusanPlace busanPlace = busanPlaceRepository.findById(comment.getPlace().getId()).orElse(null);
			if (busanPlace != null) {
				BusanComment newComment = new BusanComment();
				newComment.setPlacecomment(comment.getPlacecomment());
				newComment.setPlace(busanPlace);
				newComment.setUser(user);

				busanCommentRepository.save(newComment);
				return "Comment added successfully for BusanPlace";
			}
		} else if (comment.getFood() != null) {
			BusanFood busanFood = busanFoodRepository.findById(comment.getFood().getId()).orElse(null);
			if (busanFood != null) {
				BusanComment newComment = new BusanComment();
				newComment.setFoodcomment(comment.getFoodcomment());
				newComment.setFood(busanFood);
				newComment.setUser(user);

				busanCommentRepository.save(newComment);
				return "Comment added successfully for BusanFood";
			}
		} else if (comment.getFestival() != null) {
			BusanFestival busanFestival = busanFestivalRepository.findById(comment.getFestival().getId()).orElse(null);
			if (busanFestival != null) {
				BusanComment newComment = new BusanComment();
				newComment.setFestivalcomment(comment.getFestivalcomment());
				newComment.setFestival(busanFestival);
				newComment.setUser(user);

				busanCommentRepository.save(newComment);
				return "Comment added successfully for BusanFestival";
			}
		} else if (comment.getTour() != null) {
			BusanTour busanTour = busanTourRepository.findById(comment.getTour().getId()).orElse(null);
			if (busanTour != null) {
				BusanComment newComment = new BusanComment();
				newComment.setTourcomment(comment.getTourcomment());
				newComment.setTour(busanTour);
				newComment.setUser(user);

				busanCommentRepository.save(newComment);
				return "Comment added successfully for BusanTour";
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

			List<String> commentList = placeComments.stream().map(BusanComment::getPlacecomment)
					.collect(Collectors.toList());
			dto.setPlaceComments(commentList);

			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.notFound().build();
	}

	// 댓글 삭제
	@DeleteMapping("/deleteComment/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer id, @RequestBody Map<String, String> authInfo) {
		Optional<BusanComment> commentToDelete = busanCommentRepository.findById(id);
		if (commentToDelete.isPresent()) {
			BusanComment comment = commentToDelete.get();

			// Check if user_id in comment matches the authenticated user's ID
			String username = authInfo.get("username");
			BusanUser authenticatedUser = busanUserRepository.findByUsername(username).orElse(null);
			if (authenticatedUser != null && comment.getUser().getId().equals(authenticatedUser.getId())) {
				if (comment.getFood() != null) {
					comment.setFood(null);
				} else if (comment.getPlace() != null) {
					comment.setPlace(null);
				} else if (comment.getFestival() != null) {
					comment.setFestival(null);
				} else if (comment.getTour() != null) {
					comment.setTour(null);
				}
				busanCommentRepository.delete(comment);
				return ResponseEntity.ok("Comment deleted successfully");
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized to delete this comment");
			}
		}
		return ResponseEntity.notFound().build();
	}

}
